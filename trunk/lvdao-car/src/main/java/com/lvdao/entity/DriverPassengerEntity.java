package com.lvdao.entity;

public class DriverPassengerEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8900366485993571139L;

	private String driverOrderId;

	private String passengerOrderId;

	private String originalPlaceName;

	private String originalLongtitude;

	private String originalLatitude;

	private String targetPlaceName;

	private String targetLongtitude;

	private String targetLatitude;
	
	private String orderDistance;
	
	private String orderAppointTime;
	
	private String orderDesc;
	
	private String orderStatus;

	public String getDriverOrderId() {
		return driverOrderId;
	}

	public void setDriverOrderId(String driverOrderId) {
		this.driverOrderId = driverOrderId;
	}

	public String getPassengerOrderId() {
		return passengerOrderId;
	}

	public void setPassengerOrderId(String passengerOrderId) {
		this.passengerOrderId = passengerOrderId;
	}

	public String getOriginalPlaceName() {
		return originalPlaceName;
	}

	public void setOriginalPlaceName(String originalPlaceName) {
		this.originalPlaceName = originalPlaceName;
	}

	public String getOriginalLongtitude() {
		return originalLongtitude;
	}

	public void setOriginalLongtitude(String originalLongtitude) {
		this.originalLongtitude = originalLongtitude;
	}

	public String getOriginalLatitude() {
		return originalLatitude;
	}

	public void setOriginalLatitude(String originalLatitude) {
		this.originalLatitude = originalLatitude;
	}

	public String getTargetPlaceName() {
		return targetPlaceName;
	}

	public void setTargetPlaceName(String targetPlaceName) {
		this.targetPlaceName = targetPlaceName;
	}

	public String getTargetLongtitude() {
		return targetLongtitude;
	}

	public void setTargetLongtitude(String targetLongtitude) {
		this.targetLongtitude = targetLongtitude;
	}

	public String getTargetLatitude() {
		return targetLatitude;
	}

	public void setTargetLatitude(String targetLatitude) {
		this.targetLatitude = targetLatitude;
	}

	public String getOrderDistance() {
		return orderDistance;
	}

	public void setOrderDistance(String orderDistance) {
		this.orderDistance = orderDistance;
	}

	public String getOrderAppointTime() {
		return orderAppointTime;
	}

	public void setOrderAppointTime(String orderAppointTime) {
		this.orderAppointTime = orderAppointTime;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
