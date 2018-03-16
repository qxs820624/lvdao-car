package com.lvdao.entity;

import java.io.Serializable;
/**
 * 用户视频表     t_user_viedo 
 * date: 2016年9月16日 下午1:18:47 
 * @author wangyu
 */
public class UserViedoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7891265887804828989L;
	
	private String userId;//用户ID
	private String userName;//用户名
	private String viedoId;//视频ID
	private String viedoUrl;//视频路径
	
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
	public String getViedoId() {
		return viedoId;
	}
	public void setViedoId(String viedoId) {
		this.viedoId = viedoId;
	}
	public String getViedoUrl() {
		return viedoUrl;
	}
	public void setViedoUrl(String viedoUrl) {
		this.viedoUrl = viedoUrl;
	}
	
}