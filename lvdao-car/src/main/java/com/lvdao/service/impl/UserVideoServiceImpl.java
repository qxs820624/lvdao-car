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
import com.lvdao.dao.IUserVideoDao;
import com.lvdao.entity.UserViedoEntity;
import com.lvdao.service.IUserVideoService;

@Service("userVideoService")
public class UserVideoServiceImpl implements IUserVideoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserVideoServiceImpl.class);
	
	@Autowired
	private IUserVideoDao userVideoDao;
	
	@Override
	public int insert(UserViedoEntity entity) {
		if(null == entity) {
			LOGGER.info("UserViedoEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserViedoEntity insert service...");
		int result = userVideoDao.insert(entity);
		LOGGER.info("Exiting UserViedoEntity insert service...");
		return result;
	}
	
	@Override
	public int update(UserViedoEntity entity) {
		if(null == entity) {
			LOGGER.info("UserViedoEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserViedoEntity update service...");
		int result = userVideoDao.update(entity);
		LOGGER.info("Exiting UserViedoEntity update service...");
		return result;
	}
	
	@Override
	public int delete(UserViedoEntity entity) {
		if(null == entity) {
			LOGGER.info("UserViedoEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserViedoEntity delete service...");
		int result = userVideoDao.delete(entity);
		LOGGER.info("Exiting UserViedoEntity delete service...");
		return result;
	}
	
	@Override
	public List<UserViedoEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserViedoEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering UserViedoEntity queryList service...");
		List<UserViedoEntity> list = userVideoDao.queryList(map);
		LOGGER.info("Exiting UserViedoEntity queryList service...");
		return list;
	}
	
	@Override
	public PageList<UserViedoEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("UserViedoEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserViedoEntity queryPage service...");
		PageList<UserViedoEntity> list = userVideoDao.queryPage(page, map);
		LOGGER.info("Exiting UserViedoEntity queryPage service...");
		return list;
	}
	

	@Override
	public List<UserViedoEntity> queryAll() {
		LOGGER.info("Entering UserViedoEntity queryAll service...");
		List<UserViedoEntity> list = userVideoDao.queryAll();
		LOGGER.info("Exiting UserViedoEntity queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userVideoDao.getMaxId();
	}
	
}
