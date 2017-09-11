package cn.lesheng.fileManage.dao;

import java.util.List;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.model.HouseholdCatalog;
import cn.lesheng.fileManage.model.User;

public interface IHouseholdCatalogDao extends ICommonDao<HouseholdCatalog> {

	List<HouseholdCatalog> findByfileNo(String fileNo,Integer inputNo) throws Exception;
	
	List<HouseholdCatalog> findByfileNoInCompared(String fileNo,Integer inputNo) throws Exception;

	boolean isExist(String fileNo, Integer inputNo, Long id)throws Exception;

	List<HouseholdCatalog> findBrother(HouseholdCatalog first)throws Exception;

	void merge(HouseholdCatalog hhc) throws Exception;
	
	void updateCompared(HouseholdCatalog hhc) throws Exception;

	PageInfo<HouseholdCatalog> findPageCompared(PageInfo<HouseholdCatalog> page)throws Exception;
	
	PageInfo<HouseholdCatalog> findPage(PageInfo<HouseholdCatalog> page,User user)throws Exception;
	

}
