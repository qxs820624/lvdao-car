package com.lvdao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserWithdrawEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8316874238706650071L;

	private String userId; 
	private String userName;	//用户名
	private String parentUserId;
	private String parentUserName;
	private String accountTypeId;
	private String accountTypeName;
	private String withdrawAccountType; // 银行名称
	private String withdrawAccount; // 提现账户
	private String withdrawMoney; // 提现金额
	private String withdrawProcedure; // 提现手续费用
	private String withdrawTotal; // 提现总额
	private String accountBalance;//账户余额
	private String withdrawAccountName; // 开户人名称
	private String withdrawBankFullName; // 开户行全称
	private String comment; // 备注
	private String orderSn;
	
	public synchronized String getOrderSn() {
		return orderSn;
	}

	public synchronized void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getWithdrawAccountType() {
		return withdrawAccountType;
	}

	public void setWithdrawAccountType(String withdrawAccountType) {
		this.withdrawAccountType = withdrawAccountType;
	}

	public String getWithdrawAccount() {
		return withdrawAccount;
	}

	public void setWithdrawAccount(String withdrawAccount) {
		this.withdrawAccount = withdrawAccount;
	}

	public String getWithdrawMoney() {
		return withdrawMoney;
	}

	public void setWithdrawMoney(String withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}

	public String getWithdrawProcedure() {
		return withdrawProcedure;
	}

	public void setWithdrawProcedure(String withdrawProcedure) {
		this.withdrawProcedure = withdrawProcedure;
	}

	public String getWithdrawTotal() {
		return withdrawTotal;
	}

	public void setWithdrawTotal(String withdrawTotal) {
		this.withdrawTotal = withdrawTotal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWithdrawAccountName() {
		return withdrawAccountName;
	}

	public void setWithdrawAccountName(String withdrawAccountName) {
		this.withdrawAccountName = withdrawAccountName;
	}

	public String getWithdrawBankFullName() {
		return withdrawBankFullName;
	}

	public void setWithdrawBankFullName(String withdrawBankFullName) {
		this.withdrawBankFullName = withdrawBankFullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public synchronized String getParentUserId() {
		return parentUserId;
	}

	public synchronized void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId;
	}

	public synchronized String getParentUserName() {
		return parentUserName;
	}

	public synchronized void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}

	public synchronized String getAccountBalance() {
		return accountBalance;
	}

	public synchronized void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * 手动设置总金额
	 */
	public void initWithdrawTotal(){
		this.withdrawTotal = new BigDecimal(this.withdrawMoney).add(new BigDecimal(this.withdrawProcedure)).toString();
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
	
}
