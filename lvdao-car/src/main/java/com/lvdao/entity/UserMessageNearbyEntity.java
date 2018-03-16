package com.lvdao.entity;

import java.io.Serializable;

/**
 * 用户附近消息
 * 
 * @author zhxihu2008
 * @since 2017-02-22 19:03
 */
public class UserMessageNearbyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2289296397417845506L;
	
	private String userId;
	private String userName;
	private String userRealName;
	private String userGender;
	private String mobile;
	private String messageId;
	private String messageTitle;
	private String messageContent;
	private String userImage;
	private String messageImages;
	private String videoUrl;
	private String width;
	private String height;
	private boolean skillValidation; //技能认证
	private boolean identityValidation; //身份认证
	private String userLongitude;
	private String userLatitude;
	private String distance;
	private String userAddress;
	private String createTime;
	private String userBankFullName;
    private String userBankName;
    private String userBankCardNo;
    private String userBankAccountName;
	private String messagePicUrlList;
	private boolean active;

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

	public synchronized String getUserRealName() {
		return userRealName;
	}

	public synchronized void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public synchronized String getUserGender() {
		return userGender;
	}

	public synchronized void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public synchronized String getMobile() {
		return mobile;
	}

	public synchronized void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public synchronized String getMessageId() {
		return messageId;
	}

	public synchronized void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public synchronized String getMessageTitle() {
		return messageTitle;
	}

	public synchronized void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public synchronized String getMessageContent() {
		return messageContent;
	}

	public synchronized void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public synchronized String getUserImage() {
		return userImage;
	}

	public synchronized void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public synchronized String getMessageImages() {
		return messageImages;
	}

	public synchronized void setMessageImages(String messageImages) {
		this.messageImages = messageImages;
	}

	public synchronized String getVideoUrl() {
		return videoUrl;
	}

	public synchronized void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public synchronized String getWidth() {
		return width;
	}

	public synchronized void setWidth(String width) {
		this.width = width;
	}

	public synchronized String getHeight() {
		return height;
	}

	public synchronized void setHeight(String height) {
		this.height = height;
	}

	public synchronized boolean isSkillValidation() {
		return skillValidation;
	}

	public synchronized void setSkillValidation(boolean skillValidation) {
		this.skillValidation = skillValidation;
	}

	public synchronized boolean isIdentityValidation() {
		return identityValidation;
	}

	public synchronized void setIdentityValidation(boolean identityValidation) {
		this.identityValidation = identityValidation;
	}

	public synchronized String getUserLongitude() {
		return userLongitude;
	}

	public synchronized void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}

	public synchronized String getUserLatitude() {
		return userLatitude;
	}

	public synchronized void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}

	public synchronized String getDistance() {
		return distance;
	}

	public synchronized void setDistance(String distance) {
		this.distance = distance;
	}

	public synchronized String getUserAddress() {
		return userAddress;
	}

	public synchronized void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public synchronized String getCreateTime() {
		return createTime;
	}

	public synchronized void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public synchronized String getUserBankFullName() {
		return userBankFullName;
	}

	public synchronized void setUserBankFullName(String userBankFullName) {
		this.userBankFullName = userBankFullName;
	}

	public synchronized String getUserBankName() {
		return userBankName;
	}

	public synchronized void setUserBankName(String userBankName) {
		this.userBankName = userBankName;
	}

	public synchronized String getUserBankCardNo() {
		return userBankCardNo;
	}

	public synchronized void setUserBankCardNo(String userBankCardNo) {
		this.userBankCardNo = userBankCardNo;
	}

	public synchronized String getUserBankAccountName() {
		return userBankAccountName;
	}

	public synchronized void setUserBankAccountName(String userBankAccountName) {
		this.userBankAccountName = userBankAccountName;
	}

	public synchronized String getMessagePicUrlList() {
		return messagePicUrlList;
	}

	public synchronized void setMessagePicUrlList(String messagePicUrlList) {
		this.messagePicUrlList = messagePicUrlList;
	}

	public synchronized boolean isActive() {
		return active;
	}

	public synchronized void setActive(boolean active) {
		this.active = active;
	}
	
}
