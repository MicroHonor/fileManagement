package cn.lesheng.fileManage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.lesheng.fileManage.dto.PageInfo;

/**
 * 定义公共的DAO操作标准,基本的功能包括：增加，修改全部，删除数据，根据编号查询，查询全部，分页显示，数据统计
 * @author Devon
 * @param <K> 表示要操作的主键数据类型，由子接口实现
 * @param <V> 表示要操作的VO类型，由子接口实现
 */
public interface ICommonDao<T> {
	/**
	 * 实现数据的增加操作
	 * @param vo 包含了要增加对象的vo对象
	 * @return 如果保存成功返回true,否则返回false
	 * @throws Exception SQL执行异常
	 */
	public Serializable doInsert(T entity)throws Exception;
	/**
	 * 
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public void doUpdate(T entity)throws Exception;
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void doMerge(T entity)throws Exception;
	/**
	 * 查询指定数据表的全部记录，并且以集合的形式返回
	 * @return 如果表中存在记录，则所有记录封装为VO对象并且以List集合的形式返回<br>
	 * 如果表没有数据，那么集合的长度为0(size()==0)
	 * @throws Exception SQL执行异常
	 */
	public List<T> findAll()throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T findById(Serializable id)throws Exception;
	
	public void doDeleteById(Serializable... ids) throws Exception;
	
	public Integer doDeleteByCondition(String whereHql,Object[] values)throws Exception;
	
	public boolean doUpdateUseHql(String hql,Object[] values) throws Exception;
	
	public List<T> findByConditionWithOrder(String whereHql,Object[] values,
			Map<String,String> orderby)throws Exception;
	
	public Map<String,Object> findByConditionWithOrderAndSplitPage(String whereHql,Object[] values,
			Map<String,String> orderby,Integer pageIndex,Integer pageSize)throws Exception;
	
	public List<Object[]> findByGroupStatistics(String selectHql,String groupHql)throws Exception;
	
	public PageInfo<T> findByPage(PageInfo<T> page)throws Exception;
	
	public Long count(PageInfo<T> page)throws Exception;
	
	public List<T> findByIdsString(String ids)throws Exception;
	/**
	 * 查找同档号的另一次输入记录
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	T findBrother(String fileNo,Integer inputNo)throws Exception;
}
