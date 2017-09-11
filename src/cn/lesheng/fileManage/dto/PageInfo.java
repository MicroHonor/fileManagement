package cn.lesheng.fileManage.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageInfo<T> extends PageMsg implements Serializable {

	private static final long serialVersionUID = 513467275778692943L;
	
	private int start=0;			//数据开始
	private int limit=20;			//显示的行数
	private Long totalCount=0L;		//总行数
	private String whereHql="";		//查询的where条件
	private Object[] values;		//参数集合
	private Map<String, String> orderby;		//排序条件
	private T entity;				//实体对象
	private List<T>	list = new ArrayList<T>();
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getWhereHql() {
		return whereHql;
	}
	public void setWhereHql(String whereHql) {
		this.whereHql = whereHql;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	public Map<String, String> getOrderby() {
		return orderby;
	}
	public void setOrderby(Map<String, String> orderby) {
		this.orderby = orderby;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
