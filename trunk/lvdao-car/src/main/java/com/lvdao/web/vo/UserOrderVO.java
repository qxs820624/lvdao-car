package com.lvdao.web.vo;

import java.util.Date;

public class UserOrderVO {
	private String id;
	private String userId; // 申请人Id
	private String userName;// 申请人name
	private String userRealName;// 申请人name
	private String orderId;
	private String orderMoney; // 申请的加盟单所需金额
	private String addMethod;// 申请方式:0 自主申请,1代人申请
	private Integer paidStatus;	//	0待通过、1已通过、2被驳回
	private String remark;// 存凭证图片路径
	private String needAmount; // 加盟单应打款金额
	private boolean userMobileValidation;// 手机号是否验证 0未验证1已验证
	private boolean userIdentityValidation;// 身份认证0未验证1已验证
	private String applyUserId; // 被申请人id
	private String applyUserName;// 被申请人name
	private String applyUserRealName;// 被申请人name
	private String applyUserMobile;// 被申请人name
	private Date createTime;	//	申请日期即订单创建日期
	private String payMethod; //	支付方式
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
	public synchronized String getOrderId() {
		return orderId;
	}
	public synchronized void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public synchronized String getOrderMoney() {
		return orderMoney;
	}
	public synchronized void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public synchronized String getAddMethod() {
		return addMethod;
	}
	public synchronized void setAddMethod(String addMethod) {
		this.addMethod = addMethod;
	}
	public synchronized Integer getPaidStatus() {
		return paidStatus;
	}
	public synchronized void setPaidStatus(Integer paidStatus) {
		this.paidStatus = paidStatus;
	}
	public synchronized String getRemark() {
		return remark;
	}
	public synchronized void setRemark(String remark) {
		this.remark = remark;
	}
	public synchronized String getNeedAmount() {
		return needAmount;
	}
	public synchronized void setNeedAmount(String needAmount) {
		this.needAmount = needAmount;
	}
	public synchronized boolean isUserMobileValidation() {
		return userMobileValidation;
	}
	public synchronized void setUserMobileValidation(boolean userMobileValidation) {
		this.userMobileValidation = userMobileValidation;
	}
	public synchronized boolean isUserIdentityValidation() {
		return userIdentityValidation;
	}
	public synchronized void setUserIdentityValidation(boolean userIdentityValidation) {
		this.userIdentityValidation = userIdentityValidation;
	}
	public synchronized String getApplyUserId() {
		return applyUserId;
	}
	public synchronized void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public synchronized String getApplyUserName() {
		return applyUserName;
	}
	public synchronized void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public synchronized String getApplyUserRealName() {
		return applyUserRealName;
	}
	public synchronized void setApplyUserRealName(String applyUserRealName) {
		this.applyUserRealName = applyUserRealName;
	}
	public synchronized String getApplyUserMobile() {
		return applyUserMobile;
	}
	public synchronized void setApplyUserMobile(String applyUserMobile) {
		this.applyUserMobile = applyUserMobile;
	}
	public synchronized Date getCreateTime() {
		return createTime;
	}
	public synchronized void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public synchronized String getPayMethod() {
		return payMethod;
	}
	public synchronized void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public synchronized String getId() {
		return id;
	}
	public synchronized void setId(String id) {
		this.id = id;
	}
	
}