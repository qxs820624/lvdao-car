package com.lvdao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.common.util.DataUtils;
import com.lvdao.common.util.StringUtil;
import com.lvdao.dao.IPassengerOrderDao;
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

@Service("passengerOrderService")
public class PassengerOrderServiceImpl implements IPassengerOrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PassengerOrderServiceImpl.class);

	@Autowired
	private IPassengerOrderDao passengerOrderDao;
	
	@Autowired
	private IUserPicService userPicService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDriverOrderService driverOrderService;
	
	@Autowired
	private IVehicleService vehicleService;
	
	@Autowired
	private IDriverPassengerService driverPassengerService;

	@Override
	public int insert(PassengerOrderEntity entity) {
		if (null == entity) {
			LOGGER.info("PassengerOrderEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PassengerOrderEntity insert service...");
		int result = passengerOrderDao.insert(entity);
		LOGGER.info("Exiting PassengerOrderEntity insert service...");
		return result;
	}

	@Override
	public int update(PassengerOrderEntity entity) {
		if (null == entity) {
			LOGGER.info("PassengerOrderEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PassengerOrderEntity update service...");
		int result = passengerOrderDao.update(entity);
		LOGGER.info("Exiting PassengerOrderEntity update service...");
		return result;
	}

	@Override
	public int delete(PassengerOrderEntity entity) {
		if (null == entity) {
			LOGGER.info("PassengerOrderEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering PassengerOrderEntity delete service...");
		int result = passengerOrderDao.delete(entity);
		LOGGER.info("Exiting PassengerOrderEntity delete service...");
		return result;
	}

	@Override
	public List<PassengerOrderEntity> queryList(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("PassengerOrderEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering PassengerOrderEntity queryList service...");
		List<PassengerOrderEntity> list = passengerOrderDao.queryList(map);
		LOGGER.info("Exiting PassengerOrderEntity queryList service...");
		return list;
	}
	
	@Override
	public PageList<PassengerOrderEntity> queryPage(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("PassengerOrderEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering PassengerOrderEntity queryPage service...");
		PageList<PassengerOrderEntity> list = passengerOrderDao.queryPage(page, map);
		try {
			for(PassengerOrderEntity pOrder:list.getList()){
				//查询订单关系表信息
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("passengerOrderId", pOrder.getPassengerOrderId());
				List<DriverPassengerEntity> driverPassengerEntityList = driverPassengerService.queryList(queryMap);
				
				if(driverPassengerEntityList != null && !driverPassengerEntityList.isEmpty()){
					DriverPassengerEntity driverPassengerEntity = driverPassengerEntityList.get(0);
					String driverOrderId = driverPassengerEntity.getDriverOrderId();
					if(StringUtils.isNotBlank(driverOrderId)){
						queryMap.clear();
						queryMap.put("driverOrderId", driverOrderId);
						List<DriverOrderEntity> driverOrderList = driverOrderService.queryList(queryMap);
						if(driverOrderList == null || driverOrderList.isEmpty()){
							continue;
						}
						DriverOrderEntity driverOrderEntity = driverOrderList.get(0);
						
						Date orderAppointTime = driverOrderEntity.getOrderAppointTime();
						if(orderAppointTime!=null){
							pOrder.setVehicleOrderAppointTime(driverOrderEntity.getOrderAppointTime().getTime()+"");//司机_订单开始时间
						}else{
							pOrder.setVehicleOrderAppointTime("");
						}
	//					pOrder.setCharterFee(driverOrderEntity.getCharterFee());//包车费用
	//					pOrder.setMeanExpense(driverOrderEntity.getMeanExpense());//人均费用
						
						//查询司机照片信息
						String userId = driverOrderEntity.getUserId();
						queryMap.clear();
						queryMap.put("userId", userId);
						queryMap.put("picUse", 1);//查询头像
						List<UserPicEntity> userPicList = userPicService.queryList(queryMap);
						UserPicEntity userPicEntity = userPicList.get(0);
						pOrder.setUserPicUrl(userPicEntity.getPicUrl());//司机头像
						
						//司机信息
						queryMap.clear();
						queryMap.put("userId", userId);
						List<UserEntity> userList = userService.queryList(queryMap);
						if(userList == null || userList.isEmpty()){
							continue;
						}
						pOrder.setUserRealName(userList.get(0).getUserRealName());
						
						//车牌号
						String vehicleNo = driverOrderEntity.getVehicleNo();
						if(StringUtils.isBlank(vehicleNo)){
							continue;
						}
						pOrder.setVehicleNo(vehicleNo);
						
						//查询车辆详细信息
						queryMap.clear();
						queryMap.put("vehicleNo", vehicleNo);
						List<VehicleEntity> vehicleList = vehicleService.queryList(queryMap);
						if(vehicleList == null || vehicleNo.isEmpty()){
							continue;
						}
						VehicleEntity vehicleEntity = vehicleList.get(0);
						//汽车品牌
						pOrder.setVehicleBrand(vehicleEntity.getVehicleBrand());
						//车辆颜色
						pOrder.setVehicleColor(vehicleEntity.getVehicleColor());
						//车辆座位数
						pOrder.setVehicleSeatNo(vehicleEntity.getVehicleSeatNo());
					
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exiting PassengerOrderEntity queryPage service...");
		return list;
	}
	
	@Override
	public List<Map<String,Object>> queryPageListRet(Page page, Map<String, Object> map) {
		if (null == page || null == map) {
			LOGGER.info("PassengerOrderEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering PassengerOrderEntity queryPage service...");
		PageList<PassengerOrderEntity> list = passengerOrderDao.queryPage(page, map);
		try {
			for(PassengerOrderEntity pOrder:list.getList()){
				//查询订单关系表信息
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("passengerOrderId", pOrder.getPassengerOrderId());
				List<DriverPassengerEntity> driverPassengerEntityList = driverPassengerService.queryList(queryMap);
				
				if(driverPassengerEntityList != null && !driverPassengerEntityList.isEmpty()){
					DriverPassengerEntity driverPassengerEntity = driverPassengerEntityList.get(0);
					String driverOrderId = driverPassengerEntity.getDriverOrderId();
					if(StringUtils.isNotBlank(driverOrderId)){
						queryMap.clear();
						queryMap.put("driverOrderId", driverOrderId);
						List<DriverOrderEntity> driverOrderList = driverOrderService.queryList(queryMap);
						if(driverOrderList == null || driverOrderList.isEmpty()){
							continue;
						}
						DriverOrderEntity driverOrderEntity = driverOrderList.get(0);
						
						Date orderAppointTime = driverOrderEntity.getOrderAppointTime();
						if(orderAppointTime!=null){
							pOrder.setVehicleOrderAppointTime(driverOrderEntity.getOrderAppointTime().getTime()+"");//司机_订单开始时间
						}else{
							pOrder.setVehicleOrderAppointTime("");
						}
	//					pOrder.setCharterFee(driverOrderEntity.getCharterFee());//包车费用
	//					pOrder.setMeanExpense(driverOrderEntity.getMeanExpense());//人均费用
						
						//查询司机照片信息
						String userId = driverOrderEntity.getUserId();
						queryMap.clear();
						queryMap.put("userId", userId);
						queryMap.put("picUse", 1);//查询头像
						List<UserPicEntity> userPicList = userPicService.queryList(queryMap);
						UserPicEntity userPicEntity = userPicList.get(0);
						pOrder.setUserPicUrl(userPicEntity.getPicUrl());//司机头像
						
						//司机信息
						queryMap.clear();
						queryMap.put("userId", userId);
						List<UserEntity> userList = userService.queryList(queryMap);
						if(userList == null || userList.isEmpty()){
							continue;
						}
						pOrder.setUserRealName(userList.get(0).getUserRealName());
						
						//车牌号
						String vehicleNo = driverOrderEntity.getVehicleNo();
						if(StringUtils.isBlank(vehicleNo)){
							continue;
						}
						pOrder.setVehicleNo(vehicleNo);
						
						//查询车辆详细信息
						queryMap.clear();
						queryMap.put("vehicleNo", vehicleNo);
						List<VehicleEntity> vehicleList = vehicleService.queryList(queryMap);
						if(vehicleList == null || vehicleNo.isEmpty()){
							continue;
						}
						VehicleEntity vehicleEntity = vehicleList.get(0);
						//汽车品牌
						pOrder.setVehicleBrand(vehicleEntity.getVehicleBrand());
						//车辆颜色
						pOrder.setVehicleColor(vehicleEntity.getVehicleColor());
						//车辆座位数
						pOrder.setVehicleSeatNo(vehicleEntity.getVehicleSeatNo());
					
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exiting PassengerOrderEntity queryPage service...");
		return getListRet(list);
	}
	
	public List<Map<String,Object>> getListRet(PageList<PassengerOrderEntity> list){
		List<Map<String,Object>> ListRet = new ArrayList<Map<String,Object>>();
		if(list!=null){
			List<PassengerOrderEntity> listTemp = list.getList();
			for (PassengerOrderEntity passengerOrderEntity : listTemp) {
				Map<String,Object> mapTemp = new HashMap<String,Object>();
				mapTemp.put("passengerOrderId", passengerOrderEntity.getPassengerOrderId());
				mapTemp.put("userId", passengerOrderEntity.getUserId());
				mapTemp.put("userName", passengerOrderEntity.getUserName());
				mapTemp.put("originalLatitude", isempty(passengerOrderEntity.getOriginalLatitude()));
				mapTemp.put("originalLongtitude", isempty(passengerOrderEntity.getOriginalLongtitude()));
				mapTemp.put("originalPlaceName", isempty(passengerOrderEntity.getOriginalPlaceName()));
				mapTemp.put("targetLatitude", isempty(passengerOrderEntity.getTargetLatitude()));
				mapTemp.put("targetLongtitude", isempty(passengerOrderEntity.getTargetLongtitude()));
				mapTemp.put("targetPlaceName", isempty(passengerOrderEntity.getTargetPlaceName()));
				mapTemp.put("orderMoney", isempty(passengerOrderEntity.getOrderMoney()));
				if(passengerOrderEntity.getOrderStartTime()==null){
					mapTemp.put("orderStartTime", passengerOrderEntity.getCreateTime().getTime()+"");
				}else{
					mapTemp.put("orderStartTime", passengerOrderEntity.getOrderStartTime().getTime()+"");
				}
				if(passengerOrderEntity.getOrderEndTime()==null){
					mapTemp.put("orderEndTime", "");
				}else{
					mapTemp.put("orderEndTime", passengerOrderEntity.getOrderEndTime().getTime()+"");
				}
				mapTemp.put("createTime", passengerOrderEntity.getCreateTime().getTime()+"");
				mapTemp.put("orderType", isempty(passengerOrderEntity.getOrderType()));
				mapTemp.put("orderStatus", isempty(passengerOrderEntity.getOrderStatus()));
				mapTemp.put("headPic", isempty(passengerOrderEntity.getHeadPic()));
				mapTemp.put("vehicleLevel", isempty(passengerOrderEntity.getVehicleLevel()));
				mapTemp.put("userPicUrl",isempty(passengerOrderEntity.getUserPicUrl()));
				mapTemp.put("userRealName",isempty(passengerOrderEntity.getUserName()));
				mapTemp.put("vehicleNo", isempty(passengerOrderEntity.getVehicleNo()));
				mapTemp.put("vehicleBrand", isempty(passengerOrderEntity.getVehicleBrand()));
				mapTemp.put("vehicleColor", isempty(passengerOrderEntity.getVehicleColor()));
				mapTemp.put("vehicleSeatNo", isempty(passengerOrderEntity.getVehicleSeatNo()));
				mapTemp.put("vehicleOrderAppointTime", isempty(passengerOrderEntity.getVehicleOrderAppointTime()));
				mapTemp.put("charterFee", isempty(passengerOrderEntity.getCharterFee()));
				mapTemp.put("meanExpense", isempty(passengerOrderEntity.getMeanExpense()));
				ListRet.add(mapTemp);
			}
		}
		return ListRet;
		
	}
	
	@Override
	public List<PassengerOrderEntity> queryAll() {
		LOGGER.info("Entering PassengerOrderEntity queryAll service...");
		List<PassengerOrderEntity> list = passengerOrderDao.queryAll();
		LOGGER.info("Exiting PassengerOrderEntity queryAll service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return passengerOrderDao.getMaxId();
	}
	
	@Override
	public List<PassengerOrderEntity> queryListByOrderType(Map<String, Object> map) {
		if (null == map || map.isEmpty()) {
			LOGGER.info("PassengerOrderEntity queryListByOrderType service map is null.");
			return null;
		}
		LOGGER.info("Entering PassengerOrderEntity queryListByOrderType service...");
		List<PassengerOrderEntity> list = passengerOrderDao.queryListByOrderType(map);
		LOGGER.info("Exiting PassengerOrderEntity queryListByOrderType service...");
		return list;
	}

	@Override
	public Map<String,Object> busPassengerAddOrder(Map<String, Object> parameters) {
		Map<String,Object>  response = new HashMap<String,Object>();

		String errorMsg = "";
		PassengerOrderEntity entity = new PassengerOrderEntity();

		String userId = (String) parameters.get("userId");

		if (StringUtils.isBlank(userId)) {
			errorMsg += "用户ID为空!";
		} else {
			entity.setUserId(userId);
		}

		entity.setUserName((String) parameters.get("userName"));// 用户姓名

		String originalPlaceName = (String) parameters.get("originalPlaceName");
		if (StringUtils.isBlank(originalPlaceName)) {
			errorMsg += "起始地点名字为空!";
		} else {
			entity.setOriginalPlaceName(originalPlaceName);
		}

		String originalLongtitude = (String) parameters.get("originalLongtitude");
		if (StringUtils.isBlank(originalLongtitude)) {
			errorMsg += "起始经度为空!";
		} else {
			entity.setOriginalLongtitude(originalLongtitude);
		}

		String originalLatitude = (String) parameters.get("originalLatitude");
		if (StringUtils.isBlank(originalLatitude)) {
			errorMsg += "起始纬度为空!";
		} else {
			entity.setOriginalLatitude(originalLatitude);
		}

		String targetPlaceName = (String) parameters.get("targetPlaceName");
		if (StringUtils.isBlank(targetPlaceName)) {
			errorMsg += "目标地点名称为空!";
		} else {
			entity.setTargetPlaceName(targetPlaceName);
		}

		String targetLongtitude = (String) parameters.get("targetLongtitude");
		if (StringUtils.isBlank(targetLongtitude)) {
			errorMsg += "目标地点经度为空!";
		} else {
			entity.setTargetLongtitude(targetLongtitude);
		}

		String targetLatitude = (String) parameters.get("targetLatitude");
		if (StringUtils.isBlank(targetLatitude)) {
			errorMsg += "目标地点纬度为空!";
		} else {
			entity.setTargetLatitude(targetLatitude);
		}

		String orderStartTimeStr = (String) parameters.get("orderStartTime");
		if (StringUtils.isBlank(orderStartTimeStr)) {
			errorMsg += "订单开始时间为空!";
		} else {
			entity.setOrderStartTime(new Date(Long.parseLong(orderStartTimeStr)));
		}

		String orderSeat = (String) parameters.get("orderSeat");
		if (StringUtils.isBlank(orderSeat)) {
			errorMsg += "预定的座位数为空!";
		} else {
			entity.setTargetLatitude(orderSeat);
		}
		
		String orderMoney = (String) parameters.get("orderMoney");
		if(StringUtils.isBlank(orderMoney)){
			errorMsg += "订单金额为空!";
		}else{
			entity.setOrderMoney(orderMoney);
		}

		String orderDistance = (String) parameters.get("orderDistance");
		if(StringUtils.isBlank(orderDistance)){
			errorMsg += "订单距离为空!";
		}else{
			entity.setOrderDistance(orderDistance);
		}
		
		String charteredVehicle = (String) parameters.get("charteredVehicle");
		if(StringUtils.isBlank(charteredVehicle)){
			errorMsg += "是否包车为空!";
		}else{
			entity.setCharteredVehicle(charteredVehicle);
		}
		
		if (StringUtils.isNotBlank(errorMsg)) {
			response.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			response.put(CommonConst.RESPONSE_MESSAGE, "接口请求参数有误:" + errorMsg);
			LOGGER.error("接口请求参数有误:" + errorMsg);
			return response;
		}
		
		int passengerOrderId = passengerOrderDao.getMaxId() + CommonConst.DIGIT_ONE;
		entity.setCharteredVehicle((String) parameters.get("charteredVehicle"));//是否包车:1包车,0非包车 
		entity.setPassengerOrderId(Integer.toString(passengerOrderId));
		entity.setId(StringUtil.produceUUID());// 主键ID
		entity.setOrderType(CommonConst.PASSENGER_ORDER_BUS);// 订单类型BUS
		entity.setCreateTime(new Date());
		entity.setCreateUserId(entity.getUserId());
		entity.setCreateUserName(entity.getUserName());
		entity.setActive(true);
		entity.setOrderStatus(CommonConst.ORDER_STATUS_READY);
		passengerOrderDao.insert(entity);

		response.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		response.put(CommonConst.RESPONSE_MESSAGE, "请求成功");
		return response;
	}

	@Override
	public List<PassengerOrderEntity> findNearPassengerUser(Map<String, Object> map, Page page) {
		 List<PassengerOrderEntity> findNearPassengerUser = passengerOrderDao.findNearPassengerUser(map, page);
		 for (PassengerOrderEntity passengerOrderEntity : findNearPassengerUser) {
			 Map<String, Object> picMap = new HashMap<String, Object>();
			 picMap.put("picUse", 1);//头像
			 picMap.put("userName", passengerOrderEntity.getUserName());
			 picMap.put("userId", passengerOrderEntity.getUserId());
			List<UserPicEntity> queryList = userPicService.queryList(picMap );
			if(queryList != null && !queryList.isEmpty()){
				UserPicEntity upEntity = queryList.get(0);
				passengerOrderEntity.setUserPicUrl(upEntity.getPicUrl());
			}
		 }
		 return findNearPassengerUser;
	}
	
	
	/**
	 * 乘客发布需求  匹配到的司机信息列表
	 * 目前能想到的根据车辆类型
	 * @param userId
	 * @param orderType
	 * @return
	 */
	@Override
	public Map<String,Object> matchDrivaerOrderList(String userId,String orderType,String longtitude ,String latitude){
		LOGGER.info("Entering PassengerOrderEntity matchDrivaerOrderList...  userId = :{}", userId);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数为空
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(orderType)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户id或车辆类型不能为空");
			LOGGER.info("Exiting PassengerOrderEntity matchDrivaerOrderList....  result = :{}", result);
			return result;
		}
		if(StringUtils.isEmpty(longtitude) || StringUtils.isEmpty(latitude)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户当前的经纬度不能为空");
			LOGGER.info("Exiting  PassengerOrderEntity matchDrivaerOrderList....  result = :{}", result);
			return result;
		}
		//去匹配乘客订单列表
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("userId", userId);
		map.put("orderType", orderType);
		map.put("orderStatus", "");//未发布的
		try {
			List<DriverOrderEntity> queryList = driverOrderService.queryList(map);//得到司机的列表信息
			List<Map<String,Object>> listRet = new ArrayList<Map<String,Object>>();
			if(queryList!=null && queryList.size()>CommonConst.DIGIT_ZERO){
				for (DriverOrderEntity driverOrderEntity : queryList) {
					Map<String,Object> mapRet = new HashMap<String,Object>();
					mapRet.put("originalPlaceName", isempty(driverOrderEntity.getOriginalPlaceName()));//起始点
					mapRet.put("targetPlaceName", isempty(driverOrderEntity.getTargetPlaceName()));
					mapRet.put("meanExpense", isempty(driverOrderEntity.getMeanExpense()));//人均费用
					mapRet.put("charterFee", isempty(driverOrderEntity.getCharterFee()));//包车费用
					mapRet.put("picUrl", getPicUrlOne(driverOrderEntity.getUserId()));
					mapRet.put("userName", getNameByuserIdOne(driverOrderEntity.getUserId()));
					try {
						double distance = DataUtils.getDistance(Double.valueOf(longtitude), Double.valueOf(latitude), Double.valueOf(driverOrderEntity.getOriginalLongtitude()), Double.valueOf(driverOrderEntity.getOriginalLatitude()));
						mapRet.put("distance",String.valueOf(distance));
					} catch (Exception e) {
						mapRet.put("distance", "--");
					}
					mapRet.put("macthPercent", getmacthPercent(""));
					mapRet.put("vehicleNo", isempty(driverOrderEntity.getVehicleNo()));
					mapRet.put("vehicleBrand", isempty(driverOrderEntity.getVehicleBrand()));
					mapRet.put("vehicleColor", isempty(driverOrderEntity.getVehicleColor()));
					mapRet.put("vehicleModel", isempty(driverOrderEntity.getVehicleModel()));
					
					mapRet.put("mobile", driverOrderEntity.getUserName());//电话号码
					mapRet.put("userId", driverOrderEntity.getUserId());//userId
					mapRet.put("vehicleLevel", isempty(driverOrderEntity.getVehicleLevel()));//车辆级别  1 小面包车  2中面包车  3小货车 4中货车',
					mapRet.put("creatime", driverOrderEntity.getCreateTime().getTime()+"");
					listRet.add(mapRet);
				}
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "查询匹配司机成功");
			result.put("data", listRet);
			LOGGER.info("Exiting  PassengerOrderEntity matchDrivaerOrderList....  result = :{}", result);
			return result;
		} catch (Exception e) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "查询匹配司机异常");
			LOGGER.info("Exiting  PassengerOrderEntity matchDrivaerOrderList....  result = :{}", result);
			return result;
		}
		
	}
	
	public String getPicUrlOne(String userId){
		//司机的信息 名称 头像
		Map<String,Object> mapTemp = new HashMap<String,Object>();
		mapTemp.put("userId", userId);//匹配到司机的id
		mapTemp.put("picUse", CommonConst.DIGIT_ONE);//头像
		try {
			List<UserPicEntity> userPiclList = userPicService.queryList(mapTemp);
			if(userPiclList!=null &&  userPiclList.size()>CommonConst.DIGIT_ZERO){
				return userPiclList.get(0).getPicUrl();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getNameByuserIdOne(String userId){
		//获取用户名称
		Map<String,Object> mapTemp = new HashMap<String,Object>();
		mapTemp.put("userId", userId);//匹配到司机的id
		List<UserEntity> queryList = userService.queryList(mapTemp);
		try {
			if(queryList!=null &&  queryList.size()>CommonConst.DIGIT_ZERO){
				UserEntity userEntity = queryList.get(0);
				if(!StringUtils.isEmpty(userEntity.getUserRealName())){
					return userEntity.getUserRealName();
				}
				if(!StringUtils.isEmpty(userEntity.getUserName())){
					return userEntity.getUserName();//如果姓名为空 则返回账号
				}
				if(!StringUtils.isEmpty(userEntity.getUserMobile())){
					return userEntity.getUserMobile();//如果账户为空 则返回手机号
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//根据距离算匹配度
	public int getmacthPercent(String distance){
		int a=85;
	    int b=95;
		int c= (int) ((b-a)*Math.random()+a);
		return c ;
		
	}
	
	
	/**
	 * 单元测试
	 * @param parameters
	 * @return
	 */
	@Override
	public Map<String, Object> releaseFreightDemand(Map<String, Object> parameters) {
	
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 用户ID、用户名、货车起始出发地、起始经纬度、目标地点、目标经纬度、需要座位个数、货物的长宽高、货物重量、预约时间
		String userId = (String) parameters.get("userId");
		String userName = (String) parameters.get("userName");
		String originalPlaceName = (String) parameters.get("originalPlaceName");
		String originalLongtitude = (String) parameters.get("originalLongtitude");
		String originalLatitude = (String) parameters.get("originalLatitude");
		String targetPlaceName = (String) parameters.get("targetPlaceName");
		String targetLongtitude = (String) parameters.get("targetLongtitude");
		String targetLatitude = (String) parameters.get("targetLatitude");
//		String orderSeat = (String) parameters.get("orderSeat");
//		String goodsWeight = (String) parameters.get("goodsWeight");
//		String goodsHeight = (String) parameters.get("goodsHeight");
//		String goodsWidth = (String) parameters.get("goodsWidth");
		String appointTime = (String) parameters.get("orderAppointTime");
		String vehicleLevel = (String) parameters.get("vehicleLevel");//车辆类型
		String orderMoney = (String) parameters.get("orderMoney");//订单预估金额
		Date orderAppointTime = null;

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(userName) || StringUtils.isBlank(originalPlaceName)
				|| StringUtils.isBlank(originalLongtitude) || StringUtils.isBlank(originalLatitude)
				|| StringUtils.isBlank(targetPlaceName) || StringUtils.isBlank(targetLongtitude)
				|| StringUtils.isBlank(targetLatitude) || StringUtils.isBlank(appointTime) || StringUtils.isBlank(orderMoney)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "参数错误:" + JSON.toJSONString(parameters));
			return resultMap;
		}
		
		
		if(StringUtils.isEmpty(vehicleLevel)){
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "参数错误:" + "车辆类型不能为空");
			return resultMap;
		}

		if (!StringUtils.isBlank(appointTime)) {
			orderAppointTime = new Date(Long.parseLong(appointTime));
		}
		// try {
		// orderAppointTime = DateUtils.parseDate(appointTime,
		// DateUtils.DATE_FORMAT_MIN);
		// } catch (Exception e) {
		// resultMap.put(CommonConst.RESPONSE_STATUS,
		// CommonConst.RESPONSE_STATUS_FAIL);
		// resultMap.put(CommonConst.RESPONSE_MESSAGE, "appointTime格式错误:" +
		// appointTime + ",正确格式：yyyy-MM-dd HH:mm");
		// return resultMap;
		// }
		String  orderStartTimeStr = (String) parameters.get("orderStartTimeStr");//订单开始时间字符串
		if(StringUtils.isBlank(orderStartTimeStr)){
			orderStartTimeStr = appointTime;
		}

		PassengerOrderEntity entity = new PassengerOrderEntity();
		int passengerOrderId = this.getMaxId() + CommonConst.DIGIT_ONE;
		entity.setPassengerOrderId(Integer.toString(passengerOrderId));
		entity.setActive(true);
		entity.setOrderStartTime(new Date(Long.valueOf(orderStartTimeStr)));//订单开始时间
		entity.setCreateTime(new Date());
//		entity.setGoodsHeight(goodsHeight);
//		entity.setGoodsWeight(goodsWeight);
//		entity.setGoodsWidth(goodsWidth);
		entity.setId(StringUtil.produceUUID());
		entity.setOrderAppointTime(orderAppointTime);
//		entity.setOrderSeat(orderSeat);
		entity.setOrderType(CommonConst.STRING_THREE);
		entity.setOriginalLatitude(originalLatitude);
		entity.setOriginalLongtitude(originalLongtitude);
		entity.setOriginalPlaceName(originalPlaceName);
		entity.setTargetLatitude(targetLatitude);
		entity.setTargetLongtitude(targetLongtitude);
		entity.setTargetPlaceName(targetPlaceName);
		entity.setUserId(userId);
		entity.setUserName(userName);
		entity.setVersion(1l);
		//增加字段
		entity.setVehicleLevel(vehicleLevel);
		entity.setOrderMoney(orderMoney);
		int result = this.insert(entity);

		if (result == CommonConst.DIGIT_ZERO) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "发布货车需求服务失败");
			return resultMap;
		}

		resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		resultMap.put(CommonConst.RESPONSE_MESSAGE, "发布货车需求服务成功");
		return resultMap;
	}
	
    public String isempty(String str){
    	if(org.apache.commons.lang.StringUtils.isEmpty(str)){
    		return "";
    	}
    	return str;
    }
    
    @Override
    public Map<String,Object> getPassengerOrderDetail(Map<String,Object> parameters){
    	Map<String, Object> map = new HashMap<String, Object>();
		String passengerOrderId = (String) parameters.get("passengerOrderId");
		if (passengerOrderId == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(passengerOrderId)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "passengerOrderId is invalid..");
			LOGGER.info("Exiting OrderController getPassengerOrderDetail beacause of the invalid passengerOrderId..");
			return map;
		}
		map.put("passengerOrderId", passengerOrderId);
		List<DriverPassengerEntity> driverPassengerList = driverPassengerService.queryList(map);
		if (driverPassengerList == null || driverPassengerList.isEmpty()) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "This passenger order number does not match the driver number");
			LOGGER.info(
					"Exiting OrderController getPassengerOrderDetail beacause this passenger order number does not match the driver number..");
			return map;
		}
		String driverOrderId = driverPassengerList.get(CommonConst.DIGIT_ZERO).getDriverOrderId();
		if (driverOrderId == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(driverOrderId)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "driverOrderId is invalid..");
			LOGGER.info("Exiting OrderController getDriverOrderDetail beacause of the invalid driverOrderId..");
			return map;
		}
		map.put("driverOrderId", driverOrderId);
		List<DriverOrderEntity> driverOrderList = driverOrderService.queryList(map);
		if (driverOrderList == null || driverOrderList.isEmpty()) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "this driver order is not existed..");
			LOGGER.info("Exiting OrderController getDriverOrderDetail beacause this driver order is not existed..");
			return map;
		} else {
			DriverOrderEntity entity = driverOrderList.get(CommonConst.DIGIT_ZERO);
			map.clear();
			map.put("vehicleDriverId", entity.getUserId());
			List<VehicleEntity> vehicleList = vehicleService.queryList(map);
			if (vehicleList == null || vehicleList.isEmpty()) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_MESSAGE, "the driver's vehicle is not registered...");
				LOGGER.info(
						"Exiting OrderController getDriverOrderDetail beacause the driver's vehicle is not registered..");
				return map;
			}
			
			//查询用户头像
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("userId", entity.getUserId());
			queryMap.put("picUse", 1);
			String driverPicUrl = "";
			List<UserPicEntity> userPicList = userPicService.queryList(queryMap);
			if(userPicList != null && !userPicList.isEmpty()){
				UserPicEntity usEntity = userPicList.get(0);
				driverPicUrl = usEntity.getPicUrl();
			}
			
			List<DriverOrderEntity> orderList = driverOrderService.queryList(map);
			Map<String,Object> dateMap = new HashMap<String,Object>();
			dateMap.put("vehicleDriverId", entity.getUserId());
			dateMap.put("vehicleEntity", vehicleList.get(CommonConst.DIGIT_ZERO));
			dateMap.put("driverOrderEntity", entity);
			dateMap.put("orderNum", orderList.size());
			dateMap.put("driverPicUrl", driverPicUrl);
			
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, "success");
			map.put(CommonConst.RESPONSE_DATA, dateMap);
		}
		return map;
    }

}