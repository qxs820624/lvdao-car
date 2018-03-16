package com.lvdao.common.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

/**
 * 支付宝工具类
 * 
 * @author zhxihu2008
 * @since 2017-09-15 20:10
 */
public class AlipayUtil {
	
	private static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
	private static final String APP_ID = "2017111109865927";
	private static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCxxm5RHLKIM3YykPdSMh0+Ga4qjb1CNGUkBgvp6MasR5ljYPLNQ4HSJ7ZfujEZfEOfEwiJ6uk8WWhJ7ffdm5TPO+6ICbhXdMkDMVi1T8TAynarssWpMJ9Og2DWvcSCcpHEcg/GouKXme76+kgPPkUM79BaRUQxXKs5bor8FEMdJD8xYD2LVgJZCCkgrfYbz/1+PDSBm6UM/xgAlbmWyEWLUxmC8YJXottVPoc9wiAnD/vNxWX3qTan3ph0rblNSAD10FvwsTs2sWulyj8A2AfTXbb5FPf3pl6RCi278bwNDFzG8EEDvfzDKQGc6gSm3GzUaWAoBMUEQ5P1RwH8+1lAgMBAAECggEAN2mXi7nmDaFSt24UzVl+npl2JP7ypGblLtbTeLXGVZQA9jS9SEglto5dyHiOtlEfj+y48avvfcdPMba7oXL0Ek76oNnov4ta4KaMqHEE9dBcVq3DnceYJSmI+kR79eeyDuUI/TwJLksMF0NBl4heXeIDQvr8kwumqH36oY2FOgo7Iv26HHmDQC1KwxzTLjo+nAn6RiqTn8s4aQDVQqtXg7CV8xAqfwMeiUS35Ii3364fbXlW2gq5Ei9ts6r2Pvvru/lP7Df9ibAzfJbGup0EsC+Yh4CWqBIc4Pov02WdlDGtB1TC4K5pRySmg8XQHF1uqlRlUJhxRHoHd+hijQiNbQKBgQC6ZYeJSeKCsvqAj+HHQw0n6GFfQUYS7Kx4P49tgbLiTmstYOFU+aA5mE9wu5vi4dDYsske+/9ECOKaTalkVKKGdQrl131DwkR+bZo89aEs6htaPHbGrsBxm4tRJksEiRk8llpmpVa2JNP5/BJFemfM2zG9GFOqbZrUpNSOTccC2wKBgQCznLgnbNUxfh9nhy1u7aHFPwcWQH4vxNstBj8ZVgyEhWZN2wGyt4pY+3/AEuNjtxh7SnjbaNmNAaJ5L4IAAoausEMzIye9Kn9LQb3TJhJCkudEHZ2GeXOWfwK7w71t7n3tvslaZMFnEDOm/DU6RPWLiAMAJ8lox7F5yT3WM9YkvwKBgEsGkN5+wdCPz+qtf1hgMcxtabwYnucBIo6oLsgKG4GhxNwYEbrNW2OzXc5nhWB2OkTu+asiDWZKGUO4nMUIl/583+0Rppb+e+lTnnCJAd39QRM6ISjpsAZKcHIycqGZuXG8pxiH1nYsLrKBdq1Gjr9TPxr4zrxbr6WFzxSUU9VfAoGACrGkDUTflKjLueBEhjxDy3irNVn1qWZxHkcm7p0+9YzzwGLvwPJInG+s9YSqYT9/h6exdm7RtSGJyOlZNiLwXepEtgC71vl2xsZNRQsZPzl754RT4rPMfANn6EIJP0hZ2xPm7SjekwJV8PVHPgbpD7uzYgj8Zu6s1HF06xH/CAUCgYAObwqoKYrpN07LZzxt1BzBEQbumPJ4+2DvScfEcyHgX4HXz2b/B/t1ClrwDkVHbXLN6d67+3Gk1yOQJFvS1RlEarSRo7EgRatnclOtKBJMa6k0rsVf85gJmSHewF+rBf5vEMYF6j7UUdqx+eWCmq5wLp89dc5wLvXIVWUa5/ihmQ==";
	private static final String DATA_FORMAT = "json";
	private static final String CODE_FORMAT = "GBK";
	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhJoqJ93EWVPby08+i9/6C78VEjdZT/qkH5+In5SJ9/kwMJZe9YAKAvtnksRn2UoD5KmyFXu7mLvVk1nY5stqOueu+S2Iubmt/i5Uj8sJc4Vg5WrZjfOjfEt+gC8RV9usiNNT02Ii9Wdd7Sl3+nsJDHL+rjpPN4I8bQx6RgPI4vZYetl8quvfsxw6ISi9SFs2x8JSUNchiCPBjW+fbc2vauiB8/8W4KqQmaPCHpeo1xc1PvZZJq/ZePcp/uAbNTfINwQ1mYbeQpyWnVdBFkiXq1JOrdzGFlAX9WoUSrVFpElknhJWrAbxFH9ex+riykRjrrw9A+wTlFqSTNeVscnUmwIDAQAB";
	private static final String ENCRYPT_TYPE = "RSA2";
	
	/**
	 * 给某一个支付宝账户转账
	 * 
	 * @author zhxihu2008
	 * @since 2017-09-15 20:31
	 * @param fundTransBillNo 商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方
	 * @param payeeType(1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。)
	 * @param alipayAccount  收款方支付宝账户
	 * @param amount         付款金额
	 * @param payerShowName  付款方姓名
	 * @param payerRealName  收款方真实姓名
	 * @param remark         转账备注
	 */
	public static Boolean FundTransToAccount(String fundTransBillNo, String payeeType, String alipayAccount, String amount, String payerShowName, String payerRealName, String remark) {
		AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY, APP_ID, PRIVATE_KEY, DATA_FORMAT, CODE_FORMAT, PUBLIC_KEY, ENCRYPT_TYPE);
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent("{" +
				"\"out_biz_no\":\""
				+ fundTransBillNo
				+ "\"," +
				"\"payee_type\":\""
				+ payeeType
				+ "\"," +
				"\"payee_account\":\""
				+ alipayAccount
				+ "\"," +
				"\"amount\":\""
				+ amount
				+ "\"," +
				"\"payer_show_name\":\""
				+ payerShowName
				+ "\"," +
				"\"payee_real_name\":\""
				+ payerRealName
				+ "\"," +
				"\"remark\":\""
				+ remark
				+ "\"" +
				"  }");
		AlipayFundTransToaccountTransferResponse response;
		try {
			response = alipayClient.execute(request);
			
			if(response.isSuccess()) {
				System.out.println("调用成功");
				return true;
			} else {
				System.out.println("调用失败");
				return false;
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public static Boolean FundTransToAccount(String fundTransBillNo, String alipayAccount, String payerRealName, String amount) {
		return AlipayUtil.FundTransToAccount(fundTransBillNo, "ALIPAY_LOGONID", alipayAccount, amount, "深圳众商世界企业管理有限公司", payerRealName, "驴道支付宝提现");
	}
	
	public static void main(String[] args) {
		String fundTransBillNo = StringUtil.getOrderSn();
		String payeeType = "ALIPAY_LOGONID";
		String alipayAccount = "";
//		String alipayAccount = "";
		String amount = "0.1";
		String payerShowName = "";
		String payerRealName = "";
		String remark = "支付测试";
		
		boolean result = AlipayUtil.FundTransToAccount(fundTransBillNo, payeeType, alipayAccount, amount, payerShowName, payerRealName, remark);
		
		if(result) {
			System.out.println("转账成功");
			return;
		}
		
		System.out.println("转账失败");
		return;
	}
	
}
