package com.lvdao.car.vo;

import java.io.Serializable;

public class UserPicVO implements Serializable {

	private static final long serialVersionUID = -4167720968787852264L;

	private String userId;
	private String userName;
	private String key;
	private String picName;
	private String picDesc;
	private String picGroupId;

	public String getPicDesc() {
		return picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}

	public String getPicGroupId() {
		return picGroupId;
	}

	public void setPicGroupId(String picGroupId) {
		this.picGroupId = picGroupId;
	}

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
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

}
