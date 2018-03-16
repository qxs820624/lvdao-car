package com.lvdao.dao;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserLineEntity;

public interface IUserLineDao extends IBaseDao<UserLineEntity> {

	List<UserLineEntity> queryFrequencyLine(Map<String, Object> map);
	
}