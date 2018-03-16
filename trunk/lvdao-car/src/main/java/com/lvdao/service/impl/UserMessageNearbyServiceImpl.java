package com.lvdao.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.IUserMessageNearbyDao;
import com.lvdao.entity.UserMessageNearbyEntity;
import com.lvdao.service.IUserMessageNearbyService;

@Service("userMessageNearbyService")
public class UserMessageNearbyServiceImpl implements IUserMessageNearbyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserMessageNearbyServiceImpl.class);

	@Autowired
	private IUserMessageNearbyDao userMessageNearbyDao;

	@Override
	public int insert(UserMessageNearbyEntity entity) {
		if (null == entity) {
			LOGGER.info("UserMessageNearbyServiceImpl insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserMessageNearbyServiceImpl insert service...");
		int result = userMessageNearbyDao.insert(entity);
		LOGGER.info("Exiting UserMessageNearbyServiceImpl insert service...");
		return result;
	}

	@Override
	public int update(UserMessageNearbyEntity entity) {
		return userMessageNearbyDao.update(entity);
	}

	@Override
	public int delete(UserMessageNearbyEntity entity) {
		if (null == entity) {
			LOGGER.info("UserMessageNearbyServiceImpl delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserMessageNearbyServiceImpl delete service...");
		int result = userMessageNearbyDao.delete(entity);
		LOGGER.info("Exiting UserMessageNearbyServiceImpl delete service...");
		return result;
	}

	@Override
	public List<UserMessageNearbyEntity> queryList(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("UserMessageNearbyServiceImpl queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering UserMessageNearbyServiceImpl queryList service...");
		List<UserMessageNearbyEntity> list = userMessageNearbyDao.queryList(map);
		LOGGER.info("Exiting UserMessageNearbyServiceImpl queryList service...");
		return list;
	}

	@Override
	public PageList<UserMessageNearbyEntity> queryPage(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("UserMessageNearbyServiceImpl queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserMessageNearbyServiceImpl queryPage service...");
		PageList<UserMessageNearbyEntity> list = userMessageNearbyDao.queryPage(page, map);
		LOGGER.info("Exiting UserMessageNearbyServiceImpl queryPage service...");
		return list;
	}

	@Override
	public List<UserMessageNearbyEntity> queryAll() {
		LOGGER.info("Entering UserMessageNearbyServiceImpl queryAll service...");
		List<UserMessageNearbyEntity> list = userMessageNearbyDao.queryAll();
		LOGGER.info("Exiting UserMessageNearbyServiceImpl queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userMessageNearbyDao.getMaxId();
	}

}
