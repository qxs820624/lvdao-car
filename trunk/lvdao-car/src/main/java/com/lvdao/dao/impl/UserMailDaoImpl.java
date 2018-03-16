package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IUserMailDao;
import com.lvdao.entity.UserMailEntity;

@Repository("userMailDao")
public class UserMailDaoImpl extends BaseDao<UserMailEntity> implements IUserMailDao {
	
}
