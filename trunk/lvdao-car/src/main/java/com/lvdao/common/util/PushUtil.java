package com.lvdao.common.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushUtil {
	
	private static final Logger LOG= LoggerFactory.getLogger(PushUtil.class);
	
	private static String appKey = "d579b26034ff5cb70f6aa0e2";
	private static String masterSecret = "a60caefb1cdeb465e4d9fb2b";
	
	public static void push(PushPayload payload){
		
		@SuppressWarnings("deprecation")
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);

        // For push, all you need do is to build PushPayload object.
        //PushPayload payload = buildPushObject_all_all_alert(ALERT);
        //PushPayload payload = buildPushObject_all_alias_alert(ALERT,userName);
        //PushPayload payload = buildPushObject_all_alias_alert_extras(ALERT,userName,extras);
		//PushPayload payload = buildPushObject_part_alias_alert(ALERT,userName,userName);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
	}
	
	/**
	 * 广播，向所有装有我们APP的手机发送通知消息 byzhaoming
	 * 
	 * 进行推送的关键在于构建一个 PushPayload 对象。以下示例一般的构建对象的用法。
     * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	 * @param ALERT
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert(String ALERT) {
        return PushPayload.alertAll(ALERT);
    }
	
	/**
	 * 定下给某用户发送通知消息byzhaoming
	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
	 * @param ALERT
	 * @param userName
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert(String ALERT,String userName) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(userName))
            .setNotification(Notification.alert(ALERT))
            .build();
	}
	
	/**
	 * 给部门用户分送信息，应该最多一次性发送1000个 byzhaoming
	 * @param ALERT
	 * @param userName
	 * @param userName1
	 * @return
	 */
	public static PushPayload buildPushObject_part_alias_alert(String ALERT,String userName,String userName1) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.newBuilder()
                        //.addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias(userName, userName1))
                        .build())
				.setNotification(Notification.alert(ALERT))
				.build();
	}
	
	/**
	 * 发送额外的扩展字段信息byzhaoming
	 * @param ALERT
	 * @param userName
	 * @param extras
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert_extras(String ALERT,String userName, Map<String,String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(userName))
                .setNotification(Notification.newBuilder().setAlert(ALERT)
                     .addPlatformNotification(AndroidNotification.newBuilder()
     				         .addExtras(extras).build())
     				  //发给Ios的额外扩展字段信息       
     				 .addPlatformNotification(IosNotification.newBuilder()
	                         .addExtras(extras).build())
	                 .build())
                .build();
	}
	 
	public static void main(String[] args) {
		//String type="2";
		String ALERT="333222";
		String userName="18665311512";
		Map<String,String> extras = new HashMap<String,String>();
		extras.put("22", "22");
		extras.put("zzz", "zzz");
		PushPayload payload=buildPushObject_all_alias_alert_extras(ALERT,userName,extras);
		push(payload);
	}
	
	
}
