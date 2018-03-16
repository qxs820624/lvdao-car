package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserAddressEntity;

public interface IUserAddressService extends IBaseService<UserAddressEntity> {

	List<UserAddressEntity> queryFrequencyAddress(Map<String, Object> map);

	/**
	 * 常用地址查询
	 * @param userId
	 * @param frequency
	 * @return
	 */
	Map<String, Object> queryFrequencyAddress(String userId, String frequency);
	
}
