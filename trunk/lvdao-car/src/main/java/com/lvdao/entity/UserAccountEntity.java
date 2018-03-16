package com.lvdao.entity;

import java.io.Serializable;

/**
 * User Account Entity
 * 
 * @author Jay.Zhang
 * @since 2015-01-18 22:47
 */
public class UserAccountEntity  extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -1245765859097077964L;
	private String userId;
    private String userName;
    private String accountId;
    private String accountName;
    private String accountAmount;
    private String ownAmount;
    private String shareAmount;
   public synchronized String getOwnAmount() {
		return ownAmount;
	}

	public synchronized void setOwnAmount(String ownAmount) {
		this.ownAmount = ownAmount;
	}

	public synchronized String getShareAmount() {
		return shareAmount;
	}

	public synchronized void setShareAmount(String shareAmount) {
		this.shareAmount = shareAmount;
	}

	//数据库不存在的字段
    private String userRealName;//昵称
    private String headUrl;//用户头像
    private String userType;//用户级别
    
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public synchronized String getUserId() {
		return userId;
	}
	
	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}
	
	public synchronized String getUserName() {
		return userName;
	}
	
	public synchronized void setUserName(String userName) {
		this.userName = userName;
	}
	
	public synchronized String getAccountId() {
		return accountId;
	}
	
	public synchronized void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public synchronized String getAccountName() {
		return accountName;
	}
	
	public synchronized void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public synchronized String getAccountAmount() {
		return accountAmount;
	}
	
	public synchronized void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}

}