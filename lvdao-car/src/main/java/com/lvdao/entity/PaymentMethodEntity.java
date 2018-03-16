package com.lvdao.entity;


public class PaymentMethodEntity {
	
	private String paymentMethodId;
	private String paymentMethodName;
	
	public PaymentMethodEntity(String paymentMethodId, String paymentMethodName) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.paymentMethodName = paymentMethodName;
	}
	public synchronized String getPaymentMethodId() {
		return paymentMethodId;
	}
	public synchronized void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public synchronized String getPaymentMethodName() {
		return paymentMethodName;
	}
	public synchronized void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}
}
