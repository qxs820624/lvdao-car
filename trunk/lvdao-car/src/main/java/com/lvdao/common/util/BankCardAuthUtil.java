package com.lvdao.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 银行卡实名认证工具类
 * 
 * @author zhxihu2008
 * @since 2017-09-15 11:08
 */
public class BankCardAuthUtil {

//	private static final String APP_KEYT = "24649955";
//	private static final String APP_SECRET = "f3307487e923eb623eb3e2e6d3dab551";
	private static final String APP_CODE = "a928a297242e47718adf862ba92bea53";

	/**
	 * 实名制身份证银行卡
	 * 
	 * @author zhxihu2008
	 * @since 2017-09-15 11:11
	 * @param args
	 * 银行卡必须要有 后面的三个要素 账户名称 身份证号码 手机号码 可有可
	 */
	public static String validateBankCard(String bankCardNo, String accountName, String certID, String mobileNo) {
	    String host = "http://lundroid.market.alicloudapi.com";
	    String path = "/lianzhuo/verifi";
	    String method = "GET";

	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + APP_CODE);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("acct_name", accountName);
	    querys.put("acct_pan", bankCardNo);
	    querys.put("cert_id", certID);
	    querys.put("phone_num", mobileNo);

	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());
	    	JSONObject jsonObject = JSONObject.parseObject(JSONObject.parseObject(EntityUtils.toString(response.getEntity())).getString("resp"));
			String code = jsonObject.getString("code");
//			System.out.println(result);
	    	//获取response的body
//	    	System.out.println(EntityUtils.toString(response.getEntity()));
	 
	    	return code;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return null;
	}
	
	/**
	 * 根据返回的code查询中文意思
	 * 
	 * @author zhxihu2008
	 * @since 2017-09-15 11:54
	 * @param code
	 * @return
	 */
	public static String covertAuthCode(String code) {
		if(StringUtils.isBlank(code)) {
			return null;
		} else if(code.equals("0")) {
			return "OK";
		} else if(code.equals("4")) {
			return "此卡被没收，请于发卡方联系";
		} else if(code.equals("5")) {
			return "持卡人认证失败";
		} else if(code.equals("14")) {
			return "无效卡号";
		} else if(code.equals("15")) {
			return "此卡无对应发卡方";
		} else if(code.equals("21")) {
			return "该卡未初始化或睡眠卡";
		} else if(code.equals("34")) {
			return "作弊卡，吞卡";
		} else if(code.equals("40")) {
			return "发卡方不支持的交易";
		} else if(code.equals("41")) {
			return "此卡已经挂失	此卡已经挂失";
		} else if(code.equals("43")) {
			return "此卡被没收";
		} else if(code.equals("54")) {
			return "该卡已过期";
		} else if(code.equals("57")) {
			return "发卡方不允许此交易";
		} else if(code.equals("62")) {
			return "受限制的卡";
		} else if(code.equals("75")) {
			return "密码错误次数超限";
		} else if(code.equals("96")) {
			return "交易失败，请稍后重试";
		} 
		return null;
	}

	public static void main(String[] args) {
	
	}
	
}
