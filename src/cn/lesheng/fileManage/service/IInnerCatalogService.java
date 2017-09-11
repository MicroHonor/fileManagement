package cn.lesheng.fileManage.service;

import java.util.Map;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.User;

public interface IInnerCatalogService {

	PageInfo<Map<String, Object>> list(PageInfo<Map<String, Object>> page,
			User currentUser) throws Exception;

}
