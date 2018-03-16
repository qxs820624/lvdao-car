package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IUserMessageNearbyDao;
import com.lvdao.entity.UserMessageNearbyEntity;

@Repository("userMessageNearbyDao")
public class UserMessageNearbyDaoImpl extends BaseDao<UserMessageNearbyEntity> implements IUserMessageNearbyDao {
	
}
