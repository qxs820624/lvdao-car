package com.lvdao.common.enums;

public enum WithdrawStatusEnum {
	
	WITHDRAW_STATUS_DEALING("1", "处理中"),
	WITHDRAW_STATUS_SUCCESS("2", "打款成功"), 
	WITHDRAW_STATUS_FAIL("3", "已退回账户");

   private String id;
   private String value;
   
   private WithdrawStatusEnum(String id, String value) {
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
