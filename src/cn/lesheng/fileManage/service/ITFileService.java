package cn.lesheng.fileManage.service;

import java.util.List;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.TFile;

public interface ITFileService {
	public boolean save(TFile entity)throws Exception;

	public PageInfo<TFile> findPageByDocumentUUID(PageInfo<TFile> page,String documentUUID)throws Exception;

	public boolean compareByUUID(String uuid1, String uuid2)throws Exception;
	
	public List<TFile> listByDocumentUUID(String documentUUID)throws Exception;
}
