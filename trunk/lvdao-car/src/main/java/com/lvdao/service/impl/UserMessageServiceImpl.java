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
import com.lvdao.dao.IUserMessageDao;
import com.lvdao.entity.UserMessageEntity;
import com.lvdao.service.IUserMessageService;

@Service("userMessageService")
public class UserMessageServiceImpl implements IUserMessageService{

	 private static final Logger LOGGER = LoggerFactory.getLogger(UserMessageServiceImpl.class);
		
		@Autowired
		private IUserMessageDao userMessageDao;
		
		@Override
		public int insert(UserMessageEntity entity) {
			if(null == entity) {
				LOGGER.info("UserMessageEntity insert service entity is null.");
				return CommonConst.DIGIT_ZERO;
			}
			LOGGER.info("Entering UserMessageEntity insert service...");
			int result = userMessageDao.insert(entity);
			LOGGER.info("Exiting UserMessageEntity insert service...");
			return result;
		}
		
		@Override
		public int update(UserMessageEntity entity) {
			if(null == entity) {
				LOGGER.info("UserMessageEntity update service entity is null.");
				return CommonConst.DIGIT_ZERO;
			}
			LOGGER.info("Entering UserMessageEntity update service...");
			int result = userMessageDao.update(entity);
			LOGGER.info("Exiting UserMessageEntity update service...");
			return result;
		}
		
		@Override
		public int delete(UserMessageEntity entity) {
			if(null == entity) {
				LOGGER.info("UserMessageEntity delete service entity is null.");
				return CommonConst.DIGIT_ZERO;
			}
			LOGGER.info("Entering UserMessageEntity delete service...");
			int result = userMessageDao.delete(entity);
			LOGGER.info("Exiting UserMessageEntity delete service...");
			return result;
		}
		
		@Override
		public List<UserMessageEntity> queryList(Map<String, Object> map) {
			if(null == map || map.isEmpty()) {
				LOGGER.info("UserMessageEntity queryList service map is null.");
				return null;
			}
			LOGGER.info("Entering UserMessageEntity queryList service...");
			List<UserMessageEntity> list = userMessageDao.queryList(map);
			LOGGER.info("Exiting UserMessageEntity queryList service...");
			return list;
		}
		
		@Override
		public PageList<UserMessageEntity> queryPage(Page page, Map<String, Object> map) {
			if(null == page || null == map ) {
				LOGGER.info("UserMessageEntity queryPage service page or map is null.");
				return null;
			}
			LOGGER.info("Entering UserMessageEntity queryPage service...");
			PageList<UserMessageEntity> list = userMessageDao.queryPage(page, map);
			LOGGER.info("Exiting UserMessageEntity queryPage service...");
			return list;
		}

		@Override
		public List<UserMessageEntity> queryAll() {
			LOGGER.info("Entering UserMessageEntity queryAll service...");
			List<UserMessageEntity> list = userMessageDao.queryAll();
			LOGGER.info("Exiting UserMessageEntity queryAll service...");
			return list;
		}

		@Override
		public int getMaxId() {
			return userMessageDao.getMaxId();
		}
		
}