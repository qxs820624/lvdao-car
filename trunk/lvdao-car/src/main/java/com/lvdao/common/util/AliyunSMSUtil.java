package com.lvdao.common.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 阿里云短信发送工具类
 * 
 * @author zhxihu208
 * @since 2017-07-13 01:37
 */
public class AliyunSMSUtil {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) hangong自己的带消息服务 不能用
//    static final String accessKeyId = "LTAIZmwy3YPcepaa";
//    static final String accessKeySecret = "QPj5zHi6XnSh2r1GAT6MhZrgQFqt8O";
    static final String accessKeyId = "LTAIW8ViZApcugRU";
  	static final String accessKeySecret = "KPNyZRCmTiALH5l8GnJcQXRAxMDOTf";
    
    public static SendSmsResponse sendSms(String mobileNo, String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-shenzhen", "LTAIW8ViZApcugRU", "KPNyZRCmTiALH5l8GnJcQXRAxMDOTf");
        DefaultProfile.addEndpoint("cn-shenzhen", "cn-shenzhen", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobileNo);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("拼货");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_76600650");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        Map<String, String> map = new HashMap<String, String>();
    	map.put("code", code);
        
        request.setTemplateParam(JSONObject.toJSON(map).toString());
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("发送成功");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
 
    public static void main(String[] args) throws ClientException, InterruptedException {

        //发短信
//    	Map<String, String> map = new HashMap<String, String>();
//    	map.put("code", "123456");
//    	System.out.println(JSONObject.toJSON(map).toString());
        SendSmsResponse response = sendSms("18717874379", "123456");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
    	
    }
    
}
