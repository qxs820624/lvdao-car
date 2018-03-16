package com.lvdao.entity;

import java.io.Serializable;
/**
 * 账号信息服务  t_account
 * date: 2016年9月6日 下午8:10:29 
 * @author wangyu
 */
public class AccountEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 299076128230282731L;
	private String accountId;
    private String accountName;
    private String accountType;
    private String bonusLimitAmount;
    
	public synchronized String getAccountId() {
		return accountId;
	}
	
	public synchronized void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public synchronized String getAccountName() {
		return accountName;
	}
	
	public synchronized void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public synchronized String getAccountType() {
		return accountType;
	}
	
	public synchronized void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public synchronized String getBonusLimitAmount() {
		return bonusLimitAmount;
	}
	
	public synchronized void setBonusLimitAmount(String bonusLimitAmount) {
		this.bonusLimitAmount = bonusLimitAmount;
	}

}