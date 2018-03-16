package com.lvdao.entity;

/**
 * 常用线路
 * @author zhaoming
 *
 */
public class UserLineEntity extends BaseEntity{

	private static final long serialVersionUID = 2344522118315383250L;

	private String userId;

    private String userName;

    private String originalLineName;

    private String targetLineName;


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

    public String getOriginalLineName() {
        return originalLineName;
    }

    public void setOriginalLineName(String originalLineName) {
        this.originalLineName = originalLineName == null ? null : originalLineName.trim();
    }

    public String getTargetLineName() {
        return targetLineName;
    }

    public void setTargetLineName(String targetLineName) {
        this.targetLineName = targetLineName == null ? null : targetLineName.trim();
    }

}