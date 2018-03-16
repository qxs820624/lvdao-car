package com.lvdao.entity;

import java.io.Serializable;
/**
 * 用户角色
 * date: 2016年9月6日 下午8:42:24 
 * @author wangyu
 */
public class UserRoleEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -6702354685932108181L;
	private String  userId;
	private String  userName;
    private String  roleId;
    private String  roleName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}