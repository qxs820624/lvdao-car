package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IUserSmsDao;
import com.lvdao.entity.UserSmsEntity;

@Repository("userSmsDao")
public class UserSmsDaoImpl extends BaseDao<UserSmsEntity> implements IUserSmsDao {
	
}
