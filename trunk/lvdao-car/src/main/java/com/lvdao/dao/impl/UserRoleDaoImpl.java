package com.lvdao.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IUserRoleDao;
import com.lvdao.entity.UserRoleEntity;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDao<UserRoleEntity> implements IUserRoleDao {

	private static Logger LOGGER = LoggerFactory.getLogger(UserRoleDaoImpl.class);
	private static final String NAMESPACE = "UserRoleEntity" + CommonConst.PUNCTUATION_DOT;
	
	@Override
	public int updateById(UserRoleEntity entity) {
		LOGGER.info("Entering {0} updateById dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateById", entity);
		LOGGER.info("Exiting {0} updateById dao...");
	    return result;
	}
	
}
