package com.lvdao.entity;

import java.io.Serializable;

/**
 * 结算表
 * 
 * @author hexiang
 * @since 2017-12-05 13:51
 */
public class SettlementEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1657550894093653810L;
	private String userType;//会员等级
	private String bonusNum;//会员人数（当天分红）
	private String bonusTotalAmount;//分红总金额
	private String bonusAmount;// 每人分的金额（计划）
	private String bonusActualAmount;//实际每人分红金额
	private String bonusDate;//分红日期
	private String bonusStatus;//分红状态 0 未分红 1已分红
	private String bonusTotalActualAmount;
	private String bonusDivide;//分红比例
	private String totalProfit;//收益
	
	public synchronized String getTotalProfit() {
		return totalProfit;
	}
	public synchronized void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}
	public synchronized String getBonusDivide() {
		return bonusDivide;
	}
	public synchronized void setBonusDivide(String bonusDivide) {
		this.bonusDivide = bonusDivide;
	}
	public synchronized String getBonusTotalActualAmount() {
		return bonusTotalActualAmount;
	}
	public synchronized void setBonusTotalActualAmount(String bonusTotalActualAmount) {
		this.bonusTotalActualAmount = bonusTotalActualAmount;
	}
	public synchronized String getUserType() {
		return userType;
	}
	public synchronized void setUserType(String userType) {
		this.userType = userType;
	}
	public synchronized String getBonusNum() {
		return bonusNum;
	}
	public synchronized void setBonusNum(String bonusNum) {
		this.bonusNum = bonusNum;
	}
	public synchronized String getBonusTotalAmount() {
		return bonusTotalAmount;
	}
	public synchronized void setBonusTotalAmount(String bonusTotalAmount) {
		this.bonusTotalAmount = bonusTotalAmount;
	}
	public synchronized String getBonusAmount() {
		return bonusAmount;
	}
	public synchronized void setBonusAmount(String bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public synchronized String getBonusActualAmount() {
		return bonusActualAmount;
	}
	public synchronized void setBonusActualAmount(String bonusActualAmount) {
		this.bonusActualAmount = bonusActualAmount;
	}
	public synchronized String getBonusDate() {
		return bonusDate;
	}
	public synchronized void setBonusDate(String bonusDate) {
		this.bonusDate = bonusDate;
	}
	public synchronized String getBonusStatus() {
		return bonusStatus;
	}
	public synchronized void setBonusStatus(String bonusStatus) {
		this.bonusStatus = bonusStatus;
	}
	
}