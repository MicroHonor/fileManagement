package cn.lesheng.fileManage.service;

import java.util.Map;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.User;

public interface IBaseCatalogService {
	
	PageInfo<Map<String, Object>> list(PageInfo<Map<String, Object>> page,
			User currentUser) throws Exception;

	PageMsg saveOrUpdate(String jsonStr, PageMsg msg, User currentUser)throws Exception;

	PageMsg delete(String ids, PageMsg msg)throws Exception;

	PageMsg compare(String ids)throws Exception;

	PageInfo<Map<String, Object>> listCompared(
			PageInfo<Map<String, Object>> page)throws Exception;

}
