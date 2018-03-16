package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserAddressEntity;

public interface IUserAddressDao extends IBaseDao<UserAddressEntity> {
	/**
	 * 常用地址
	 * @param map
	 * @return
	 */
	List<UserAddressEntity> queryFrequencyAddress(Map<String,Object> map);
}