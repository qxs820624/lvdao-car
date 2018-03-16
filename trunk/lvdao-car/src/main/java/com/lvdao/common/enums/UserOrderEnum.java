package com.lvdao.common.enums;

public enum UserOrderEnum {
	
	USERORDER_TYPE_OPEN_ACCOUNT_NO_PAY(1, 0, "未付款的开户订单"),
	USERORDER_TYPE_OPEN_ACCOUNT_PAYED(1, 1, "开户已付款的订单"),
	USERORDER_TYPE_BUY_CASH_INTEGRAL_NO_PAY(2, 0, "未支付的购买现金积分订单"),
	USERORDER_TYPE_BUY_CASH_INTEGRAL_PAYED(2, 1, "已支付的购买现金积分订单");

    private Integer type;
    private Integer status;
    private String value;
    
    private UserOrderEnum(Integer type, Integer status, String value) {
        this.type = type;
        this.status = status;
        this.value = value;
    }
	
    public Integer getType() {
		return type;
	}

	public Integer getStatus() {
		return status;
	}

	public String getValue() {
		return value;
	}

}
