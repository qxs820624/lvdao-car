package com.lvdao.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserMessageEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1201578356259377336L;

	private String userId;

    private String userName;

    private String messageId;

    private String messageCategoryId;

    private String messageAttrIds;

    private Date startTime;

    private Date endTime;

    private String address;

    private String cityName;

    private String regionId;

    private String longitude;

    private String latitude;

    private String receiveTime;

    private Integer genderNeed;

    private BigDecimal price;

    private Integer messageType;

    private Integer hiddenStatus;

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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessageCategoryId() {
        return messageCategoryId;
    }

    public void setMessageCategoryId(String messageCategoryId) {
        this.messageCategoryId = messageCategoryId == null ? null : messageCategoryId.trim();
    }

    public String getMessageAttrIds() {
        return messageAttrIds;
    }

    public void setMessageAttrIds(String messageAttrIds) {
        this.messageAttrIds = messageAttrIds == null ? null : messageAttrIds.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime == null ? null : receiveTime.trim();
    }

    public Integer getGenderNeed() {
        return genderNeed;
    }

    public void setGenderNeed(Integer genderNeed) {
        this.genderNeed = genderNeed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getHiddenStatus() {
        return hiddenStatus;
    }

    public void setHiddenStatus(Integer hiddenStatus) {
        this.hiddenStatus = hiddenStatus;
    }

}