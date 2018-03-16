package com.lvdao.entity;

import java.io.Serializable;
/**
 * 用户邮件 t_user_mail
 * date: 2016年9月6日 下午8:38:16 
 * @author wangyu
 */
public class UserMailEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 833769161014414704L;
	private String userId;
    private String userName;
    private String mailId;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
    
}