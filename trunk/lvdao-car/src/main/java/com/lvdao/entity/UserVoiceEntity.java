package com.lvdao.entity;

import java.io.Serializable;
/**
 * 用户声音t_user_voice
 * date: 2016年9月16日 下午1:17:56 
 * @author wangyu
 */
public class UserVoiceEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 6761932576591180613L;
	private String userId;//用户ID
	private String userName;//用户名
	private String voiceId;//声音ID
	private String voiceUrl; //声音url
	
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVoiceId() {
		return voiceId;
	}
	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}
}