package com.lvdao.entity;

import java.io.Serializable;
/**
 * 角色权限 t_role_permission
 * date: 2016年9月6日 下午8:15:33 
 * @author wangyu
 */
public class RolePermissionEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4076409092033895696L;
	private String roleId;
    private String roleName;
    private String permissionId;
    private String permissionName;
    
	public synchronized String getRoleId() {
		return roleId;
	}
	
	public synchronized void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public synchronized String getRoleName() {
		return roleName;
	}
	
	public synchronized void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public synchronized String getPermissionId() {
		return permissionId;
	}
	
	public synchronized void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	public synchronized String getPermissionName() {
		return permissionName;
	}
	
	public synchronized void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
 
}