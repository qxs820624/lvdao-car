package com.lvdao.entity;

import java.io.Serializable;
/**
 * 角色 t_role
 * date: 2016年9月6日 下午8:15:09 
 * @author wangyu
 */
public class RoleEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8661925033322381262L;
	private String  roleId;
    private String  roleName;
    private String  roleDesc;
	
    
    
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
}