package cn.lesheng.fileManage.service;

import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.InnerCatalog;

public interface IInnerCatalogService extends IBaseCatalogService {

	PageMsg checkImages(String ids,PageMsg msg)throws Exception;
	
	void merge(InnerCatalog entity)throws Exception;
}
