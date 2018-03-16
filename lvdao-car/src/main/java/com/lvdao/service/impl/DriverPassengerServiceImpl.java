package com.lvdao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.common.util.ChatIMUtil;
import com.lvdao.common.util.DataUtils;
import com.lvdao.common.util.StringUtil;
import com.lvdao.dao.IDriverPassengerDao;
import com.lvdao.entity.DriverOrderEntity;
import com.lvdao.entity.DriverPassengerEntity;
import com.lvdao.entity.PassengerOrderEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.entity.VehicleEntity;
import com.lvdao.service.IDriverOrderService;
import com.lvdao.service.IDriverPassengerService;
import com.lvdao.service.IPassengerOrderService;
import com.lvdao.service.IUserPicService;
import com.lvdao.service.IUserService;
import com.lvdao.service.IVehicleService;

@Service("driverPassengerService")
public class DriverPassengerServiceImpl implements IDriverPassengerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverPassengerServiceImpl.class);

	@Autowired
	private IDriverPassengerDao driverPassengerDao;

	@Autowired
	private IPassengerOrderService passengerOrderService;

	@Autowired
	private IDriverOrderService driverOrderService;

	@Autowired
	private IVehicleService vehicleService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserPicService userPicService;

	@Override
	public int insert(DriverPassengerEntity entity) {
		if (null == entity || StringUtils.isBlank(entity.getDriverOrderId())) {
			LOGGER.info("driverPassengerDao insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering driverPassengerDao insert service...");
		int result = driverPassengerDao.insert(entity);
		LOGGER.info("Exiting driverPassengerDao insert service...");
		return result;
	}

	@Override
	public int update(DriverPassengerEntity entity) {
		if (null == entity || StringUtils.isBlank(entity.getDriverOrderId())) {
			LOGGER.info("driverPassengerDao insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering driverPassengerDao update service...");
		int result = driverPassengerDao.update(entity);
		LOGGER.info("Exiting driverPassengerDao update service...");
		return result;
	}

	@Override
	public int delete(DriverPassengerEntity entity) {
		if (null == entity || StringUtils.isBlank(entity.getDriverOrderId())) {
			LOGGER.info("driverPassengerDao insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering driverPassengerDao delete service...");
		int result = driverPassengerDao.delete(entity);
		LOGGER.info("Exiting driverPassengerDao delete service...");
		return result;
	}

	@Override
	public List<DriverPassengerEntity> queryList(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("driverPassengerDao queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering driverPassengerDao queryList service...");
		List<DriverPassengerEntity> list = driverPassengerDao.queryList(map);
		LOGGER.info("Exiting driverPassengerDao queryList service...");
		return list;
	}

	@Override
	public PageList<DriverPassengerEntity> queryPage(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("driverPassengerDao queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering driverPassengerDao queryPage service...");
		PageList<DriverPassengerEntity> list = driverPassengerDao.queryPage(page, map);
		LOGGER.info("Exiting driverPassengerDao queryPage service...");
		return list;
	}

	@Override
	public List<DriverPassengerEntity> queryAll() {
		LOGGER.info("Entering driverPassengerDao queryAll service...");
		List<DriverPassengerEntity> list = driverPassengerDao.queryAll();
		LOGGER.info("Exiting driverPassengerDao queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return driverPassengerDao.getMaxId();
	}

	@Override
	public Map<String, Object> updateOrderStatus(String passengerOrderId, String driverOrderId, String type) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(passengerOrderId) || StringUtils.isBlank(driverOrderId) || StringUtils.isBlank(type)) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "参数不完整");
			return result;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("passengerOrderId", passengerOrderId);
		map.put("active", true);
		List<PassengerOrderEntity> passengerOrderList = passengerOrderService.queryList(map);
		if (passengerOrderList == null || passengerOrderList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "乘客订单ID不存在");
			return result;
		}

		map.clear();
		map.put("driverOrderId", driverOrderId);
		map.put("active", true);
		List<DriverOrderEntity> driverOrderList = driverOrderService.queryList(map);
		if (driverOrderList == null || driverOrderList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "司机订单ID不存在");
			return result;
		}

		map.put("passengerOrderId", passengerOrderId);
		List<DriverPassengerEntity> driverPassengerList = this.queryList(map);
		if (driverPassengerList == null || driverPassengerList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该司机乘客尚未匹配");
			return result;
		}

		PassengerOrderEntity passengerOrder = passengerOrderList.get(CommonConst.DIGIT_ZERO);
		DriverOrderEntity driverOrder = driverOrderList.get(CommonConst.DIGIT_ZERO);
		DriverPassengerEntity driverPassenger = driverPassengerList.get(CommonConst.DIGIT_ZERO);

		int driverOrderUpdateResult = CommonConst.DIGIT_ZERO;
		int passengerOrderUpdateResult = CommonConst.DIGIT_ZERO;
		int driverPassengerUpdateResult = CommonConst.DIGIT_ZERO;

		// 已匹配
		if (CommonConst.STRING_THREE.equals(type)) {
			passengerOrder.setOrderStatus(CommonConst.STRING_ZERO);
			driverOrder.setOrderStatus(CommonConst.STRING_ZERO);
			passengerOrderUpdateResult = passengerOrderService.update(passengerOrder);
			driverOrderUpdateResult = driverOrderService.update(driverOrder);
			if (driverOrderUpdateResult == CommonConst.DIGIT_ZERO
					|| passengerOrderUpdateResult == CommonConst.DIGIT_ZERO) {
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "修改订单状态失败");
				return result;
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "修改订单状态成功");
			return result;
		}

		// 司机点击完成服务
		if (CommonConst.STRING_FOUR.equals(type)) {
			passengerOrder.setOrderStatus(CommonConst.STRING_ONE);
			// 完成服务记录行程结束时间
			passengerOrder.setOrderEndTime(new Date());
			driverOrder.setOrderStatus(CommonConst.STRING_ONE);
			driverPassenger.setOrderStatus(CommonConst.STRING_ONE);
			passengerOrderUpdateResult = passengerOrderService.update(passengerOrder);
			driverOrderUpdateResult = driverOrderService.update(driverOrder);
			driverPassengerUpdateResult = this.update(driverPassenger);
			if (driverOrderUpdateResult == CommonConst.DIGIT_ZERO
					|| passengerOrderUpdateResult == CommonConst.DIGIT_ZERO
					|| driverPassengerUpdateResult == CommonConst.DIGIT_ZERO) {
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "修改订单状态失败");
				return result;
			}
			
			// 环信发送扩展信息
			JSONObject extJson = new JSONObject();
			try {
				extJson.put(CommonConst.ORDER_OPERATION_STATUS, CommonConst.ORDER_OPERATION_FINISH);
				map.clear();
				map.put("userName", driverOrder.getUserName());
				map.put("picUse", CommonConst.DIGIT_ONE);
				List<UserPicEntity> userPicList = userPicService.queryList(map);
				if(userPicList != null && userPicList.size() > CommonConst.DIGIT_ZERO) {
					extJson.put("headImage", userPicList.get(CommonConst.DIGIT_ZERO).getPicUrl());
				}
				ChatIMUtil.sendMessage(passengerOrderList.get(CommonConst.DIGIT_ZERO).getUserName(), "司机已确认完成订单...", extJson);
			} catch (Exception e) {
				LOGGER.error("finish Order ChatIMUtil.sendMessage exception...");
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "修改订单状态成功");
			return result;
		}

		// 乘客点击取消
		if (CommonConst.STRING_FIVE.equals(type)) {
			passengerOrder.setOrderStatus(CommonConst.STRING_TWO);
			driverOrder.setOrderStatus(CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS);
			driverPassenger.setOrderStatus(CommonConst.STRING_TWO);
			passengerOrderUpdateResult = passengerOrderService.update(passengerOrder);
			driverOrderUpdateResult = driverOrderService.update(driverOrder);
			driverPassengerUpdateResult = this.update(driverPassenger);
			if (driverOrderUpdateResult == CommonConst.DIGIT_ZERO
					|| passengerOrderUpdateResult == CommonConst.DIGIT_ZERO
					|| driverPassengerUpdateResult == CommonConst.DIGIT_ZERO) {
				result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				result.put(CommonConst.RESPONSE_MESSAGE, "修改订单状态失败");
				return result;
			}
			
			// 环信发送扩展信息
			JSONObject extJson = new JSONObject();
			try {
				extJson.put(CommonConst.ORDER_OPERATION_STATUS, CommonConst.ORDER_OPERATION_CANCLE);
				ChatIMUtil.sendMessage(driverOrder.getUserName(), "乘客已取消订单...", extJson);
			} catch (Exception e) {
				LOGGER.error("receivePassenger ChatIMUtil.sendMessage exception...");
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "修改订单状态成功");
			return result;
		}
		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		result.put(CommonConst.RESPONSE_MESSAGE, "type不存在");
		return result;
	}

	@Override
	public synchronized Map<String, Object> receiveOrder(Map<String, Object> parameters) {
		Map<String, Object> result = new HashMap<String, Object>();
		String type = (String) parameters.get("type");
		String passengerOrderId = (String) parameters.get("passengerOrderId");
		String userName = (String) parameters.get("userName");
		String userId = (String) parameters.get("userId");
		String vehicleId = (String) parameters.get("vehicleId");
		String longitude = (String) parameters.get("longitude");
		String latitude = (String) parameters.get("latitude");

		// 校验司机是否存在
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		List<UserEntity> userList = userService.queryList(paramMap);
		if (userList == null || userList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该司机不存在");
			return result;
		}
		String userRealName = userList.get(CommonConst.DIGIT_ZERO).getUserRealName();

		// 查询该乘客订单
		paramMap.clear();
		paramMap.put("passengerOrderId", passengerOrderId);
		List<PassengerOrderEntity> passengerOrderList = passengerOrderService.queryList(paramMap);
		if (passengerOrderList == null || passengerOrderList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该乘客订单不存在");
			return result;
		}

		if (!StringUtils.isBlank(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOrderStatus())) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "该乘客订单已匹配");
			return result;
		}

		// 查询该司机下是否有车辆
		paramMap.clear();
		paramMap.put("vehicleDriverId", userId);
		paramMap.put("vehicleId", vehicleId);
		List<VehicleEntity> vehicleList = vehicleService.queryList(paramMap);
		if (vehicleList == null || vehicleList.size() == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "未添加车辆，无法接单");
			return result;
		}

		// 查询该司机最新的订单
		// paramMap.clear();
		// paramMap.put("userName", userName);
		// paramMap.put("orderType", type);
		// List<DriverOrderEntity> driverOrderList =
		// driverOrderService.queryList(paramMap);
		// if(driverOrderList == null || driverOrderList.size() ==
		// CommonConst.DIGIT_ZERO) {
		// result.put(CommonConst.RESPONSE_STATUS,
		// CommonConst.RESPONSE_STATUS_FAIL);
		// result.put(CommonConst.RESPONSE_MESSAGE, "未发布服务，无法接单");
		// return result;
		// }
		// 创建司机订单
		DriverOrderEntity entity = new DriverOrderEntity();
		String driverOrderId = driverOrderService.getMaxId() + "";
		entity.setId(StringUtil.produceUUID());
		entity.setDriverOrderId(driverOrderId);
		entity.setUserId(userId);
		entity.setUserName(userName);
		entity.setVehicleNo(vehicleList.get(CommonConst.DIGIT_ZERO).getVehicleNo());
		entity.setVehicleBrand(vehicleList.get(CommonConst.DIGIT_ZERO).getVehicleBrand());
		entity.setVehicleModel(vehicleList.get(CommonConst.DIGIT_ZERO).getVehicleModel());
		entity.setVehicleColor(vehicleList.get(CommonConst.DIGIT_ZERO).getVehicleColor());
		entity.setOrderStatus(CommonConst.ORDER_STATUS_NULL);
		entity.setOrderAppointTime(new Date());//订单预约时间
		entity.setOriginalLatitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalLatitude());
		entity.setOriginalLongtitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalLongtitude());
		entity.setOriginalPlaceName(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalPlaceName());
		entity.setTargetLatitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getTargetLatitude());
		entity.setTargetLongtitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getTargetLongtitude());
		entity.setTargetPlaceName(passengerOrderList.get(CommonConst.DIGIT_ZERO).getTargetPlaceName());
		entity.setActive(true);
		entity.setCreateUserId(userId);
		entity.setCreateUserName(userName);
		entity.setCreateTime(new Date());
		entity.setOrderType(type);
		entity.setVersion(1l);
		int insertResult = driverOrderService.insert(entity);
		if (insertResult == CommonConst.DIGIT_ZERO) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "Publishing service fail..");
			LOGGER.info("Publishing service fail..");
			return result;
		}

		DriverPassengerEntity driverPassenger = new DriverPassengerEntity();
		driverPassenger.setActive(true);
		driverPassenger.setCreateTime(new Date());
		driverPassenger.setDriverOrderId(driverOrderId);
		driverPassenger.setId(StringUtil.produceUUID());
		driverPassenger.setOrderStatus(CommonConst.STRING_ZERO);
		driverPassenger.setOriginalLatitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalLatitude());
		driverPassenger.setOriginalLongtitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalLongtitude());
		driverPassenger.setOriginalPlaceName(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalPlaceName());
		driverPassenger.setTargetLatitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getTargetLatitude());
		driverPassenger.setTargetLongtitude(passengerOrderList.get(CommonConst.DIGIT_ZERO).getTargetLongtitude());
		driverPassenger.setTargetPlaceName(passengerOrderList.get(CommonConst.DIGIT_ZERO).getTargetPlaceName());
		driverPassenger.setPassengerOrderId(passengerOrderList.get(CommonConst.DIGIT_ZERO).getPassengerOrderId());
		driverPassenger.setVersion(1l);
		insertResult = this.insert(driverPassenger);
		if (insertResult > CommonConst.DIGIT_ZERO) {
			//updateOrderStatus(passengerOrderId, entity.getDriverOrderId(), CommonConst.STRING_THREE);
			PassengerOrderEntity passengerOrderEntity = passengerOrderList.get(CommonConst.DIGIT_ZERO);
			passengerOrderEntity.setOrderStatus(CommonConst.STRING_ZERO);
			passengerOrderService.update(passengerOrderEntity);
		}

		// 计算司机与乘客的距离
		double distance = DataUtils.getDistance(Double.parseDouble(longitude), Double.parseDouble(latitude),
				Double.parseDouble(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalLongtitude()),
				Double.parseDouble(passengerOrderList.get(CommonConst.DIGIT_ZERO).getOriginalLatitude()));

		// 环信发送扩展信息
		JSONObject extJson = new JSONObject();
		try {
			extJson.put(CommonConst.ORDER_OPERATION_STATUS, CommonConst.ORDER_OPERATION_MATCH);
			extJson.put("userId", entity.getUserId());
			extJson.put("userName", entity.getUserName());
			extJson.put("vehicleNo", entity.getVehicleNo());
			extJson.put("vehicleBrand", entity.getVehicleBrand());
			extJson.put("vehicleModel", entity.getVehicleModel());
			extJson.put("vehicleColor", entity.getVehicleColor());
			extJson.put("longitude", longitude);
			extJson.put("latitude", latitude);
			extJson.put("distance", distance / 1000);
			extJson.put("passengerOrderId", passengerOrderList.get(CommonConst.DIGIT_ZERO).getPassengerOrderId());
			extJson.put("driverOrderId", driverOrderId);
			if (StringUtils.isBlank(userRealName)) {
				userRealName = CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS;
			} else {
				userRealName = userRealName.substring(CommonConst.DIGIT_ZERO, CommonConst.DIGIT_ONE) + "师傅";
			}
			extJson.put("userRealName", userRealName);
			
			paramMap.clear();
			paramMap.put("userName", userName);
			paramMap.put("picUse", CommonConst.DIGIT_ONE);
			List<UserPicEntity> userPicList = userPicService.queryList(paramMap);
			if(userPicList != null && userPicList.size() > CommonConst.DIGIT_ZERO) {
				extJson.put("headImage", userPicList.get(CommonConst.DIGIT_ZERO).getPicUrl());
			}
			ChatIMUtil.sendMessage(passengerOrderList.get(CommonConst.DIGIT_ZERO).getUserName(), "司机正在赶来，请耐心等待...", extJson);
		} catch (Exception e) {
			LOGGER.error("DriverPassengerServiceImpl receiveOrder ChatIMUtil.sendMessage exception...");
		}

		result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		result.put(CommonConst.RESPONSE_MESSAGE, "接单成功");
		result.put(CommonConst.RESPONSE_DATA, driverOrderId);
		return result;
	}

}
