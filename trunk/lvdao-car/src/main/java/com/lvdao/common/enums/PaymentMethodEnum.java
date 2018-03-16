package com.lvdao.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 付款方式枚举类
 * 
 * @author hexiang
 * @since 2017-07-25 16:27
 */
public enum PaymentMethodEnum {
	
	PAYMENT_METHOD_WX("1", "微信"), 
	PAYMENT_METHOD_ALIPAY("2", "支付宝"),
	PAYMENT_METHOD_BANK("3", "银联"),
	PAYMENT_METHOD_CASH("4", "现金"),
	PAYMENT_METHOD_OFFLINE_WX("5", "线下微信"), 
	PAYMENT_METHOD_OFFLINE_ALIPAY("6", "线下支付宝"),
	PAYMENT_METHOD_OFFLINE_BANK("7", "线下银联"),
	PAYMENT_METHOD_POINT("8", "积分"),
	PAYMENT_METHOD_WALLET("9", "余额"),
	PAYMENT_METHOD_COUPON("10", "优惠券"),
	PAYMENT_METHOD_MIX("11", "混合支付");

    private String id;
    private String value;
    
    private PaymentMethodEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }
	
    public String getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }
    
    public static String getPaymentMethodIdByPaymentMethodName(String paymentMethodName) {
        if (StringUtils.isBlank(paymentMethodName)) {
            return null;
        }
        
        for (PaymentMethodEnum paymentMethodEnum: PaymentMethodEnum.values()) {
            if (paymentMethodEnum.getValue().equals(paymentMethodName)) {
                return paymentMethodEnum.getId();
            }
        }
        return null;
    }
    
    public static String getPaymentMethodNameByPaymentMethodId(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        
        for (PaymentMethodEnum paymentMethodEnum: PaymentMethodEnum.values()) {
            if (paymentMethodEnum.getId().equals(id)) {
                return paymentMethodEnum.getValue();
            }
        }
        return null;
    }

    public static List<String> getPaymentMethodList() {
    	List<String> list = new ArrayList<String>();
    	
        for (PaymentMethodEnum paymentMethodEnum: PaymentMethodEnum.values()) {
        	list.add(paymentMethodEnum.getValue());
        }
        return list;
    }
    
}
