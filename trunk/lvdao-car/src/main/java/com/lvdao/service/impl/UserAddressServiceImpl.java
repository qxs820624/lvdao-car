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
import com.lvdao.dao.IUserAddressDao;
import com.lvdao.entity.UserAddressEntity;
import com.lvdao.service.IUserAddressService;

@Service("userAddressService")
public class UserAddressServiceImpl implements IUserAddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressServiceImpl.class);
	
	@Autowired
	private IUserAddressDao userAddressDao;
	
	@Override
	public int insert(UserAddressEntity entity) {
		if(null == entity) {
			LOGGER.info("UserAddressEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserAddressEntity insert service...");
		int result = userAddressDao.insert(entity);
		LOGGER.info("Exiting UserAddressEntity insert service...");
		return result;
	}
	
	@Override
	public int update(UserAddressEntity entity) {
		if(null == entity) {
			LOGGER.info("UserAddressEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserAddressEntity update service...");
		int result = userAddressDao.update(entity);
		LOGGER.info("Exiting UserAddressEntity update service...");
		return result;
	}
	
	@Override
	public int delete(UserAddressEntity entity) {
		if(null == entity) {
			LOGGER.info("UserAddressEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering UserAddressEntity delete service...");
		int result = userAddressDao.delete(entity);
		LOGGER.info("Exiting UserAddressEntity delete service...");
		return result;
	}
	
	@Override
	public List<UserAddressEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("UserAddressEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering UserAddressEntity queryList service...");
		List<UserAddressEntity> list = userAddressDao.queryList(map);
		LOGGER.info("Exiting UserAddressEntity queryList service...");
		return list;
	}
	
	@Override
	public PageList<UserAddressEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("UserAddressEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering UserAddressEntity queryPage service...");
		PageList<UserAddressEntity> list = userAddressDao.queryPage(page, map);
		LOGGER.info("Exiting UserAddressEntity queryPage service...");
		return list;
	}
	

	@Override
	public List<UserAddressEntity> queryAll() {
		LOGGER.info("Entering UserAddressEntity queryAll service...");
		List<UserAddressEntity> list = userAddressDao.queryAll();
		LOGGER.info("Exiting UserAddressEntity queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return userAddressDao.getMaxId();
	}
	
	@Override
	public List<UserAddressEntity> queryFrequencyAddress(Map<String,Object> map){
		LOGGER.info("Entering UserAddressEntity queryFrequencyAddress service...");
		List<UserAddressEntity> list = userAddressDao.queryFrequencyAddress(map);
		LOGGER.info("Exiting UserAddressEntity queryFrequencyAddress service...");
		return list;
	}
	
	@Override
	public Map<String,Object> queryFrequencyAddress(String userId,String frequency){
		LOGGER.info("Entering UserAddressServiceImpl queryFrequencyAddress...  userId = :{}, frequency = :{}", userId, frequency);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		int frequencyRet = 0;//常用地址出现的次数
		if(StringUtils.isEmpty(frequency)){
			frequencyRet = CommonConst.DIGIT_TEN;
		}else{
			frequencyRet=Integer.valueOf(frequency);
		}
		if(StringUtils.isEmpty(userId)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户Id不能为空");
			LOGGER.info("Exiting UserAddressServiceImpl queryFrequencyAddress...  result = :{}", result);
			return result;
		}
		//执行sql
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("frequency", frequencyRet);
		try {
			List<UserAddressEntity> list = this.queryFrequencyAddress(map);
			List<String> addressList = new ArrayList<String>();
			if(list != null && list.size() > CommonConst.DIGIT_ZERO) {
				for (UserAddressEntity userAddressEntity : list) {
					addressList.add(userAddressEntity.getAddress());
				}
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "常用地址查询成功");
			result.put("addressList", addressList);
			LOGGER.info("Exiting UserAddressServiceImpl queryFrequencyAddress...  result = :{}", result);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("查询异常");
		}
	}
	
}
