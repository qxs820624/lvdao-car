package com.lvdao.entity;

import java.io.Serializable;
import java.util.Date;

import com.lvdao.common.CommonConst;
import com.lvdao.common.util.StringUtil;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6584862684261844603L;

	private String id;
	private Integer status;
	private boolean active;
	private String createUserId;
	private String createUserName;
	private Date createTime;
	private String updateUserId;
	private String updateUserName;
	private Date updateTime;
	private long version;

	public synchronized String getId() {
		return id;
	}

	public synchronized void setId(String id) {
		this.id = id;
	}

	public synchronized boolean isActive() {
		return active;
	}

	public synchronized void setActive(boolean active) {
		this.active = active;
	}
	
	public synchronized Integer getStatus() {
		return status;
	}
	
	public synchronized void setStatus(Integer status) {
		this.status = status;
	}

	public synchronized String getCreateUserId() {
		return createUserId;
	}

	public synchronized void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public synchronized String getCreateUserName() {
		return createUserName;
	}

	public synchronized void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public synchronized Date getCreateTime() {
		return createTime;
	}

	public synchronized void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public synchronized String getUpdateUserId() {
		return updateUserId;
	}

	public synchronized void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public synchronized String getUpdateUserName() {
		return updateUserName;
	}

	public synchronized void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public synchronized Date getUpdateTime() {
		return updateTime;
	}

	public synchronized void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public synchronized long getVersion() {
		return version;
	}

	public synchronized void setVersion(long version) {
		this.version = version;
	}

	public void initId(){
		this.id = StringUtil.produceUUID();
	}
	
	public void initCreateTime(){
		this.createTime = new Date();
	}
	
	public void initActive(){
		this.active = CommonConst.TRUE;
	}
	
	public void initVersion(){
		this.version = 1;
	}
}
