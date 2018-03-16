package com.lvdao.entity;

import java.io.Serializable;

public class PictureGroupEntity extends BaseEntity implements Serializable {
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -829344017620127728L;
	private String picGroupId;
	private String picGroupName;
	private String picGroupDesc;
	private String picId;

	public String getPicGroupId() {
		return picGroupId;
	}

	public void setPicGroupId(String picGroupId) {
		this.picGroupId = picGroupId;
	}

	public String getPicGroupName() {
		return picGroupName;
	}

	public void setPicGroupName(String picGroupName) {
		this.picGroupName = picGroupName;
	}

	public String getPicGroupDesc() {
		return picGroupDesc;
	}

	public void setPicGroupDesc(String picGroupDesc) {
		this.picGroupDesc = picGroupDesc;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

}
