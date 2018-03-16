package com.lvdao.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 充值记录
 * 升级会员
 * @author Administrator
 *
 */
public class RechargeEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8469667049114672035L;

	private String id;

    private String userName;

    private String userId;

    private String rechargeAccountType;

    private String rechargeAccount;

    private String rechargeMoney;

    private Integer rechargeWay;

    private String rechargeOrderid;

    private String rechargeTransactionid;

    private String accountBalance;

    private String comment;

    private Integer status;

    private Integer active;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private String updateUserId;

    private String updateUserName;

    private Date updateTime;

    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRechargeAccountType() {
        return rechargeAccountType;
    }

    public void setRechargeAccountType(String rechargeAccountType) {
        this.rechargeAccountType = rechargeAccountType == null ? null : rechargeAccountType.trim();
    }

    public String getRechargeAccount() {
        return rechargeAccount;
    }

    public void setRechargeAccount(String rechargeAccount) {
        this.rechargeAccount = rechargeAccount == null ? null : rechargeAccount.trim();
    }

    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney == null ? null : rechargeMoney.trim();
    }

    public Integer getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(Integer rechargeWay) {
        this.rechargeWay = rechargeWay;
    }

    public String getRechargeOrderid() {
        return rechargeOrderid;
    }

    public void setRechargeOrderid(String rechargeOrderid) {
        this.rechargeOrderid = rechargeOrderid == null ? null : rechargeOrderid.trim();
    }

    public String getRechargeTransactionid() {
        return rechargeTransactionid;
    }

    public void setRechargeTransactionid(String rechargeTransactionid) {
        this.rechargeTransactionid = rechargeTransactionid == null ? null : rechargeTransactionid.trim();
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance == null ? null : accountBalance.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}