package cn.lesheng.fileManage.service;

import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.dto.PageMsg;
import cn.lesheng.fileManage.model.HouseholdCatalog;
import cn.lesheng.fileManage.model.User;

public interface IHouseholdCatalogService {
	/**
	 * 查询全部对象并封装为page
	 * @param page
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	public PageInfo<HouseholdCatalog> list(
			PageInfo<HouseholdCatalog> page, User user) throws Exception;
	/**
	 * 保存或更新
	 * @param listStr
	 * @return
	 * @throws Exception
	 */
	public PageMsg saveOrUpdate(String listStr,PageMsg msg,User user)throws Exception;
	/**
	 * 判断是否存在
	 * @param hhc
	 * @return
	 * @throws Exception
	 */
	public boolean isExistInCompared(HouseholdCatalog hhc)throws Exception;
	
	public boolean isExist(HouseholdCatalog hhc)throws Exception;
	public PageMsg delete(String ids,PageMsg msg)throws Exception;
	public PageMsg compare(String ids)throws Exception;
	public String updateUUID(HouseholdCatalog entity)throws Exception;
	
	public void updateCompared(HouseholdCatalog entity) throws Exception;
	public PageInfo<HouseholdCatalog> listCompared(PageInfo<HouseholdCatalog> page)throws Exception;
	
}
