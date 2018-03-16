package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserLineEntity;

public interface IUserLineService extends IBaseService<UserLineEntity> {

	List<UserLineEntity> queryFrequencyLine(Map<String, Object> map);

	/**
	 * 常用线路
	 * @param userId
	 * @param frequency
	 * @return
	 */
	Map<String, Object> queryFrequencyLine(String userId, String frequency);
	
}
