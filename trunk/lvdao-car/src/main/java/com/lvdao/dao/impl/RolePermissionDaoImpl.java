package com.lvdao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.dao.IRolePermissionDao;
import com.lvdao.entity.RolePermissionEntity;

@Repository("rolePermissionDao")
public class RolePermissionDaoImpl extends BaseDao<RolePermissionEntity> implements IRolePermissionDao {

	private static Logger LOGGER = LoggerFactory.getLogger(RolePermissionDaoImpl.class);
	private static final String NAMESPACE = "RolePermissionEntity" + CommonConst.PUNCTUATION_DOT;
	
	@Override
	public int getCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(NAMESPACE + "count", map);
	}
	
	@Override
	public List<String> queryPermissionListByRoleId(String roleId) {
		LOGGER.info("Entering RolePermissionDaoImpl queryPermissionListByRoleId Dao...");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<String> list = this.getSqlSession().selectList(NAMESPACE + "queryPermissionListByRoleId", map);
		
		LOGGER.info("Entering RolePermissionDaoImpl queryPermissionListByRoleId Dao...");
		return list;
	}

}
