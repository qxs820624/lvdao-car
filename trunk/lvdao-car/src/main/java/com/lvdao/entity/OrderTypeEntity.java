package com.lvdao.entity;


public class OrderTypeEntity {

	private String orderTypeId;
	private String orderTypeName;
	
	public OrderTypeEntity(String orderTypeId, String orderTypeName) {
		super();
		this.orderTypeId = orderTypeId;
		this.orderTypeName = orderTypeName;
	}
	public String getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
}
