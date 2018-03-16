package com.lvdao.entity;

import java.io.Serializable;
import java.util.List;

public class UserPermissionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5989530390302450801L;

	private String userId;
	
	private String userName;
	
	private List<String> permissionList;

	/**
	 * @return the userId
	 */
	public synchronized String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public synchronized String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public synchronized void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the permissionList
	 */
	public synchronized List<String> getPermissionList() {
		return permissionList;
	}

	/**
	 * @param permissionList the permissionList to set
	 */
	public synchronized void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
	
	
		
}
