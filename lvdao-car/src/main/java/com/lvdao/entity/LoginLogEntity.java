package com.lvdao.entity;

import java.io.Serializable;

/**
 * 这张表主要用来存放注册登录日志  t_login_log
 * date: 2016年9月6日 下午7:05:10 
 * @author wangyu
 */
public class LoginLogEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1946168182467501357L;
	
	private String 	loginLogId;
    private String  userId;//用户ID
    private String  userName;//用户名
    private String  logType;//日志类型0 注册，1登录
    private String  logIp;//登录IP
    private String  logMacAddress;//登录的Mac地址
    private String  logLongitude;//登录经度
    private String  logLatitude;//登录的纬度
    private String  logCountry;//登录的国家
    private String  logPrince;//登录的省份
    private String  logCity;//登录的城市
    private String  logDistrict;//登录所在的城市的区
    private String  logStreet;//登录所在的街道
    private String  logZone;//用户所在小区
    private String  logDesc;//Log Desc

	public String getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(String loginLogId) {
		this.loginLogId = loginLogId;
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

	public String getLogMacAddress() {
		return logMacAddress;
	}

	public void setLogMacAddress(String logMacAddress) {
		this.logMacAddress = logMacAddress;
	}

	public String getLogLongitude() {
		return logLongitude;
	}

	public void setLogLongitude(String logLongitude) {
		this.logLongitude = logLongitude;
	}

	public String getLogLatitude() {
		return logLatitude;
	}

	public void setLogLatitude(String logLatitude) {
		this.logLatitude = logLatitude;
	}

	public String getLogCountry() {
		return logCountry;
	}

	public void setLogCountry(String logCountry) {
		this.logCountry = logCountry;
	}

	public String getLogPrince() {
		return logPrince;
	}

	public void setLogPrince(String logPrince) {
		this.logPrince = logPrince;
	}

	public String getLogCity() {
		return logCity;
	}

	public void setLogCity(String logCity) {
		this.logCity = logCity;
	}

	public String getLogDistrict() {
		return logDistrict;
	}

	public void setLogDistrict(String logDistrict) {
		this.logDistrict = logDistrict;
	}

	public String getLogStreet() {
		return logStreet;
	}

	public void setLogStreet(String logStreet) {
		this.logStreet = logStreet;
	}

	public String getLogZone() {
		return logZone;
	}

	public void setLogZone(String logZone) {
		this.logZone = logZone;
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
    
}