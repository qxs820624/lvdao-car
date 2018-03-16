package com.lvdao.entity;

import java.io.Serializable;

/**
 * 权限  t_permission
 * date: 2016年9月6日 下午8:16:57 
 * @author wangyu
 */
public class PermissionEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8862751905516881800L;
	private String permissionId;
    private String permissionName;
    private String permissionType;
    private String permissionUrl;
    private String permissionDesc;
    
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
	
	public synchronized String getPermissionType() {
		return permissionType;
	}
	
	public synchronized void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}
	
	public synchronized String getPermissionUrl() {
		return permissionUrl;
	}
	
	public synchronized void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	
	public synchronized String getPermissionDesc() {
		return permissionDesc;
	}
	
	public synchronized void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

}