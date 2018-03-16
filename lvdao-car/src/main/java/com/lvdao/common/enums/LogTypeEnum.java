package com.lvdao.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public enum LogTypeEnum {
	
	LOG_TYPE_LOGIN_INFO("0", "登录日志"), 
	LOG_TYPE_CASH_RECORD_INFO("1", "资金交易日志"), 
	LOG_TYPE_CHANGE_ROLE_INFO("2", "角色变更日志"), 
	LOG_TYPE_CHANGE_PERMISSION_INFO("3", "权限变更日志"), 
	LOG_TYPE_REGISTER_INFO("4", "注册新用户日志"), 
	LOG_TYPE_BUY_STOCK_INFO("5", "股票购买日志"), 
	LOG_TYPE_STOCK_TRADE_INFO("6", "股票交易日志"),
	LOG_TYPE_INSERT_APP_SELLER("7", "插入app商家表日志"),
	LOG_TYPE_INSERT_APP_USER("8", "插入app会员表日志"),
	LOG_TYPE_INSERT_USER_FROM_APP_SELLER("9", "获取appSeller插入user表错误日志"),
	LOG_TYPE_INSERT_USER_FROM_APP_USER("10", "获取appUser插入user表错误日志"),
	LOG_TYPE_DATA_SYNC_INFO("11","数据库同步日志"),
	LOG_TYPE_SMS_SENDER_INFO("12" , "短信发送日志"),
	LOG_TYPE_MAIL_SENDER_INFO("13" , "邮件发送日志"),
	LOG_TYPE_INSERT_WEIXIN_USER("14", "插入微信会员表日志"),
	LOG_TYPE_BUYPOINT_TRADE_INFO("15", "积分认购交易日志"),
	LOG_TYPE_VIP_PROMOTION_BONUS_INFO("18", "VIP推荐奖金"),
	LOG_TYPE_MANAGER_PROMOTION_BONUS_INFO("19", "经理人推荐奖金"),
	LOG_TYPE_VIP_BONUS_INFO("20", "加权分红"),
	LOG_TYPE_MANAGER_BONUS_INFO("21", "经理人分红"),
	LOG_TYPE_WITHDRAW_INFO("22", "提现"),
	LOG_TYPE_BOUNS_RETURN("23", "投资返还"),
	LOG_TYPE_RECOMMEND_BOUNS("24", "推荐奖励发放"),
	LOG_TYPE_OUT_CAR_RETURN("24", "上车补贴");

    private String id;
    private String value;
    
    private LogTypeEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }
	
    public String getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }
    
    public static String getLogTypeIdByLogTypeName(String logTypeName) {
        if (StringUtils.isBlank(logTypeName)) {
            return null;
        }
        
        for (LogTypeEnum logTypeEnum: LogTypeEnum.values()) {
            if (logTypeEnum.getValue().equals(logTypeName)) {
                return logTypeEnum.getId();
            }
        }
        return null;
    }
    
    public static String getLogTypeNameByLogTypeId(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        
        for (LogTypeEnum logTypeEnum: LogTypeEnum.values()) {
            if (logTypeEnum.getId().equals(id)) {
                return logTypeEnum.getValue();
            }
        }
        return null;
    }

    public static Map<String, String> getRoleMap() {
    	Map<String, String> map = new HashMap<String, String>();
    	
        for (LogTypeEnum logTypeEnum: LogTypeEnum.values()) {
        	map.put(logTypeEnum.getId(), logTypeEnum.getValue());
        }
        return map;
    }
    
}
