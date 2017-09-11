package cn.lesheng.fileManage.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.lesheng.fileManage.dao.IHouseholdCatalogDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.HouseholdCatalog;
import cn.lesheng.fileManage.model.User;
import cn.lesheng.fileManage.service.IHouseholdCatalogService;
import cn.lesheng.fileManage.service.ITFileService;
import cn.lesheng.fileManage.util.FileUtil;

@Service
public class HouseholdCatalogService implements IHouseholdCatalogService {
	@Resource
	private IHouseholdCatalogDao householdCatalogDao;
	@Resource
	private ITFileService tFileServiceImpl;


	@Value("${filePhotoPath}")
	private String photoPath;
	
	@Override
	public PageInfo<HouseholdCatalog> list(
			PageInfo<HouseholdCatalog> page,User user) throws Exception {;
		return this.householdCatalogDao.findPage(page,user);
	}

	@Override
	public PageMsg saveOrUpdate(String listStr,PageMsg msg,User user)
			throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create(); 
		Type listType = new TypeToken<List<HouseholdCatalog>>() {}.getType();
		List<HouseholdCatalog> list = gson.fromJson(listStr, listType);
		Integer inputNo = user.getOrderType();
		for(Object obj:list){
			HouseholdCatalog hhc = (HouseholdCatalog) obj;
			hhc.setInputNo(inputNo);
			if(isExist(hhc)){
				msg.setSuccess(false);
				msg.setMsg("您修改的新档号："+hhc.getFileNo()+"已存在!!");
				return msg;
			}else{
				if (hhc.getId()!=0){
					this.householdCatalogDao.merge(hhc);
				}else{
//					if(isExist(hhc)){
//						msg.setSuccess(false);
//						msg.setMsg("您新增的档号"+hhc.getFileNo()+"已存在!!");
//						return msg;
//					}
					this.householdCatalogDao.doInsert(hhc);
				}
			}
		}
		msg.setSuccess(true);
		msg.setMsg("保存成功!!");
		return msg;
	}

	@Override
	public boolean isExistInCompared(HouseholdCatalog hhc) throws Exception {
		return this.householdCatalogDao.findByfileNoInCompared(hhc.getFileNo(),hhc.getInputNo()).size()>0;
	}

	@Override
	public boolean isExist(HouseholdCatalog hhc) throws Exception {
		return this.householdCatalogDao.isExist(hhc.getFileNo(), hhc.getInputNo(),hhc.getId());
	}

	@Override
	public PageMsg delete(String ids,PageMsg msg) throws Exception {
		String[] array = ids.split(",");
		if(array.length>0){
			for(String idStr:array){
				householdCatalogDao.doDeleteById(Long.valueOf(idStr));
			}
		}
		msg.setSuccess(true);
		msg.setMsg("删除成功!!");
		return msg;
	}

	@Override
	public PageMsg compare(String ids) throws Exception {
		PageMsg msg = new PageMsg();
		List<HouseholdCatalog> firstList = this.householdCatalogDao.findByIdsString(ids);
		int count = 0;
		for(HouseholdCatalog first:firstList){
			List<HouseholdCatalog> secondList = this.householdCatalogDao.findBrother(first);
			if(secondList!=null&&secondList.size()==1){
				HouseholdCatalog second = secondList.get(0);
				if(first.compare(second)
						&&tFileServiceImpl.compareByUUID(first.getUuid(),second.getUuid())){
					first.setIsCompared(true);
					second.setIsCompared(true);
					this.householdCatalogDao.doMerge(first);
					this.householdCatalogDao.doMerge(second);
//					updateCompared(first);
//					updateCompared(second);
					count++;
				}
			}
		}
		if(count>0){
			msg.setSuccess(true);
			msg.setMsg("比对完成，有 "+count+" 条记录比对成功！！");
		}else{
			msg.setSuccess(false);
			msg.setMsg("比对失败，没找到一致的数据！！");
		}
		return msg;
	}

	@Override
	public String updateUUID(HouseholdCatalog entity) throws Exception {
		if(entity!=null){
			if(!StringUtils.hasText(entity.getUuid())||
					entity.getUuid().length()!=32||entity.getUuid().length()!=36){
				entity.setUuid(UUID.randomUUID().toString());
			}
		}else{
			throw new Exception("未找到对应的文档!!");
		}
		return entity.getUuid();
	}

	@Override
	public void updateCompared(HouseholdCatalog entity) throws Exception {
		this.householdCatalogDao.updateCompared(entity);
	}

	@Override
	public PageInfo<HouseholdCatalog> listCompared(
			PageInfo<HouseholdCatalog> page) throws Exception {
		return this.householdCatalogDao.findPageCompared(page);
	}

	@Override
	public PageMsg checkPhoto(String ids) throws Exception {
		FileUtil.checkPhoto(photoPath+"123");
		return null;
	}

}
