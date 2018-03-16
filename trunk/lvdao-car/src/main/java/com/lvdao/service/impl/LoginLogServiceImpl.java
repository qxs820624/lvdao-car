package com.lvdao.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.ILoginLogDao;
import com.lvdao.entity.LoginLogEntity;
import com.lvdao.service.ILoginLogService;

@Service("loginLogService")
public class LoginLogServiceImpl implements ILoginLogService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogServiceImpl.class);
	
	@Autowired
	private ILoginLogDao loginLogDao;
	
	@Override
	public int insert(LoginLogEntity entity) {
		return loginLogDao.insert(entity);
	}
	
	@Override
	public int update(LoginLogEntity entity) {
		return loginLogDao.update(entity);
	}
	
	@Override
	public int delete(LoginLogEntity entity) {
		return loginLogDao.delete(entity);
	}
	
	@Override
	public List<LoginLogEntity> queryList(Map<String, Object> map) {
		return loginLogDao.queryList(map);
	}
	
	@Override
	public List<LoginLogEntity> queryAll() {
		return loginLogDao.queryAll();
	}
	
	@Override
	public PageList<LoginLogEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("ILoginLogService queryPage page or map is null.");
			return null;
		}
		LOGGER.info("Entering ILoginLogService queryPage service...");
		PageList<LoginLogEntity> list = loginLogDao.queryPage(page, map);
		LOGGER.info("Exiting ILoginLogService queryPage service...");
		return list;
	}
	
	@Override
	public int getMaxId() {
		return loginLogDao.getMaxId();
	}
	
}
