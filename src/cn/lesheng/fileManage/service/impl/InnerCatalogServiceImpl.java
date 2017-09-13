package cn.lesheng.fileManage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.lesheng.fileManage.dao.IInnerCatalogDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.InnerCatalog;
import cn.lesheng.fileManage.model.User;
import cn.lesheng.fileManage.service.IImageService;
import cn.lesheng.fileManage.service.IInnerCatalogService;
import cn.lesheng.fileManage.util.CompareUtils;
import cn.lesheng.fileManage.util.FileUtil;
import cn.lesheng.fileManage.util.GsonUtil;

@Service
public class InnerCatalogServiceImpl extends AbsBaseServiceImpl implements IInnerCatalogService {
	
	@Resource
	private IInnerCatalogDao innerCatalogDaoImpl;
	
	@Resource
	private IImageService imageServiceImpl;
	
	@Value("${fileImagePath}")
	private String imagePath;

	@Override
	public PageInfo<Map<String, Object>> list(
			PageInfo<Map<String, Object>> page, User currentUser)
			throws Exception {
		PageInfo<InnerCatalog> entityPage = this.innerCatalogDaoImpl
				.findPageByInputNo(createQueryPage(page), currentUser.getOrderType());
		page.setList(createMapList(entityPage.getList()));
		return page;
	}

	private PageInfo<InnerCatalog> createQueryPage(
			PageInfo<Map<String, Object>> page) {
		PageInfo<InnerCatalog> entityPage = new PageInfo<InnerCatalog>();
		entityPage.setStart(page.getStart());
		entityPage.setTotalCount(page.getTotalCount());
		return entityPage;
	}

	@Override
	public PageMsg saveOrUpdate(String jsonStr, PageMsg msg, User currentUser)
			throws Exception {
		Map<String,Object> map = GsonUtil.jsonStrToMap(jsonStr);
		InnerCatalog vo = new InnerCatalog();
		String fileNo = (String)map.get("fileNo");
		String idStr = (String)map.get("id");
		map.remove("fileNo");
		map.remove("id");
		map.remove("errors");
		map.remove("imagesCount");
		String content = GsonUtil.mapToJsonStr(map);
		if(!StringUtils.hasText(idStr)){
			vo.setFileNo(fileNo);
			vo.setContent(content);
			vo.setInputNo(currentUser.getOrderType());
			this.innerCatalogDaoImpl.doInsert(vo);
		}else{
			InnerCatalog entity = this.innerCatalogDaoImpl.findById(Long.valueOf(idStr));
			entity.setFileNo(fileNo);
			entity.setContent(content);
//			vo.setId(Long.valueOf(idStr));
//			this.innerCatalogDaoImpl.doUpdate(vo);
			if(StringUtils.hasText(entity.getErrors())){
				this.doCompare(entity);
			}
		}
		msg.setSuccess(true);
		return msg;
	}

	@Override
	public PageMsg delete(String ids, PageMsg msg) throws Exception {
		String[] array = ids.split(",");
		if(array.length>0){
			for(String idStr:array){
				innerCatalogDaoImpl.doDeleteById(Long.valueOf(idStr));
			}
		}
		msg.setSuccess(true);
		msg.setMsg("删除成功!!");
		return msg;
	}

	@Override
	public PageMsg compare(String ids) throws Exception {
		PageMsg msg = new PageMsg();
		StringBuffer buf = new StringBuffer("比对完成!<br>");
		List<InnerCatalog> list = this.innerCatalogDaoImpl.findByIdsString(ids);
		int notInputCount = 0;
		int comparedCount = 0;
		int errorCount = 0;
		for(InnerCatalog entity:list){
			int cr = this.doCompare(entity);
			if(cr==0){
				notInputCount++;
			}else if(cr==1){
				comparedCount++;
			}else if(cr==2){
				errorCount++;
			}
		}
		msg.setSuccess(true);
		if(comparedCount>0){
			buf.append("比对成功的数据有 "+comparedCount+" 条<br>");
		}
		if(errorCount>0){
			buf.append("比对失败的数据有 "+errorCount+" 条<br>");
		}
		if(notInputCount>0){
			buf.append("没有找到对应档号的数据有 "+notInputCount+" 条<br>");
		}
		msg.setMsg(buf.toString());
		return msg;
	}

	private int doCompare(InnerCatalog entity)throws Exception  {
		InnerCatalog brother = innerCatalogDaoImpl.findBrother(entity.getFileNo(),entity.getInputNo());
		if(brother==null){
			entity.setErrors("|fileNo|");
			return 0;
		}
		if(entity.getContent().equals(brother.getContent())){
			entity.setIsCompared(true);
			brother.setIsCompared(true);
			entity.setErrors("");
			brother.setErrors("");
			return 1;
		}else{
			Map<String,Object> entityMap=GsonUtil.jsonStrToMap(entity.getContent());
			Map<String,Object> brotherMap = GsonUtil.jsonStrToMap(brother.getContent());
			String errors = CompareUtils.compareMap(entityMap, brotherMap);
			entity.setErrors(errors);
			brother.setErrors(errors);
			return 2;
		}
		
	}

	@Override
	public PageInfo<Map<String, Object>> listCompared(
			PageInfo<Map<String, Object>> page)
			throws Exception {
		PageInfo<InnerCatalog> entityPage = 
				this.innerCatalogDaoImpl.findPageByCompared(createQueryPage(page));
		page.setList(createMapList(entityPage.getList()));
		return page;
	}

	private List<Map<String, Object>> createMapList(List<InnerCatalog> list) {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		for(InnerCatalog entity:list){
			Map<String,Object> map = GsonUtil.jsonStrToMap(entity.getContent());
			map.put("id", entity.getId());
			map.put("fileNo", entity.getFileNo());
			map.put("errors", entity.getErrors());
			map.put("imagesCount", entity.getImagesCount());
			mapList.add(map);
		}
		return mapList;
	}

	@Override
	public PageMsg checkImages(String ids,PageMsg msg) throws Exception {
		Map<String,List<String>> directories = new FileUtil().checkImages(imagePath);
		List<InnerCatalog> list = this.innerCatalogDaoImpl.findByIdsString(ids);
		int count = 0;
		for(InnerCatalog entity:list){
			String fileNo = entity.getFileNo();
			if(directories.containsKey(fileNo)){
				List<String> images = directories.get(fileNo);
				if(images.size()>0){
					entity.setImagesCount(images.size());
					this.merge(entity);
					this.imageServiceImpl.saveForList(images,fileNo);
					count++;
				}
			}
		}
		if(count>0){
			msg.setMsg("检查完成，共"+count+"条记录匹配成功！");
		}else{
			msg.setMsg("检查完成，没有匹配的记录，请检查！");
		}
		msg.setSuccess(true);
		return msg;
	}

	@Override
	public void merge(InnerCatalog entity) throws Exception {
		this.innerCatalogDaoImpl.doMerge(entity);
	}
	

}
