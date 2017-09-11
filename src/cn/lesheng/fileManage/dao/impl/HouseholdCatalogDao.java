package cn.lesheng.fileManage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.lesheng.fileManage.dao.IHouseholdCatalogDao;
import cn.lesheng.fileManage.dao.ITFileDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.HouseholdCatalog;
import cn.lesheng.fileManage.model.User;

@Repository
public class HouseholdCatalogDao extends CommonDaoImpl<HouseholdCatalog> implements IHouseholdCatalogDao {

	@Resource
	private ITFileDao iTFileDaoImpl;
	
	@Override
	public List<HouseholdCatalog> findByfileNo(String fileNo,Integer inputNo) throws Exception {
		String whereHql = " and fileNo = ? and inputNo=? ";
		Object[] values = {fileNo,inputNo};
		return super.findByConditionWithOrder(whereHql, values, null);
	}

	@Override
	public List<HouseholdCatalog> findByfileNoInCompared(String fileNo,
			Integer inputNo) throws Exception {
		String whereHql = " and fileNo = ? and inputNo=? and iscompared=?";
		Object[] values = {fileNo,inputNo,true};
		return super.findByConditionWithOrder(whereHql, values, null);
	}
	
	public Long doInsert(HouseholdCatalog entity)throws Exception{
		entity.setCreateTime(new Date());
		entity.setUuid(UUID.randomUUID().toString());
		return (Long)super.doInsert(entity);
	}

	@Override
	public boolean isExist(String fileNo, Integer inputNo, Long id)
			throws Exception {
		String whereHql = " and fileNo=? and inputNo=? and id!=? ";
		Object[] values = {fileNo,inputNo,id};
		return super.findByConditionWithOrder(whereHql, values, null).size()>1;
	}

	@Override
	public List<HouseholdCatalog> findBrother(HouseholdCatalog entity)
			throws Exception {
		String whereHql = " and fileNo=? and inputNo=? and id!=? ";
		Object[] values = {entity.getFileNo(),3-entity.getInputNo(),entity.getId()};
		return super.findByConditionWithOrder(whereHql, values, null);
	}

	@Override
	public void merge(HouseholdCatalog hhc)throws Exception {
//		HouseholdCatalog entity = super.findById(hhc.getId());
//		if(!entity.getFileNo().equals(hhc.getFileNo())){
//			this.iTFileDaoImpl.updateFileNo(entity.getFileNo(),hhc.getFileNo());
//		}
		super.doMerge(hhc);
	}

	@Override
	public void updateCompared(HouseholdCatalog hhc) throws Exception {
		String hql = " update HouseholdCatalog t set t.isCompared=? where t.id=? ";
		Object[] values = new Object[]{hhc.getIsCompared(),hhc.getId()};
		super.doUpdateUseHql(hql, values);
	}

	@Override
	public PageInfo<HouseholdCatalog> findPageCompared(
			PageInfo<HouseholdCatalog> page) throws Exception {
		page.setWhereHql(" and isCompared = ? and inputNo=? ");
		page.setValues(new Object[]{true,1});
		return super.findByPage(page);
	}

	@Override
	public PageInfo<HouseholdCatalog> findPage(PageInfo<HouseholdCatalog> page,
			User user) throws Exception {
		page.setWhereHql(" and inputNo = ? and isCompared = false");
		page.setValues(new Object[]{user.getOrderType()});
		Map<String,String> orderby = new HashMap<String,String>();
		orderby.put("createTime", "desc");
		orderby.put("id", "desc");
		page.setOrderby(orderby);
		return super.findByPage(page);
	}
	
	
	
}
