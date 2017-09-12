package cn.lesheng.fileManage.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.lesheng.fileManage.dao.ICommonDao;
import cn.lesheng.fileManage.dto.PageInfo;
import cn.lesheng.fileManage.util.GenericType;


@Repository
public class CommonDaoImpl<T> implements ICommonDao<T> {
	@Resource
	protected SessionFactory sessionFactory;
	
	@Override
	public Serializable doInsert(T entity) throws Exception {
		return this.sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void doUpdate(T entity) throws Exception {
		this.sessionFactory.getCurrentSession().update(entity);
	}
	
	@Override
	public void doMerge(T entity) throws Exception {
		this.sessionFactory.getCurrentSession().merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws Exception {
		String className = GenericType.getEntityClass(this.getClass())
				.getName();
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				"from " + className);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) throws Exception {
		return (T) this.sessionFactory.getCurrentSession().get(
				GenericType.getEntityClass(this.getClass()), id);
	}

	@Override
	public void doDeleteById(Serializable... ids) throws Exception {
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				Object obj = this.findById(ids[i]);
				if (obj != null) {
					this.sessionFactory.getCurrentSession().delete(obj);
				}
			}
		}
	}

	@Override
	public boolean doUpdateUseHql(String hql, Object[] values) throws Exception {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByConditionWithOrder(String whereHql, Object[] values,
			Map<String, String> orderby) throws Exception {
		String headHql = "from "+ GenericType.getEntityClass(this.getClass())
				.getName() +" where 1=1 ";
		// 创建一个StringBuffer用来存储遍历Map的数据
		StringBuffer buf = new StringBuffer("");
		// 判断Map是否为空
		if (orderby != null && orderby.size() > 0) {
			// 向StringBuffer添加 order by 字符串用于设置排序
			buf.append(" order by ");
			// 遍历Map
			for (Map.Entry<String, String> map : orderby.entrySet()) {
				buf.append(" " + map.getKey() + " " + map.getValue() + ",");
			}
			// 遍历Map之后要删除最后一个逗号
			buf.deleteCharAt(buf.length() - 1);
		}
		if(whereHql==null){
			whereHql="";
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				headHql+whereHql + buf.toString());
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();
	}

	@Override
	public Integer doDeleteByCondition(String whereHql, Object[] values)
			throws Exception {
		String headHql = "delete "+ GenericType.getEntityClass(this.getClass())
				.getName() +" where 1=1 ";
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				headHql+whereHql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}

	@Override
	public Map<String,Object> findByConditionWithOrderAndSplitPage(String whereHql,
			Object[] values, Map<String, String> orderby, Integer pageIndex,
			Integer pageSize) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String headHql = "from "+ GenericType.getEntityClass(this.getClass())
				.getName() +" where 1=1 ";
		// 创建一个StringBuffer用来存储遍历Map的数据
		StringBuffer buf = new StringBuffer("");
		// 判断Map是否为空
		if (orderby != null && orderby.size() > 0) {
			// 向StringBuffer添加 order by 字符串用于设置排序
			buf.append(" order by ");
			// 遍历Map
			for (Map.Entry<String, String> map : orderby.entrySet()) {
				buf.append(" " + map.getKey() + " " + map.getValue() + ",");
			}
			// 遍历Map之后要删除最后一个逗号
			buf.deleteCharAt(buf.length() - 1);
		}
		if(whereHql==null){
			whereHql="";
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				headHql+whereHql + buf.toString());
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		resultMap.put("count", query.list().size());
		query.setFirstResult((pageIndex-1)*pageSize);
		query.setMaxResults(pageSize);
		resultMap.put("list", query.list());
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByGroupStatistics(String selectHql,
			String groupHql) throws Exception {
		String hql = selectHql + " from " + GenericType.getEntityClass(this.getClass())
				.getName() + groupHql;
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<T> findByPage(PageInfo<T> page) throws Exception {
		Long totalCount = this.count(page);
		if(totalCount>0){
			page.setTotalCount(totalCount);
			String queryHql = createQueryHql(page.getWhereHql(),page.getOrderby());
			Query query = createQuery(queryHql,page.getValues());
			query.setFirstResult(page.getStart());
			query.setMaxResults(page.getLimit());
			page.setList(query.list());
		}
		return page;
	}

	private String createQueryHql(String whereHql, Map<String, String> orderby) {
		StringBuffer orderHql = new StringBuffer("");
		// 判断Map是否为空
		if (orderby != null && orderby.size() > 0) {
			// 向StringBuffer添加 order by 字符串用于设置排序
			orderHql.append(" order by ");
			// 遍历Map
			for (Map.Entry<String, String> map : orderby.entrySet()) {
				orderHql.append(" " + map.getKey() + " " + map.getValue() + ",");
			}
			// 遍历Map之后要删除最后一个逗号
			orderHql.deleteCharAt(orderHql.length() - 1);
		}
		return getBaseHql()+whereHql+orderHql.toString();
	}

	@Override
	public Long count(PageInfo<T> page) throws Exception {
		return ((Number)createQuery(createCountHql(page.getWhereHql()),page.getValues()).uniqueResult()).longValue();
	}

	private String createCountHql(String whereHql) {
		return "select count(*) "+getBaseHql()+whereHql;
	}

	private Query createQuery(String hql,Object[] values) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
	private String getBaseHql(){
		return "from "+ GenericType.getEntityClass(this.getClass()).getSimpleName()+" where 1=1";
	}

	@Override
	public List<T> findByIdsString(String ids) throws Exception {
		String whereHql = " and id in ("+ids+") ";
		return this.findByConditionWithOrder(whereHql, null, null);
	}

	@Override
	public T findBrother(String fileNo, Integer inputNo) throws Exception {
		String whereHql = " and fileNo=? and inputNo=? ";
		Object[] values = {fileNo,3-inputNo};
		List<T> list =this.findByConditionWithOrder(whereHql, values, null);
		return list.size()==1?list.get(0):null;
	}

}
