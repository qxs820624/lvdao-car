package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserPicEntity;

public interface IUserPicDao extends IBaseDao<UserPicEntity> {
	
	int count(Map<String, Object> map);

	int updateUserPicByUserId(UserPicEntity entity);

	int insertList(List<UserPicEntity> entityList);

	int batchDelete(List<UserPicEntity> entityList);

	int getMaxGroupId();
}
