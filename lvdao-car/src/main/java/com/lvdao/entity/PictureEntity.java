package com.lvdao.entity;

import java.io.Serializable;

public class PictureEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5833550943648225642L;
	
	private String picId;
	private String picRealName;
	private String picUrl;
	private String picSize;
	private String picType;
	private String picName;

	/**
	 * @return the picId
	 */
	public String getPicId() {
		return picId;
	}

	/**
	 * @param picId the picId to set
	 */
	public void setPicId(String picId) {
		this.picId = picId;
	}

	/**
	 * @return the picRealName
	 */
	public String getPicRealName() {
		return picRealName;
	}

	/**
	 * @param picRealName the picRealName to set
	 */
	public void setPicRealName(String picRealName) {
		this.picRealName = picRealName;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the picSize
	 */
	public String getPicSize() {
		return picSize;
	}

	/**
	 * @param picSize the picSize to set
	 */
	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}

	/**
	 * @return the picType
	 */
	public String getPicType() {
		return picType;
	}

	/**
	 * @param picType the picType to set
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}

	/**
	 * @return the picName
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * @param picName the picName to set
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}
	
}
