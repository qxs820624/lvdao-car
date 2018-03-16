package com.lvdao.common.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 订单类型
 *
 * @author hexiang
 * @since 2017-12-18 15:37
 */
public enum OrderTypeEnum {

	SIGN_ORDER("1", "签到订单"),
	RECHARGE_ORDER("2", "充值订单"),
	RECHARGE_VIP_ORDER("3", "升级VIP订单"),
	RECHARGE_SALER_ORDER("4","升级合创人订单"),
	RECHARGE_MANAGER_ORDER("5", "升级为司机订单"),
	RECHARGE_MONTH_ORDER("6","充值月费订单"), 
	RECHARGE_YEAR_ORDER("7", "充值年费订单"), 
	CAR_ORDER("8", "车费订单"),
//	INVESTMENT_FIVE_ORDER("9", "投资5万订单"),
//	INVESTMENT_TEN_ORDER("10", "投资10万订单"),
//	INVESTMENT_FIFTEEN_ORDER("11", "投资15万订单"),
//	INVESTMENT_TWENTYFIVE_ORDER("12", "投资25万订单"),
//	INVESTMENT_FIFTY_ORDER("9", "投资50万订单");
	INVESTMENT_ORDER("9", "加盟订单");

	private String id;
	private String value;

	private OrderTypeEnum(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public static String getOrderTypeIdByOrderTypeName(String orderTypeName) {
		if (StringUtils.isBlank(orderTypeName))
			return null;

		for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
			if (orderTypeEnum.getValue().equals(orderTypeName)) {
				return orderTypeEnum.getId();
			}
		}
		return null;
	}

	public static String getOrderTypeNameByOrderTypeId(String orderTypeId) {
		if (StringUtils.isBlank(orderTypeId)) {
			return null;
		}

		for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
			if (orderTypeEnum.getId().equals(orderTypeId)) {
				return orderTypeEnum.getValue();
			}
		}
		return null;
	}
}
