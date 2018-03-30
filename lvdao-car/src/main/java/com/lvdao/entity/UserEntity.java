package com.lvdao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息 t_user
 * date: 2016年9月6日 下午8:26:37 
 * @author wangyu
 */
public class UserEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1261260634785481768L;
	private String userId;//用户名
	private String wxId;//微信ID
    private String userName;// 用户名
    private String userGender;//用户性别：1，男 0 女
    private String userRealName;//用户真实姓名
    private String userCenterCode;//集团编码
    private String userCenterName;//集团名称
    private String easemobName;//环信名称
    private String userIdNum;//用户身份证号码
    private String userDesc;//用户描述
    private String userInvitationCode;//用户邀请码
    private String userMobile;//用户手机
    private boolean userMobileValidation;//手机号是否验证 0未验证1已验证
    private boolean userSkillValidation;//技能认证 0未验证1已验证
    private boolean userIdentityValidation;//身份认证0未验证1已验证
    private String userCountry;//用户所在国家
    private String userProvince;//用户所在省份
    private String userCity;//用户所在城市
    private String userDistrict;//城市所在的区县
    private String userStreet;//用户所在街道
    private String userZone;//用户所在小区
    private String userAddress;//用户所在楼幢门牌号等
    private String userLongitude;//用户所在经度
    private String userLatitude;//用户所在纬度
    private String userEmail;//用户邮箱
    private boolean userEmailValidation;//用户邮箱绑定状态
    private String userPassword;//用户密码
    private String userPaymentPassword;//用户支付密码
    private String userDegreeId;//用户等级ID
    private String userDegreeName;//用户等级名称
    private String userParentId;//用户推荐人ID
    private String userParentName;//推荐人用户名
    private String userBankFullName;//用户绑定的银行卡所在银行全称 如苏州园区支行'
    private String userBankName;//用户银行卡的银行名（中国工商银行）
    private String userBankCardNo;//用户银行卡号
    private String userBankAccountName;//银行卡持有人名称
    private String token;//token 存于mongo redis 用于作为app用户的唯一标识
    private String userPicAmonunt;//用户图片的数量
    private String qq;//用户QQ号
    private int userAge;//用户年龄
    private Date userBirthday;//用户生日
    private double distance; //用于存储两个用户之间的距离（不存库，用于返回app端）
    private String picUrl;	//用户头像（冗余属性，用于查询和返回）
    private boolean fingerprintSwitch;//指纹开关
    private boolean gestureSwitch;//手势开关
    private String gesturePassword;//手势密码
    private int userType;//用户等级	                          
    private String roleId;//增加两个显示字段，不在数据库字段
    private String roleName;
    //private String picUrl;
    private String userStatus; //是否激活
    
    private Short userBankValidation;

    private String userAlipayAccount;

    private String userWechatAccount;
    
    
    public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
    
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserGender() {
		return userGender;
	}
	
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public synchronized String getUserStatus() {
		return userStatus;
	}

	public synchronized void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getWxId() {
		return wxId;
	}
	
	public void setWxId(String wxId) {
		this.wxId = wxId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserRealName() {
		return userRealName;
	}
	
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	public String getUserIdNum() {
		return userIdNum;
	}
	
	public void setUserIdNum(String userIdNum) {
		this.userIdNum = userIdNum;
	}
	
	public String getUserDesc() {
		return userDesc;
	}
	
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	
	public String getUserInvitationCode() {
		return userInvitationCode;
	}

	public void setUserInvitationCode(String userInvitationCode) {
		this.userInvitationCode = userInvitationCode;
	}

	public String getUserMobile() {
		return userMobile;
	}
	
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	public boolean getUserMobileValidation() {
		return userMobileValidation;
	}
	
	public void setUserMobileValidation(boolean userMobileValidation) {
		this.userMobileValidation = userMobileValidation;
	}
	
	public String getUserCountry() {
		return userCountry;
	}
	
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	
	public String getUserProvince() {
		return userProvince;
	}
	
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}
	
	public String getUserDistrict() {
		return userDistrict;
	}
	
	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}
	
	public String getUserStreet() {
		return userStreet;
	}
	
	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}
	
	public String getUserZone() {
		return userZone;
	}
	
	public void setUserZone(String userZone) {
		this.userZone = userZone;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getUserLongitude() {
		return userLongitude;
	}
	
	public void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}
	
	public String getUserLatitude() {
		return userLatitude;
	}
	
	public void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserPaymentPassword() {
		return userPaymentPassword;
	}
	
	public void setUserPaymentPassword(String userPaymentPassword) {
		this.userPaymentPassword = userPaymentPassword;
	}
	
	public String getUserDegreeId() {
		return userDegreeId;
	}
	
	public void setUserDegreeId(String userDegreeId) {
		this.userDegreeId = userDegreeId;
	}
	
	public String getUserDegreeName() {
		return userDegreeName;
	}
	
	public void setUserDegreeName(String userDegreeName) {
		this.userDegreeName = userDegreeName;
	}
	
	public String getUserParentId() {
		return userParentId;
	}
	
	public void setUserParentId(String userParentId) {
		this.userParentId = userParentId;
	}
	
	public String getUserParentName() {
		return userParentName;
	}
	
	public void setUserParentName(String userParentName) {
		this.userParentName = userParentName;
	}
	
	public String getUserBankFullName() {
		return userBankFullName;
	}
	
	public void setUserBankFullName(String userBankFullName) {
		this.userBankFullName = userBankFullName;
	}
	
	public String getUserBankName() {
		return userBankName;
	}
	
	public void setUserBankName(String userBankName) {
		this.userBankName = userBankName;
	}
	
	public String getUserBankCardNo() {
		return userBankCardNo;
	}
	
	public void setUserBankCardNo(String userBankCardNo) {
		this.userBankCardNo = userBankCardNo;
	}
	
	public String getUserCity() {
		return userCity;
	}
	
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	
	public boolean getUserEmailValidation() {
		return userEmailValidation;
	}
	
	public void setUserEmailValidation(boolean userEmailValidation) {
		this.userEmailValidation = userEmailValidation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserPicAmonunt() {
		return userPicAmonunt;
	}

	public void setUserPicAmonunt(String userPicAmonunt) {
		this.userPicAmonunt = userPicAmonunt;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isUserSkillValidation() {
		return userSkillValidation;
	}

	public void setUserSkillValidation(boolean userSkillValidation) {
		this.userSkillValidation = userSkillValidation;
	}

	public boolean isUserIdentityValidation() {
		return userIdentityValidation;
	}

	public void setUserIdentityValidation(boolean userIdentityValidation) {
		this.userIdentityValidation = userIdentityValidation;
	}

	public String getUserBankAccountName() {
		return userBankAccountName;
	}

	public void setUserBankAccountName(String userBankAccountName) {
		this.userBankAccountName = userBankAccountName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public synchronized boolean getFingerprintSwitch() {
		return fingerprintSwitch;
	}

	public synchronized void setFingerprintSwitch(boolean fingerprintSwitch) {
		this.fingerprintSwitch = fingerprintSwitch;
	}

	public synchronized boolean getGestureSwitch() {
		return gestureSwitch;
	}

	public synchronized void setGestureSwitch(boolean gestureSwitch) {
		this.gestureSwitch = gestureSwitch;
	}

	public synchronized String getGesturePassword() {
		return gesturePassword;
	}

	public synchronized void setGesturePassword(String gesturePassword) {
		this.gesturePassword = gesturePassword;
	}

	public String getEasemobName() {
		return easemobName;
	}

	public void setEasemobName(String easemobName) {
		this.easemobName = easemobName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Short getUserBankValidation() {
		return userBankValidation;
	}

	public void setUserBankValidation(Short userBankValidation) {
		this.userBankValidation = userBankValidation;
	}

	public String getUserAlipayAccount() {
		return userAlipayAccount;
	}

	public void setUserAlipayAccount(String userAlipayAccount) {
		this.userAlipayAccount = userAlipayAccount;
	}

	public String getUserWechatAccount() {
		return userWechatAccount;
	}

	public void setUserWechatAccount(String userWechatAccount) {
		this.userWechatAccount = userWechatAccount;
	}

	public synchronized String getUserCenterCode() {
		return userCenterCode;
	}

	public synchronized void setUserCenterCode(String userCenterCode) {
		this.userCenterCode = userCenterCode;
	}

	public synchronized String getUserCenterName() {
		return userCenterName;
	}

	public synchronized void setUserCenterName(String userCenterName) {
		this.userCenterName = userCenterName;
	}
	
	
	
}