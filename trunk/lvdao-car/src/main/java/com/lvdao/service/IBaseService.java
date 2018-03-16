package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;

public interface IBaseService<T> {
	
	int insert(T entity);
	int update(T entity);
	int delete(T entity);
	List<T> queryList(Map<String, Object> map);
	PageList<T> queryPage(Page page, Map<String, Object> map);
	List<T> queryAll();
	int getMaxId();
	
	//int insert(T entity, String collectionName);
	//ServiceResult<T> update(MongoEntity mongo);
	//List<T> queryList(MongoEntity mongo);
	//PageList<T> queryPage(MongoEntity mongo, Page page);
    //int saveInMongo(String collectionName);
	
}
