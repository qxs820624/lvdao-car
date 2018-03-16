package com.lvdao.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IRoleDao;
import com.lvdao.entity.RoleEntity;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<RoleEntity> implements IRoleDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);
	private static final String NAMESPACE = "RoleEntity" + CommonConst.PUNCTUATION_DOT;
	
	@Override
	public int updateById(RoleEntity entity) {
		LOGGER.info("Entering RoleDaoImpl updateById dao...");
		int result = this.getSqlSession().update(NAMESPACE + "updateById", entity);
		LOGGER.info("Exiting RoleDaoImpl updateById dao...");
	    return result;
	}
	
}
