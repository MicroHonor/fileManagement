package cn.lesheng.fileManage.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.lesheng.fileManage.dao.IInnerCatalogDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.InnerCatalog;
import cn.lesheng.fileManage.model.User;
import cn.lesheng.fileManage.service.IInnerCatalogService;

@Service
public class InnerCatalogServiceImpl implements IInnerCatalogService {
	
	@Resource
	private IInnerCatalogDao innerCatalogDaoImpl;

	@Override
	public PageInfo<Map<String, Object>> list(
			PageInfo<Map<String, Object>> page, User currentUser)
			throws Exception {
		PageInfo<InnerCatalog> entityPage = new PageInfo<InnerCatalog>();
		entityPage.setStart(page.getStart());
		entityPage.setTotalCount(page.getTotalCount());
		entityPage = this.innerCatalogDaoImpl.findPage(entityPage, currentUser);
		List<InnerCatalog> entityList = entityPage.getList();
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Type mapType = new TypeToken<Map<String,String>>() {}.getType();
		for(InnerCatalog entity:entityList){
			Gson gson = new GsonBuilder().create();
			Map<String,Object> map = gson.fromJson(entity.getContent(), mapType);
			map.put("id", entity.getId());
			map.put("fileNo", entity.getFileno());
			mapList.add(map);
		}
		page.setList(mapList);
		return page;
	}

}
