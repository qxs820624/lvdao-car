package com.lvdao.entity;

import java.io.Serializable;

/**
 * 用户图片关系 t_user_pic
 * date: 2016年9月6日 下午8:41:14 
 * @author wangyu
 */
public class UserPicEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4844942768522107237L;
	private String userId;
    private String userName;
    private String picId;
    private String picUrl;
    private int picUse;
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
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getPicUse() {
		return picUse;
	}
	public void setPicUse(int picUse) {
		this.picUse = picUse;
	}
	
    
}