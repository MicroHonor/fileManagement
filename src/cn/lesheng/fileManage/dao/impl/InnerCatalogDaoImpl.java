package cn.lesheng.fileManage.dao.impl;



import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.lesheng.fileManage.dao.IInnerCatalogDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.InnerCatalog;
import cn.lesheng.fileManage.model.User;

@Repository
public class InnerCatalogDaoImpl extends CommonDaoImpl<InnerCatalog> implements IInnerCatalogDao {

	@Override
	public PageInfo<InnerCatalog> findPage(PageInfo<InnerCatalog> page,
			User user) throws Exception {
		page.setWhereHql(" and inputNo = ? and isCompared = false");
		page.setValues(new Object[]{user.getOrderType()});
		Map<String,String> orderby = new HashMap<String,String>();
		orderby.put("id", "desc");
		page.setOrderby(orderby);
		return super.findByPage(page);
	}

	
}
