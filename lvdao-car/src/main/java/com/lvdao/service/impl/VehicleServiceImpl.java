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
import com.lvdao.dao.IVehicleDao;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.entity.VehicleEntity;
import com.lvdao.service.IUserPicService;
import com.lvdao.service.IUserService;
import com.lvdao.service.IVehicleService;

@Service("vehicleService")
public class VehicleServiceImpl implements IVehicleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

	@Autowired
	private IVehicleDao vehicleDao;
	
	@Autowired
	private IUserPicService userPicService;
	
	@Autowired
	private IUserService userService;

	@Override
	public int insert(VehicleEntity entity) {
		if (null == entity) {
			LOGGER.info("VehicleEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering VehicleEntity insert service...");
		int result = vehicleDao.insert(entity);
		LOGGER.info("Exiting VehicleEntity insert service...");
		return result;
	}

	@Override
	public int update(VehicleEntity entity) {
		if (null == entity) {
			LOGGER.info("VehicleEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering VehicleEntity update service...");
		int result = vehicleDao.update(entity);
		LOGGER.info("Exiting VehicleEntity update service...");
		return result;
	}

	@Override
	public int delete(VehicleEntity entity) {
		if (null == entity) {
			LOGGER.info("VehicleEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering VehicleEntity delete service...");
		int result = vehicleDao.delete(entity);
		LOGGER.info("Exiting VehicleEntity delete service...");
		return result;
	}

	@Override
	public List<VehicleEntity> queryList(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("VehicleEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering VehicleEntity queryList service...");
		List<VehicleEntity> list = vehicleDao.queryList(map);
		LOGGER.info("Exiting VehicleEntity queryList service...");
		return list;
	}

	@Override
	public PageList<VehicleEntity> queryPage(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("VehicleEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering VehicleEntity queryPage service...");
		PageList<VehicleEntity> list = vehicleDao.queryPage(page, map);
		LOGGER.info("Exiting VehicleEntity queryPage service...");
		return list;
	}

	@Override
	public List<VehicleEntity> queryAll() {
		LOGGER.info("Entering VehicleEntity queryAll service...");
		List<VehicleEntity> list = vehicleDao.queryAll();
		LOGGER.info("Exiting VehicleEntity queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return vehicleDao.getMaxId();
	}

	@Override
	public List<VehicleEntity> findNearDriverUser(Map<String, Object> map, Page page) {
		return vehicleDao.findNearDriverUser(map, page);
	}

	@Override
	public Map<String, Object> addVehicle(Map<String, Object> parameters) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		//添加车辆信息
    	VehicleEntity vehicleEntity = new VehicleEntity();
    	vehicleEntity.setId(StringUtil.produceUUID());
    	int maxId = this.getMaxId();
    	int vetVehicleId = maxId +1;
    	vehicleEntity.setVehicleId(vetVehicleId+"");//车辆ID
    	vehicleEntity.setVehicleType(parameters.get("vehicleType")+"");//汽车类型
    	vehicleEntity.setVehicleBrand(parameters.get("vehicleBrand")+"");//汽车品牌
    	vehicleEntity.setVehicleNo(parameters.get("vehicleNo")+"");//车牌号
    	vehicleEntity.setVehicleSeatNo(parameters.get("vehicleSeatNo")+"");//汽车座位数量
    	vehicleEntity.setVehicleLoadCapacity(parameters.get("vehicleLoadCapacity")+"");//汽车载重
    	vehicleEntity.setVehicleLoadVolume(parameters.get("vehicleLoadVolume")+"");//汽车载货体积
    	vehicleEntity.setVehicleLength(parameters.get("vehicleLength")+"");//汽车长度
    	vehicleEntity.setVehicleHeight(parameters.get("vehicleHeight")+"");//汽车高度
    	vehicleEntity.setVehicleWidth(parameters.get("vehicleWidth")+"");//汽车宽度
    	vehicleEntity.setVehicleColor(parameters.get("vehicleColor")+"");//车辆颜色
    	vehicleEntity.setVehicleLevel(parameters.get("vehicleLevel")+"");//车辆等级
    	
    	String userID = parameters.get("vehicleDriverId")+"";//车主ID
    	if(StringUtils.isNotBlank(userID)){
    		//update by hexiang 
    		Map<String, Object> paramMap = new HashMap<String, Object>();
    		paramMap.put("userId", userID);
    		List<UserEntity> userList = userService.queryList(paramMap);
//    		UserEntity userEntity = userService.getUserEntityByUserId(userID);   方法return为null
    		vehicleEntity.setVehicleDriverId(userID);//车主ID
    		if(userList != null && userList.size() > CommonConst.DIGIT_ZERO){
    		 	vehicleEntity.setVehicleDriverName(userList.get(CommonConst.DIGIT_ZERO).getUserName());//车主姓名
            	vehicleEntity.setVehicleDriverMobileNo(userList.get(CommonConst.DIGIT_ZERO).getUserMobile());//车主手机号码
    		}
    	}
    	vehicleEntity.setVersion(1);
    	vehicleEntity.setCreateTime(new Date());
    	vehicleEntity.setCreateUserId(userID);
    	vehicleEntity.setActive(true);
    	int insertRet = this.insert(vehicleEntity);
		
    	if(insertRet <= CommonConst.DIGIT_ZERO){
    		result .put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
    		result.put(CommonConst.RESPONSE_MESSAGE, "添加车辆信息失败失败");
    		LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
    		return result;
    	}
    	
    	//图片信息
    	String vehiclePhotos = (String) parameters.get("vehiclePhotos");
    	
		UserPicEntity userPicEntity = new UserPicEntity();
		userPicEntity.setId(StringUtil.produceUUID());
		userPicEntity.setUserId(vehicleEntity.getVehicleId());
		userPicEntity.setUserName("车辆ID");
		int userPicMaxId = userPicService.getMaxId();
		int userPicMaxIdNext = userPicMaxId+1;
		userPicEntity.setPicId(userPicMaxIdNext+"");
		userPicEntity.setPicUrl(vehiclePhotos);
		int type = 9;//类型 1头像，2照片，3反馈，4身份证正面，5身份证反面,6驾照正面，7驾照反面，8行驶证，9车辆照片，10其他', 
		userPicEntity.setPicUse(type);
		userPicEntity.setActive(true);
		userPicEntity.setCreateUserId(vehicleEntity.getVehicleDriverId());
		userPicEntity.setCreateUserName(vehicleEntity.getVehicleDriverName());
		userPicEntity.setCreateTime(new Date());
		userPicEntity.setVersion(1);
		userPicService.insert(userPicEntity);
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		result.put(CommonConst.RESPONSE_MESSAGE, "添加车辆信息成功");
		LOGGER.info("Exiting UserServiceImpl updateUserByUserId...  result = :{}", result);
		return result;
	}

	@Override
	public List<VehicleEntity> queryVehicleAndPhotoInfo(Map<String, Object> parameters) {
		List<VehicleEntity> vehicleList = this.queryList(parameters);
		if(vehicleList == null || vehicleList.isEmpty()){
			LOGGER.info("Exiting VehicleServiceImpl queryVehicleAndPhotoInfo...result is null");
			return null;
		}
		//循环查询车辆照片的信息
		for (VehicleEntity vehicleEntity : vehicleList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", vehicleEntity.getVehicleId());
			map.put("picUse", 9);
			List<UserPicEntity> list = userPicService.queryList(map);
			if(list != null && !list.isEmpty()){
				vehicleEntity.setVehiclePhotos(list.get(0).getPicUrl());
			}
		}
		return vehicleList;
	}

	@Override
	public Map<String, Object> deleteVehicle(Map<String, Object> parameters) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String vehicleId = (String) parameters.get("vehicleId");//车辆ID
		
		VehicleEntity entity = new VehicleEntity();
		entity.setVehicleId(vehicleId);
		int deleteByVehicleId = vehicleDao.delete(entity);
		if(deleteByVehicleId >0 ){
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "成功!");
		}else{
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "删除失败!");
		}
		
		return resultMap;
	}

}