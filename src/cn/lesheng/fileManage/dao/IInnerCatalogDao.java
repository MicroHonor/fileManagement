package cn.lesheng.fileManage.dao;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.InnerCatalog;


public interface IInnerCatalogDao extends ICommonDao<InnerCatalog> {
	
	PageInfo<InnerCatalog> findPageByInputNo(PageInfo<InnerCatalog> page,Integer inputNo)throws Exception;

	PageInfo<InnerCatalog> findPageByCompared(PageInfo<InnerCatalog> page)throws Exception;


}
