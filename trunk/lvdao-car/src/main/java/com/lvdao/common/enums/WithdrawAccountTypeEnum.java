package com.lvdao.common.enums;

public enum WithdrawAccountTypeEnum {
	
	WITHDRAW_ACCOUNT_TYPE_WEIXIN("1", "微信"),
	WITHDRAW_ACCOUNT_TYPE_ALIPAY("2", "支付宝"), 
	WITHDRAW_ACCOUNT_TYPE_BAND("3", "银行卡");

   private String id;
   private String value;
   
   private WithdrawAccountTypeEnum(String id, String value) {
       this.id = id;
       this.value = value;
   }
	
   public String getId() {
       return id;
   }
   
   public String getValue() {
       return value;
   }
}
