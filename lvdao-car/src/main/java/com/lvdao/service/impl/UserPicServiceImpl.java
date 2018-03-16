package com.lvdao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.common.util.StringUtil;
import com.lvdao.dao.IUserPicDao;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.service.IUserPicService;

@Service("userPicService")
public class UserPicServiceImpl implements IUserPicService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserPicServiceImpl.class);

	@Autowired
	private IUserPicDao userPicDao;

	@Override
	public int insert(UserPicEntity entity) {
		if (null == entity) {
			LOGGER.info("UserPicServiceImpl insert entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserPicServiceImpl insert...");
		int result = userPicDao.insert(entity);
		LOGGER.info("Exiting UserPicServiceImpl insert...");
		return result;
	}

	@Override
	public int insertList(List<UserPicEntity> entityList) {
		if (null == entityList || entityList.size() == 0) {
			LOGGER.info("UserPicServiceImpl insertList entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserPicServiceImpl insertList...");
		int result = userPicDao.insertList(entityList);
		LOGGER.info("Exiting UserPicServiceImpl insertList...");
		return result;
	}

	@Override
	public int update(UserPicEntity entity) {
		if (null == entity || StringUtils.isBlank(entity.getUserName()) || StringUtils.isBlank(entity.getPicId())) {
			LOGGER.info("UserPicServiceImpl update entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserPicServiceImpl update...");
		int result = userPicDao.update(entity);
		LOGGER.info("Exiting UserPicServiceImpl update...");
		return result;
	}

	@Override
	public int delete(UserPicEntity entity) {
		if (null == entity || StringUtils.isBlank(entity.getUserId()) || StringUtils.isBlank(entity.getPicGroupId())) {
			LOGGER.info("UserPicServiceImpl delete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserPicServiceImpl delete...");
		int result = userPicDao.delete(entity);
		LOGGER.info("Exiting UserPicServiceImpl delete...");
		return result;
	}

	@Override
	public int batchDelete(List<UserPicEntity> entityList) {
		if (null == entityList || entityList.size() == CommonConst.DIGIT_ZERO) {
			LOGGER.info("UserPicServiceImpl batchDelete entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserPicServiceImpl batchDelete...");
		int result = userPicDao.batchDelete(entityList);
		LOGGER.info("Exiting UserPicServiceImpl batchDelete...");
		return result;
	}

	@Override
	public List<UserPicEntity> queryList(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("UserPicServiceImpl queryList map is null.");
			return null;
		}
		LOGGER.info("Entering UserPicServiceImpl queryList...");
		List<UserPicEntity> list = userPicDao.queryList(map);
		LOGGER.info("Exiting UserPicServiceImpl queryList...");
		return list;
	}

	@Override
	public PageList<UserPicEntity> queryPage(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("UserPicServiceImpl queryList page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserPicServiceImpl queryPage...");
		PageList<UserPicEntity> list = userPicDao.queryPage(page, map);
		LOGGER.info("Exiting UserPicServiceImpl queryPage...");
		return list;
	}

	@Override
	public List<UserPicEntity> queryAll() {
		LOGGER.info("Entering UserPicServiceImpl queryAll...");
		List<UserPicEntity> list = userPicDao.queryAll();
		LOGGER.info("Exiting UserPicServiceImpl queryAll...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userPicDao.getMaxId();
	}

	/**
	 * 根据用户ID修改用户图片
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public int updateUserPicByUserId(UserPicEntity entity) {
		return userPicDao.updateUserPicByUserId(entity);
	}

	@Override
	public int getMaxGroupId() {
		return userPicDao.getMaxGroupId();
	}

	/**
	 * 更改用户头像
	 */
	@Override
	public Map<String, Object> updateHeadPortrait(String userId, String userName, String headPortrait) {
		LOGGER.info("Entering UserPicServiceImpl updateHeadPortrait...  userId = :{}, headPortrait = :{} ", userId,
				headPortrait);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(userId)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "userId不能为空");
			return resultMap;
		}
		if (StringUtils.isEmpty(userName)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "userName不能为空");
			return resultMap;
		}
		if (StringUtils.isEmpty(headPortrait)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "头像不能为空");
			return resultMap;
		}
		UserPicEntity userPicEntity = new UserPicEntity();
		userPicEntity.setActive(true);//查activiti = 1
		userPicEntity.setPicUse(CommonConst.DIGIT_ONE);//头像
		userPicEntity.setUserId(userId);
		userPicEntity.setPicUrl(CommonConst.LVDAO_OSSPATH + headPortrait);
		userPicEntity.setUpdateUserId(userId);
		userPicEntity.setUpdateTime(new Date());
		try {
			// 判断是否有存头像
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("picUse", CommonConst.DIGIT_ONE);
			List<UserPicEntity> list = this.queryList(param);
			if (list == null || list.size() == CommonConst.DIGIT_ZERO) {
				userPicEntity.setActive(true);
				userPicEntity.setCreateTime(new Date());
				userPicEntity.setId(StringUtil.produceUUID());
				userPicEntity.setPicUse(CommonConst.DIGIT_ONE);
				userPicEntity.setUserName(userName);
				int insertResult = this.insert(userPicEntity);
				if (insertResult > CommonConst.DIGIT_ZERO) {
					resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
					resultMap.put(CommonConst.RESPONSE_MESSAGE, "更新头像成功");
					return resultMap;
				}
			} else {
				int updateUserPicByUserId = this.updateUserPicByUserId(userPicEntity);
				if (updateUserPicByUserId > CommonConst.DIGIT_ZERO) {
					resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
					resultMap.put(CommonConst.RESPONSE_MESSAGE, "更新头像成功");
					return resultMap;
				}
			}

		} catch (Exception e) {
			throw new RuntimeException("更新头像异常");
		}
		resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		resultMap.put(CommonConst.RESPONSE_MESSAGE, "更新头像失败");
		return resultMap;
	}

}
