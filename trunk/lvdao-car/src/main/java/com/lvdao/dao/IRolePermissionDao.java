package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.RolePermissionEntity;

public interface IRolePermissionDao extends IBaseDao<RolePermissionEntity> {

	int getCount(Map<String, Object> map);
	List<String> queryPermissionListByRoleId(String roleId);
	
}