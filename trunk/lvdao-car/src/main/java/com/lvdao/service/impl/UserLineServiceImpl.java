package com.lvdao.service.impl;

import java.util.ArrayList;
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
import com.lvdao.dao.IUserLineDao;
import com.lvdao.entity.UserLineEntity;
import com.lvdao.service.IUserLineService;

@Service("userLineService")
public class UserLineServiceImpl implements IUserLineService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserLineServiceImpl.class);
	
	@Autowired
	private IUserLineDao userLineDao;
	
	@Override
	public int insert(UserLineEntity entity) {
		if(null == entity) {
			LOGGER.info("UserLineEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserLineEntity insert service...");
		int result = userLineDao.insert(entity);
		LOGGER.info("Exiting UserLineEntity insert service...");
		return result;
	}
	
	@Override
	public int update(UserLineEntity entity) {
		if(null == entity) {
			LOGGER.info("UserLineEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserLineEntity update service...");
		int result = userLineDao.update(entity);
		LOGGER.info("Exiting UserLineEntity update service...");
		return result;
	}
	
	@Override
	public int delete(UserLineEntity entity) {
		if(null == entity) {
			LOGGER.info("UserLineEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserLineEntity delete service...");
		int result = userLineDao.delete(entity);
		LOGGER.info("Exiting UserLineEntity delete service...");
		return result;
	}
	
	@Override
	public List<UserLineEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserLineEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering UserLineEntity queryList service...");
		List<UserLineEntity> list = userLineDao.queryList(map);
		LOGGER.info("Exiting UserLineEntity queryList service...");
		return list;
	}
	
	@Override
	public PageList<UserLineEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("UserLineEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserLineEntity queryPage service...");
		PageList<UserLineEntity> list = userLineDao.queryPage(page, map);
		LOGGER.info("Exiting UserLineEntity queryPage service...");
		return list;
	}
	

	@Override
	public List<UserLineEntity> queryAll() {
		LOGGER.info("Entering UserLineEntity queryAll service...");
		List<UserLineEntity> list = userLineDao.queryAll();
		LOGGER.info("Exiting UserLineEntity queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userLineDao.getMaxId();
	}
	
	@Override
	public List<UserLineEntity> queryFrequencyLine(Map<String,Object> map){
		LOGGER.info("Entering UserLineEntity queryFrequencyLine service...");
		List<UserLineEntity> list = userLineDao.queryFrequencyLine(map);
		LOGGER.info("Exiting UserLineEntity queryFrequencyLine service...");
		return list;
	}
	
	@Override
	public Map<String,Object> queryFrequencyLine(String userId,String frequency){
		LOGGER.info("Entering UserLineServiceImpl queryFrequencyLine...  userId = :{}, frequency = :{}", userId, frequency);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		int frequencyRet = 0;//常用线路出现的次数
		if(StringUtils.isEmpty(frequency)){
			frequencyRet = CommonConst.DIGIT_TEN;
		}else{
			frequencyRet=Integer.valueOf(frequency);
		}
		if(StringUtils.isEmpty(userId)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户Id不能为空");
			LOGGER.info("Exiting UserLineServiceImpl queryFrequencyLine...  result = :{}", result);
			return result;
		}
		//执行sql
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("frequency", frequencyRet);
		try {
			List<UserLineEntity> list = this.queryFrequencyLine(map);
			List<String> lineList = new ArrayList<String>();
			if(list != null && list.size() > CommonConst.DIGIT_ZERO) {
				for (UserLineEntity userLineEntity : list) {
					lineList.add(userLineEntity.getOriginalLineName()+"-"+userLineEntity.getTargetLineName());
				}
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "常用线路查询成功");
			result.put("lineList", lineList);
			LOGGER.info("Exiting UserLineServiceImpl queryFrequencyLine...  result = :{}", result);
			return result;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException("查询异常");
		}
	}
	
	
}
