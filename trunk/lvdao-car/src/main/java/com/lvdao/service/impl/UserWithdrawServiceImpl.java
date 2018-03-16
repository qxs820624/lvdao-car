package com.lvdao.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.IUserWithdrawDao;
import com.lvdao.entity.UserWithdrawEntity;
import com.lvdao.service.IUserWithdrawService;

@Repository("userWithdrawService")
public class UserWithdrawServiceImpl implements IUserWithdrawService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserWithdrawServiceImpl.class);

	@Autowired
	private IUserWithdrawDao userWithdrawDao;

	@Override
	public int insert(UserWithdrawEntity entity) {
		if (null != entity && StringUtils.isBlank(entity.getId())) {
			LOGGER.info("UserWithdraw insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserWithdraw update service...");
		int result = userWithdrawDao.insert(entity);
		LOGGER.info("Exiting UserWithdraw insert service...");
		return result;
	}

	@Override
	public int update(UserWithdrawEntity entity) {
		if (null != entity && StringUtils.isBlank(entity.getId())) {
			LOGGER.info("UserWithdraw update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserWithdraw update service...");
		int result = userWithdrawDao.update(entity);
		LOGGER.info("Exiting UserWithdraw update service...");
		return result;
	}

	@Override
	public int delete(UserWithdrawEntity entity) {
		if (null != entity && StringUtils.isBlank(entity.getId())) {
			LOGGER.info("UserWithdraw delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserWithdraw delete service...");
		int result = userWithdrawDao.delete(entity);
		LOGGER.info("Exiting UserWithdraw delete service...");
		return result;
	}

	@Override
	public List<UserWithdrawEntity> queryList(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("UserWithdraw queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering UserWithdraw queryList service...");
		List<UserWithdrawEntity> list = userWithdrawDao.queryList(map);
		LOGGER.info("Exiting UserWithdraw queryList service...");
		return list;
	}

	@Override
	public PageList<UserWithdrawEntity> queryPage(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("UserWithdraw queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserWithdraw queryPage service...");
		PageList<UserWithdrawEntity> list = userWithdrawDao.queryPage(page, map);
		LOGGER.info("Exiting UserWithdraw queryPage service...");
		return list;
	}

	@Override
	public List<UserWithdrawEntity> queryAll() {
		LOGGER.info("Entering UserWithdraw queryAll service...");
		List<UserWithdrawEntity> list = userWithdrawDao.queryAll();
		LOGGER.info("Exiting UserWithdraw queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userWithdrawDao.getMaxId();
	}

	/**
	 * 检测数据库非空字段，是否为空
	 * 
	 * @param entity
	 * @return
	 */
	protected boolean checkNotNullFieldForInsert(UserWithdrawEntity entity) {
		if (null == entity 
				|| StringUtils.isBlank(entity.getUserName())
				|| StringUtils.isBlank(entity.getWithdrawAccountType())
				|| StringUtils.isBlank(entity.getWithdrawAccount()) 
				|| StringUtils.isBlank(entity.getWithdrawMoney())
				|| StringUtils.isBlank(entity.getWithdrawProcedure()) 
				|| StringUtils.isBlank(entity.getWithdrawTotal())
				|| StringUtils.isBlank(entity.getWithdrawBankFullName())
				|| StringUtils.isBlank(entity.getWithdrawAccountName())) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserWithdrawEntity> queryBatchList(List<String> ids) {
		LOGGER.info("Entering UserWithdraw queryBatchList service...");
		List<UserWithdrawEntity> list = userWithdrawDao.queryBatchList(ids);
		LOGGER.info("Exiting UserWithdraw queryBatchList service...");
		return list;
	}

}
