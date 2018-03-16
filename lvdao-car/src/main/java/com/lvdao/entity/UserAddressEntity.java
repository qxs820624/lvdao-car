package com.lvdao.entity;

/**
 * 
 * @author zhaoming
 * 常用地址
 *
 */
public class UserAddressEntity extends BaseEntity{
	
	private static final long serialVersionUID = -3875594544562032843L;

	private String userId;

    private String userName;

    private String address;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    
}