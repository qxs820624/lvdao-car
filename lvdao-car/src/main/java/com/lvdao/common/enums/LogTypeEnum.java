package com.lvdao.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public enum LogTypeEnum {
	/**
	 * 所有类型同app端，需及时同步
	 */
	LOG_TYPE_REGISTER_INFO("0", "注册日志"), 
	LOG_TYPE_LOGIN_INFO("1", "登录日志"),
	LOG_TYPE_APPLY_AGENCY("3", "升级"),
	LOG_TYPE_RECHARGE("4", "充值"),
	LOG_TYPE_SHOPPING("5", "消费"),
	LOG_TYPE_ADD_INTEGRAL("6", "增加积分"),
	LOG_TYPE_SUBTRACT_INTEGRAL("7", "扣减积分"),
	LOG_TYPE_WITHDRAW("8", "提现"),
	LOG_TYPE_SIGNIN("9", "签到"),
	LOG_TYPE_PROFIT("10", "分红"),
	LOG_TYPE_GET_RED_PACKAGE("11", "领取红包"),
	LOG_TYPE_ADD_RMB("12", "增加现金"),   //lvdao-web 现金记录
	LOG_TYPE_SUBTRACT_RMB("13", "扣减现金"),
	LOG_TYPE_RECOMMEND_BONUS_AMOUNT("14", "推荐奖"),
	LOG_TYPE_ADD_STOCK("15", "增加股券"),
	LOG_TYPE_SUBTRACT_STOCK("16", "扣减股券"),
	LOG_TYPE_PUBLISH_INFO("17", "发布用车信息"),
	LOG_TYPE_CHANGE_PERMISSION_INFO("18", "权限变更日志"),
	LOG_TYPE_CHANGE_ROLE_INFO("19", "角色变更日志"),
	LOG_TYPE_BOUNS_RETURN("20", "燃油补贴"),
	LOG_TYPE_OUT_CAR_RETURN("21", "上车补贴"),
	LOG_TYPE_YECO_STOCK("22", "YECO股券账户变更日志"),
	LOG_TYPE_RIDE_CUPON("23", "乘车券记录"),
	LOG_TYPE_REWARD_PACKAGE("24", "燃油包变更记录"),//	 用desc做加减统计
	LOG_TYPE_REFUSE_WITHDRAW("25", "拒绝提现申请"),
	LOG_TYPE_OUT_CAR_COMPENSATE("26", "出车补贴"),
	LOG_TYPE_INGEO_STOCK("27", "英吉尔股券账户变更日志"),
	LOG_TYPE_HONG_KONG_STOCK("28", "港股账户变更日志");
	

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
