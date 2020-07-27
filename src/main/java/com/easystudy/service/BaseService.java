package com.easystudy.service;

import java.util.List;

public interface BaseService<T> {
	
	/**
	 * 往数据库插入一条记录，如果已存在则抛异常
	 * @param o
	 */
	public void add(T o);
	/**
	 * 根据id往数据库删除一条记录
	 * @param o 删除对象的o
	 */
	public void delete(T o);
	/**
	 * 修改用户信息
	 * @param User
	 * @return
	 */
	public void update(T o);
	
	/**
	 * 根据主键查找
	 * @param User
	 * @return
	 */
	T findById(Object id);
	
	/**
	 * 根据条件分页查询
	 * @param entityClass 表对应的实体类
	 * @param pageIndex 初始页-从1开始
	 * @param pageSize 每页数量
	 * @return 返回得到的结果集
	 */
	public List<T> findByAttributes(Long pageIndex, Long pageSize);
	
}
