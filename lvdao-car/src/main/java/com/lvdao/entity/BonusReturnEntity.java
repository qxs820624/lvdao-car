package com.lvdao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖金返还实体类
 * 
 * @author zhxihu2008
 * @since 2018-03-09 12:47
 */
public class BonusReturnEntity implements Serializable {

	private static final long serialVersionUID = 1510931455394612042L;

	private String id;
	
	/**
	 * 订单ID
	 */
	private String orderId;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 订单金额
	 */
	private String orderAmount;
	
	/**
	 * 已返还的金额
	 */
	private String returnAmount;
	
	/**
	 * 返还的总次数
	 */
	private String returnTime;
	
	/**
	 * 已返还的总比例
	 */
	private String returnPercent;
	
	/**
	 * 订单创建的时间
	 */
	private Date orderTime;
	
	/**
	 * 最后一次返还的时间
	 */
	private Date createTime;

	public synchronized String getId() {
		return id;
	}

	public synchronized void setId(String id) {
		this.id = id;
	}

	public synchronized String getOrderId() {
		return orderId;
	}

	public synchronized void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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

	public synchronized String getOrderAmount() {
		return orderAmount;
	}

	public synchronized void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public synchronized String getReturnAmount() {
		return returnAmount;
	}

	public synchronized void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

	public synchronized String getReturnTime() {
		return returnTime;
	}

	public synchronized void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public synchronized String getReturnPercent() {
		return returnPercent;
	}

	public synchronized void setReturnPercent(String returnPercent) {
		this.returnPercent = returnPercent;
	}
	
	public synchronized Date getOrderTime() {
		return orderTime;
	}

	public synchronized void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public synchronized Date getCreateTime() {
		return createTime;
	}

	public synchronized void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
