package com.lvdao.service;

import java.util.List;

import com.lvdao.entity.RolePermissionEntity;

public interface IRolePermissionService extends IBaseService<RolePermissionEntity> {
    
	boolean hasPermission(String permCode, List<String> roles);
	List<String> queryPermissionListByRoleId(String roleId);
	boolean saveAllUserPermissionInSession();
	
	/**
	 * 保存所有用户的权限关系在 mongo
	 * @return
	 */
	//boolean saveAllUserPermissionInMongo();
	
	/**
	 * 根据用户id在mongo中查询权限
	 * @param userId
	 * @return
	 */
	//List<String> queryPermissionsInMongoByUserId(String userId);
	
}