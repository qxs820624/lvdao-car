package com.lvdao.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 账户类型枚举类
 * 
 * @author zhxihu2008
 * @since 2017-01-24 18:55
 */
public enum AccountEnum {
	
	RMB("1", "现金账户"),
//	INTEGRAL("2","积分账户"),
//	RMB_COUPON("3","现金券账户"),
	STOCK("2","股券积分账户"),
	RECOMMEND_BONUS("3","分享补贴账户"),
	BOUNS_RETURN("4","燃油补贴账户"),
	RIDE_COUPON("5","乘车券账户"),
	SHARE_REWARD("6","燃油包账户");

    private String id;
    private String value;
    
    private AccountEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }
	
    public String getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }
 
    public static String getAccountIdByAccountName(String accountName) {
    	
        if (StringUtils.isBlank(accountName)) {
            return null;
        }
        
        for (AccountEnum accountEnum: AccountEnum.values()) {
            if (accountEnum.getValue().equals(accountName)) {
                return accountEnum.getId();
            }
        }
        return null;
    }
    
    public static String getAccountNameByAccountId(String id) {
    	
        if (StringUtils.isBlank(id)) {
            return null;
        }
        
        for (AccountEnum accountEnum: AccountEnum.values()) {
            if (accountEnum.getId().equals(id)) {
                return accountEnum.getValue();
            }
        }
        return null;
    }

    public static Map<String, String> getAccountMap() {
    	
    	Map<String, String> map = new HashMap<String, String>();
    	
        for (AccountEnum accountEnum: AccountEnum.values()) {
        	map.put(accountEnum.getId(), accountEnum.getValue());
        }
        return map;
    }
    
}
