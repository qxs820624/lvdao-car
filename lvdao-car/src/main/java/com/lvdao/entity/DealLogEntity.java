package com.lvdao.entity;

import java.io.Serializable;
/**
 * 这张表主要存放交易日志 充值 扣款 转让/赠送金额 t_deal_log
 * date: 2016年9月6日 下午6:59:41 
 * @author wangyu
 */
public class DealLogEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1946168182467501357L;

	private String dealLogId;
    private String userId;//用户ID
    private String userName;//用户姓名
    private String logType;//登录类型 0充值，1支付，2扣款，3会员升级，4用户提现，5分红点击头像的时候）
    private String logAmount;//消耗的金额
    private String targetUserId;//转入的用户信息
    private String targetUserName;//转入的用户名
    private String logDesc;//日志描述
    private String logIp;//Log IP
    private String logMacAddress;//Log Mac Address
    private String dataSyncSql;
    private String dataSyncInfo;
    
	public String getDealLogId() {
		return dealLogId;
	}

	public void setDealLogId(String dealLogId) {
		this.dealLogId = dealLogId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getTargetUserName() {
		return targetUserName;
	}

	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogMacAddress() {
		return logMacAddress;
	}

	public void setLogMacAddress(String logMacAddress) {
		this.logMacAddress = logMacAddress;
	}

	public String getDataSyncSql() {
		return dataSyncSql;
	}

	public void setDataSyncSql(String dataSyncSql) {
		this.dataSyncSql = dataSyncSql;
	}

	public String getDataSyncInfo() {
		return dataSyncInfo;
	}

	public void setDataSyncInfo(String dataSyncInfo) {
		this.dataSyncInfo = dataSyncInfo;
	}

	public String getLogAmount() {
		return logAmount;
	}

	public void setLogAmount(String logAmount) {
		this.logAmount = logAmount;
	}
    
}