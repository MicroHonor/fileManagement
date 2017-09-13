package cn.lesheng.fileManage.dao.impl;



import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lesheng.fileManage.dao.IInnerCatalogDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.InnerCatalog;

@Repository
public class InnerCatalogDaoImpl extends CommonDaoImpl<InnerCatalog> implements IInnerCatalogDao {

	@Override
	public PageInfo<InnerCatalog> findPageByInputNo(PageInfo<InnerCatalog> page,
			Integer inputNo) throws Exception {
		page.setWhereHql(" and inputNo = ? and isCompared = false");
		page.setValues(new Object[]{inputNo});
		Map<String,String> orderby = new HashMap<String,String>();
		orderby.put("id", "desc");
		page.setOrderby(orderby);
		return super.findByPage(page);
	}

	@Override
	public PageInfo<InnerCatalog> findPageByCompared(
			PageInfo<InnerCatalog> page) throws Exception {
		page.setWhereHql(" and inputNo = ? and isCompared = true");
		page.setValues(new Object[]{1});
		Map<String,String> orderby = new HashMap<String,String>();
		orderby.put("id", "desc");
		page.setOrderby(orderby);
		return super.findByPage(page);
	}

	
}
