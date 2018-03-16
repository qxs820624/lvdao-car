package com.lvdao.entity;


import java.io.Serializable;
import java.util.Date;

public class UserOrderEntity extends BaseEntity  implements Serializable {
	
	private static final long serialVersionUID = 6196335276694109460L;

    private String userId;

    private String userName;

    private String vehicleNo;

    private Integer paidStatus;

    private String orderMoney;

    private String transactionId;

    private String orderId;

    private String routeId;

    private String ossRouteUrl;
    
    
    private String orderDistance;

    private String remark;
    
    private Date  vipOrderTime;
    
    private String orderType;
    
    private String paymentMethod;
    
    private String orderCommentTypeId;
    
    private String orderCommentContent;
    
    private Date orderCommentTime;
    
    private String orderCommentPicGroupId;
    
    private String driverOrderId;
    
    private String passengerOrderId;
    
    private String addMethod;
    
    private String otherPersonMobile;
    
    private String otherPersonRealName;//	页面需求,没有字段

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

	public synchronized String getVehicleNo() {
		return vehicleNo;
	}

	public synchronized void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public synchronized Integer getPaidStatus() {
		return paidStatus;
	}

	public synchronized void setPaidStatus(Integer paidStatus) {
		this.paidStatus = paidStatus;
	}

	public synchronized String getOrderMoney() {
		return orderMoney;
	}

	public synchronized void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	public synchronized String getTransactionId() {
		return transactionId;
	}

	public synchronized void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public synchronized String getOrderId() {
		return orderId;
	}

	public synchronized void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public synchronized String getRouteId() {
		return routeId;
	}

	public synchronized void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public synchronized String getOssRouteUrl() {
		return ossRouteUrl;
	}

	public synchronized void setOssRouteUrl(String ossRouteUrl) {
		this.ossRouteUrl = ossRouteUrl;
	}

	public synchronized String getOrderDistance() {
		return orderDistance;
	}

	public synchronized void setOrderDistance(String orderDistance) {
		this.orderDistance = orderDistance;
	}

	public synchronized String getRemark() {
		return remark;
	}

	public synchronized void setRemark(String remark) {
		this.remark = remark;
	}

	public synchronized Date getVipOrderTime() {
		return vipOrderTime;
	}

	public synchronized void setVipOrderTime(Date vipOrderTime) {
		this.vipOrderTime = vipOrderTime;
	}

	public synchronized String getOrderType() {
		return orderType;
	}

	public synchronized void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public synchronized String getPaymentMethod() {
		return paymentMethod;
	}

	public synchronized void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public synchronized String getOrderCommentTypeId() {
		return orderCommentTypeId;
	}

	public synchronized void setOrderCommentTypeId(String orderCommentTypeId) {
		this.orderCommentTypeId = orderCommentTypeId;
	}

	public synchronized String getOrderCommentContent() {
		return orderCommentContent;
	}

	public synchronized void setOrderCommentContent(String orderCommentContent) {
		this.orderCommentContent = orderCommentContent;
	}

	public synchronized Date getOrderCommentTime() {
		return orderCommentTime;
	}

	public synchronized void setOrderCommentTime(Date orderCommentTime) {
		this.orderCommentTime = orderCommentTime;
	}

	public synchronized String getOrderCommentPicGroupId() {
		return orderCommentPicGroupId;
	}

	public synchronized void setOrderCommentPicGroupId(String orderCommentPicGroupId) {
		this.orderCommentPicGroupId = orderCommentPicGroupId;
	}

	public synchronized String getDriverOrderId() {
		return driverOrderId;
	}

	public synchronized void setDriverOrderId(String driverOrderId) {
		this.driverOrderId = driverOrderId;
	}

	public synchronized String getPassengerOrderId() {
		return passengerOrderId;
	}

	public synchronized void setPassengerOrderId(String passengerOrderId) {
		this.passengerOrderId = passengerOrderId;
	}

	public synchronized String getAddMethod() {
		return addMethod;
	}

	public synchronized void setAddMethod(String addMethod) {
		this.addMethod = addMethod;
	}

	public synchronized String getOtherPersonMobile() {
		return otherPersonMobile;
	}

	public synchronized void setOtherPersonMobile(String otherPersonMobile) {
		this.otherPersonMobile = otherPersonMobile;
	}

	public synchronized String getOtherPersonRealName() {
		return otherPersonRealName;
	}

	public synchronized void setOtherPersonRealName(String otherPersonRealName) {
		this.otherPersonRealName = otherPersonRealName;
	}
 
	
}