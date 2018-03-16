package com.lvdao.web.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 消息vo
 * 
 * @author hx
 * @since 2016/10/12 17:31
 */
public class MessageVO {
	private String messageId;// 站内信ID MESSAGE_ID
	private String messageTitle;// 站内信标题 MESSAGE_TITLE
	private String userId;// 用户名ID USER_ID
	private String userName;// 用户名 USER_NAME
	private String messageContent;// MESSAGE_CONTENT 站内信内容
	private String startTime;// 预约开始时间
	private String endTime;// 预约结束时间
	private String regionId;// 区域ID
	private String receiveTime;// 记录该条求的消息 接收 应的消息的次数
	private String genderNeed;// 性别需求
	private List<String> messageCategoryIds;
	private BigDecimal price;// 价格

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
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

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getGenderNeed() {
		return genderNeed;
	}

	public void setGenderNeed(String genderNeed) {
		this.genderNeed = genderNeed;
	}

	public List<String> getMessageCategoryIds() {
		return messageCategoryIds;
	}

	public void setMessageCategoryIds(List<String> messageCategoryIds) {
		this.messageCategoryIds = messageCategoryIds;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
