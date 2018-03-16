package com.lvdao.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public enum UserSourceEnum {
	
	USER_SOURCE_APP("1", "吃货网"),
	USER_SOURCE_WX("2", "微信公众号"),
	USER_SOURCE_PC("3", "官网");
	
    private String id;
    private String value;
    
    private UserSourceEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

    public static String getSourceIdBySourceName(String sourceName) {
        if (StringUtils.isBlank(sourceName)) {
            return null;
        }
        
        for (UserSourceEnum userSourceEnum: UserSourceEnum.values()) {
            if (userSourceEnum.getValue().equals(sourceName)) {
                return userSourceEnum.getId();
            }
        }
        return null;
    }
    
    public static String getRoleNameByroleId(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        
        for (UserSourceEnum userSourceEnum: UserSourceEnum.values()) {
            if (userSourceEnum.getId().equals(id)) {
                return userSourceEnum.getValue();
            }
        }
        return null;
    }

    public static Map<String, String> getRoleMap() {
    	Map<String, String> map = new HashMap<String, String>();
    	
        for (UserSourceEnum userSourceEnum: UserSourceEnum.values()) {
        	map.put(userSourceEnum.getId(), userSourceEnum.getValue());
        }
        return map;
    }
	
}
