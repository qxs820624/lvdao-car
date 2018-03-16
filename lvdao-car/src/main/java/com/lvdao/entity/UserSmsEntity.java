package com.lvdao.entity;

import java.io.Serializable;

/**
 * 用户短信关系表 t_user_sms
 * date: 2016年9月6日 下午8:42:33 
 * @author wangyu
 */
public class UserSmsEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 2782045792358176679L;
	private String userId;
    private String userName;
    private String smsId;
    
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
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

    
}