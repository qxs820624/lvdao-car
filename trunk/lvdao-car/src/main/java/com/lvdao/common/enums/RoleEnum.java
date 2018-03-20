package com.lvdao.common.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 角色枚举类
 * 
 * @author zhxihu2008
 * @since 2017-01-24 18:16
 */
public enum RoleEnum {
	ROLE_TYPE_DRIVER("1", "专职司机","10000"), 
	ROLE_TYPE_FARE("2", "乘客",""), 
	ROLE_TYPE_PART_DRIVER("3", "兼职司机","1000"),
	ROLE_TYPE_FIVE_CAR_MASTER("4", "5万车主","50000"),
	ROLE_TYPE_ADMIN("5", "系统管理员",""),
	ROLE_TYPE_TEM_CAR_MASTER("6", "10万车主","100000"),
	ROLE_TYPE_MANAGEER("7", "运营",""),
	ROLE_TYPE_FINANCE_OFFICER("8", "财务","");
	

    private String id;
    private String value;
    private String price;//	升级为该角色加盟订单金额
    
    private RoleEnum(String id, String value, String price) {
        this.id = id;
        this.value = value;
        this.price = price;
    }
	
    public String getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }
    
    public static String getRoleIdByRoleName(String roleName) {
        if (StringUtils.isBlank(roleName)) {
            return null;
        }
        
        for (RoleEnum roleEnum: RoleEnum.values()) {
            if (roleEnum.getValue().equals(roleName)) {
                return roleEnum.getId();
            }
        }
        return null;
    }
    
    public static String getRoleNameByroleId(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        
        for (RoleEnum roleEnum: RoleEnum.values()) {
            if (roleEnum.getId().equals(id)) {
                return roleEnum.getValue();
            }
        }
        return null;
    }

    public static Map<String, String> getRoleMap() {
    	Map<String, String> map = new HashMap<String, String>();
    	
        for (RoleEnum roleEnum: RoleEnum.values()) {
        	map.put(roleEnum.getId(), roleEnum.getValue());
        }
        return map;
    }

	public String getPrice() {
		return price;
	}
}
