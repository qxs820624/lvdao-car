package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;

public interface IBaseDao<T> {

	int insert(T entity);
	int update(T entity);
	int delete(T entity);
	List<T> queryList(Map<String, Object> map);
	PageList<T> queryPage(Page page, Map<String, Object> map);
	List<T> queryAll();
	int getMaxId();
	//int saveInMongo(String collectionName);
	
}
