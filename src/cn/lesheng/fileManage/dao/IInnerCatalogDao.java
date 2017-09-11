package cn.lesheng.fileManage.dao;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.InnerCatalog;
import cn.lesheng.fileManage.model.User;


public interface IInnerCatalogDao extends ICommonDao<InnerCatalog> {
	
	PageInfo<InnerCatalog> findPage(PageInfo<InnerCatalog> page,User user)throws Exception;

}
