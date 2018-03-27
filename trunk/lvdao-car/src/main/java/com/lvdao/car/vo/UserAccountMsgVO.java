package com.lvdao.car.vo;

import java.util.Date;

/**
 * 消息vo
 * 
 * @author hx
 * @since 2016/10/12 17:31
 */
public class UserAccountMsgVO {
	private String accountTypeId;
	private String accountTypeName;
	private String amount;
	private Date createTime;
	public synchronized String getAccountTypeId() {
		return accountTypeId;
	}
	public synchronized void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public synchronized String getAccountTypeName() {
		return accountTypeName;
	}
	public synchronized void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public synchronized String getAmount() {
		return amount;
	}
	public synchronized void setAmount(String amount) {
		this.amount = amount;
	}
	public synchronized Date getCreateTime() {
		return createTime;
	}
	public synchronized void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}
