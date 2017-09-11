package cn.lesheng.fileManage.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lesheng.fileManage.dao.ITFileDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.TFile;
import cn.lesheng.fileManage.service.ITFileService;

@Service
public class TFileServiceImpl implements ITFileService {
	@Resource
	private ITFileDao tFileDaoImpl;

	@Override
	public boolean save(TFile entity) throws Exception {
		return this.tFileDaoImpl.doInsert(entity)!=null;
	}

	@Override
	public PageInfo<TFile> findPageByDocumentUUID(PageInfo<TFile> page,String documentUUID) throws Exception {
		page.setWhereHql(" and documentUUID=? ");
		page.setValues(new Object[]{documentUUID});
		return this.tFileDaoImpl.findByPage(page);
	}

	@Override
	public boolean compareByUUID(String uuid1, String uuid2) throws Exception {
		List<TFile> list1 = listByDocumentUUID(uuid1);
		List<TFile> list2 = listByDocumentUUID(uuid2);
		if(list1.size()==list2.size()){
			Collections.sort(list1);
			Collections.sort(list2);
			for(int i=0;i<list1.size();i++){
				if(!list1.get(i).equals(list2.get(i))){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<TFile> listByDocumentUUID(String documentUUID) throws Exception {
		String whereHql = " and documentUUID=? ";
		Object[] values = new Object[]{documentUUID};
		return this.tFileDaoImpl.findByConditionWithOrder(whereHql, values, null);
	}

	
}
