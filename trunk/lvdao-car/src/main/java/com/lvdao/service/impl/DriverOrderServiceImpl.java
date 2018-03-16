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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.common.util.DataUtils;
import com.lvdao.common.util.StringUtil;
import com.lvdao.dao.IDriverOrderDao;
import com.lvdao.entity.DriverOrderEntity;
import com.lvdao.entity.PassengerOrderEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.entity.VehicleEntity;
import com.lvdao.service.IDriverOrderService;
import com.lvdao.service.IPassengerOrderService;
import com.lvdao.service.IUserPicService;
import com.lvdao.service.IUserService;
import com.lvdao.service.IVehicleService;

@Service("driverOrderService")
public class DriverOrderServiceImpl  implements IDriverOrderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverOrderServiceImpl.class);
	
	@Autowired
	private IDriverOrderDao driverOrderDao;
	
	@Autowired
	private IPassengerOrderService passengerOrderService;
	
	@Autowired
	private IUserPicService userPicService;
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 车辆操作的service
	 */
	@Autowired
	@Qualifier("vehicleService")
	private IVehicleService vehicleService;
	
	@Override
	public int insert(DriverOrderEntity entity) {
		if(null == entity) {
			LOGGER.info("DriverOrderEntity insert service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering DriverOrderEntity insert service...");
		int result = driverOrderDao.insert(entity);
		LOGGER.info("Exiting DriverOrderEntity insert service...");
		return result;
	}
	
	@Override
	public int update(DriverOrderEntity entity) {
		if(null == entity) {
			LOGGER.info("DriverOrderEntity update service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering DriverOrderEntity update service...");
		int result = driverOrderDao.update(entity);
		LOGGER.info("Exiting DriverOrderEntity update service...");
		return result;
	}
	
	@Override
	public int delete(DriverOrderEntity entity) {
		if(null == entity) {
			LOGGER.info("DriverOrderEntity delete service entity is null.");
			return CommonConst.DIGIT_ZERO;
		}
		LOGGER.info("Entering DriverOrderEntity delete service...");
		int result = driverOrderDao.delete(entity);
		LOGGER.info("Exiting DriverOrderEntity delete service...");
		return result;
	}
	
	@Override
	public List<DriverOrderEntity> queryList(Map<String, Object> map) {
		if(null == map || map.isEmpty()) {
			LOGGER.info("DriverOrderEntity queryList service map is null.");
			return null;
		}
		LOGGER.info("Entering DriverOrderEntity queryList service...");
		List<DriverOrderEntity> list = driverOrderDao.queryList(map);
		LOGGER.info("Exiting DriverOrderEntity queryList service...");
		return list;
	}
	
	@Override
    public Map<String,Object> getDriverOrderDetail(Map<String,Object> parameters){
    	Map<String, Object> map = new HashMap<String, Object>();
		String driverOrderId = (String) parameters.get("driverOrderId");
		if (driverOrderId == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(driverOrderId)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "driverOrderId is invalid..");
			LOGGER.info("Exiting OrderController getDriverOrderDetail beacause of the invalid driverOrderId..");
			return map;
		}
		map.put("driverOrderId", driverOrderId);
		List<DriverOrderEntity> driverOrderList = this.queryList(map);
		if (driverOrderList == null || driverOrderList.isEmpty()) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_MESSAGE, "this driver order is not existed..");
			LOGGER.info("Exiting OrderController getDriverOrderDetail beacause this driver order is not existed..");
			return map;
		} else {
			DriverOrderEntity entity = driverOrderList.get(CommonConst.DIGIT_ZERO);
			map.clear();
			//需要传入条件 byzhaoming
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
			
			List<DriverOrderEntity> orderList = this.queryList(map);//不需要订单数
			Map<String ,Object> dateMap = new HashMap<String ,Object>();
			dateMap.put("vehicleDriverId", entity.getUserId());
			dateMap.put("driverPicUrl", driverPicUrl);
			dateMap.put("vehicleEntity", vehicleList.get(CommonConst.DIGIT_ZERO));
			dateMap.put("driverOrderEntity", entity);
			dateMap.put("orderNum", orderList.size());
			
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, "success");
			map.put(CommonConst.RESPONSE_DATA,dateMap);
		}
			return map;
    }
	
	@Override
	public PageList<DriverOrderEntity> queryPage(Page page,
			Map<String, Object> map) {
		if(null == page || null == map ) {
			LOGGER.info("DriverOrderEntity queryPage service page or map is null.");
			return null;
		}
		LOGGER.info("Entering DriverOrderEntity queryPage service...");
		PageList<DriverOrderEntity> list = driverOrderDao.queryPage(page, map);
		LOGGER.info("Exiting DriverOrderEntity queryPage service...");
		return list;
	}
	

	@Override
	public List<DriverOrderEntity> queryAll() {
		LOGGER.info("Entering DriverOrderEntity queryAll service...");
		List<DriverOrderEntity> list = driverOrderDao.queryAll();
		LOGGER.info("Exiting DriverOrderEntity queryAll service...");
		return list;
	}
	
	@Override
	public List<DriverOrderEntity> queryListByOrderType(Map<String,Object> map) {
		LOGGER.info("Entering DriverOrderEntity queryListByOrderType service...");
		List<DriverOrderEntity> list = driverOrderDao.queryListByOrderType(map);
		LOGGER.info("Exiting DriverOrderEntity queryListByOrderType service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return driverOrderDao.getMaxId();
	}

	@Override
	public Map<String,Object> addBusDriverOrder(Map<String, Object> parameters) {
		Map<String,Object> response = new HashMap<String,Object>();

		String errorMsg = "";
		
		DriverOrderEntity entity = new  DriverOrderEntity();
		
		String userId = (String) parameters.get("userId");
		if(StringUtils.isBlank(userId)){
			errorMsg += "用户ID不能为空!";
		}else{
			entity.setUserId(userId);
		}
		
		String userName = (String) parameters.get("userName");
		if(StringUtils.isBlank(userName)){
			errorMsg += "用户名不能为空!";
		}else{
			entity.setUserName(userName);
		}
		
		String vehicleNo = (String) parameters.get("vehicleNo");
		if(StringUtils.isBlank(vehicleNo)){
			errorMsg += "车牌号不能为空!";
		}else{
			entity.setVehicleNo(vehicleNo);
		}
		
		String seatNo = (String) parameters.get("seatNo");
		if(StringUtils.isBlank(seatNo)){
			errorMsg += "限载人数不能为空!";
		}else{
			entity.setSeatNo(seatNo);
		}
		
		String orderStartTimeStr = (String) parameters.get("orderStartTime");
		if (StringUtils.isBlank(orderStartTimeStr)) {
			errorMsg += "订单开始时间为空!";
		} else {
			entity.setOrderStartTime(new Date(Long.parseLong(orderStartTimeStr)));
		}
		
		String meanExpense = (String) parameters.get("meanExpense");
		if(StringUtils.isBlank(meanExpense)){
			errorMsg += "人均费用为空!";
		}else {
			entity.setMeanExpense(meanExpense);
		}
		
		String charterFee = (String) parameters.get("charterFee");
		if(StringUtils.isBlank(charterFee)){
			errorMsg += "包车费用为空!";
		}else {
			entity.setCharterFee(charterFee);
		}
		
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
		
		if (StringUtils.isNotBlank(errorMsg)) {
			response.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			response.put(CommonConst.RESPONSE_MESSAGE, "接口请求参数有误:" + errorMsg);
			LOGGER.error("接口请求参数有误:" + errorMsg);
			return response;
		}
		int driverOrderId = this.getMaxId() + CommonConst.DIGIT_ONE;
		entity.setOrderDistance((String) parameters.get("orderDistance"));//起始地点产生的距离公里数
		entity.setDriverOrderId(driverOrderId+"");
		entity.setActive(true);
		entity.setId(StringUtil.produceUUID());
		entity.setOrderType(CommonConst.DRIVER_ORDER_BUS);
		entity.setCreateTime(new Date());
		entity.setCreateUserId(entity.getUserId());
		entity.setOrderStatus(CommonConst.ORDER_STATUS_NULL);//订单状态
		
		//生成订单时,添加车辆颜色
		if(StringUtils.isNotBlank(vehicleNo)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("vehicleNo",vehicleNo);
			List<VehicleEntity> vehicleItemList = vehicleService.queryList(map);
			if(vehicleItemList != null && !vehicleItemList.isEmpty()){
				entity.setVehicleColor(vehicleItemList.get(0).getVehicleColor());//添加车辆颜色
			}
		}
		this.insert(entity);
		
		response.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		response.put(CommonConst.RESPONSE_MESSAGE, "成功");
		return response;
	}
	
	@Override
	public Map<String,Object> publishOrderList(String userId){
		LOGGER.info("Entering DriverOrderEntity publishOrderList...  userId = :{}", userId);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数是否为空
		if(StringUtils.isEmpty(userId)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户id不能为空");
			LOGGER.info("Exiting DriverOrderEntity publishOrderList...  result = :{}", result);
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
//		map.put("orderStatus", "");
//		map.put("orderType", CommonConst.DIGIT_TWO);//只有巴士 小货车
		try {
			//司机发布中心
			List<DriverOrderEntity> driverOrderList = this.queryListByOrderType(map);
			//乘客发布中心
			List<PassengerOrderEntity> passengerOrderList = passengerOrderService.queryListByOrderType(map);
			//返回data
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("driverOrderList", driverOrderList);
			data.put("passengerOrderList", passengerOrderList);
			//返回
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "发布中心查询成功");
			result.put("data", data);
			LOGGER.info("Exiting DriverOrderEntity publishOrderList...  result = :{}", result);
			return result;
		} catch (Exception e) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "查询异常");
			LOGGER.info("Exiting DriverOrderEntity publishOrderList...  result = :{}", result);
			return result;
		}
		//return map;
	}
	
	/**
	 * 司机发布服务  匹配到的乘客信息列表
	 * 目前能想到的根据车辆类型
	 * @param userId
	 * @param orderType
	 * @return
	 */
	@Override
	public Map<String,Object> matchPassengerOrderList(String userId,String orderType,String longtitude ,String latitude){
		LOGGER.info("Entering DriverOrderEntity matchPassengerOrderList...  userId = :{}", userId);
		Map<String,Object> result = new HashMap<String,Object>();
		//判断参数为空
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(orderType)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户id或车辆类型不能为空");
			LOGGER.info("Exiting DriverOrderEntity matchPassengerOrderList...  result = :{}", result);
			return result;
		}
		if(StringUtils.isEmpty(longtitude) || StringUtils.isEmpty(latitude)){
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "用户当前的经纬度不能为空");
			LOGGER.info("Exiting DriverOrderEntity matchPassengerOrderList...  result = :{}", result);
			return result;
		}
		//去匹配乘客订单列表
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("userId", userId);
		map.put("orderType", orderType);
		map.put("orderStatus", "");//未发布的
		try {
			List<PassengerOrderEntity> queryList = passengerOrderService.queryList(map);
			List<Map<String,Object>> listRet = new ArrayList<Map<String,Object>>();
			if(queryList!=null && queryList.size()>CommonConst.DIGIT_ZERO){
				for (PassengerOrderEntity passengerOrderEntity : queryList) {
					Map<String,Object> mapRet = new HashMap<String,Object>();
					mapRet.put("originalPlaceName", isempty(passengerOrderEntity.getOriginalPlaceName()));//起始点
					mapRet.put("targetPlaceName", isempty(passengerOrderEntity.getTargetPlaceName()));
					mapRet.put("orderMoney", isempty(passengerOrderEntity.getOrderMoney()));
					mapRet.put("createTime", passengerOrderEntity.getCreateTime().getTime()+"");
					mapRet.put("picUrl", getPicUrlOne(passengerOrderEntity.getUserId()));
					mapRet.put("userName", getNameByuserIdOne(passengerOrderEntity.getUserId()));
					try {
						double distance = DataUtils.getDistance(Double.valueOf(longtitude), Double.valueOf(latitude), Double.valueOf(passengerOrderEntity.getOriginalLongtitude()), Double.valueOf(passengerOrderEntity.getOriginalLatitude()));
						mapRet.put("distance", String.valueOf(distance));
					} catch (Exception e) {
						mapRet.put("distance", "--");
					}
					mapRet.put("macthPercent", getmacthPercent(""));
					mapRet.put("mobile", passengerOrderEntity.getUserName());//电话号码
					mapRet.put("userId", passengerOrderEntity.getUserId());//userId
					mapRet.put("vehicleLevel", isempty(passengerOrderEntity.getVehicleLevel()));//车辆级别
					
					listRet.add(mapRet);
				}
			}
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			result.put(CommonConst.RESPONSE_MESSAGE, "查询匹配乘客成功");
			result.put("data", listRet);
			LOGGER.info("Exiting DriverOrderEntity matchPassengerOrderList...  result = :{}", result);
			return result;
		} catch (Exception e) {
			result.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			result.put(CommonConst.RESPONSE_MESSAGE, "查询匹配乘客异常");
			LOGGER.info("Exiting DriverOrderEntity matchPassengerOrderList...  result = :{}", result);
			return result;
		}
		
	}
	
	public String getPicUrlOne(String userId){
		//乘客的信息 名称 头像
		Map<String,Object> mapTemp = new HashMap<String,Object>();
		mapTemp.put("userId", userId);//匹配到乘客的id
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
		mapTemp.put("userId", userId);//匹配到乘客的id
		try {
		List<UserEntity> queryList = userService.queryList(mapTemp);
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
	
	public static void main(String[] args) {
//        int a=85;
//        int b=95;
//        // Random random = new Random();
//        //(int)((b-a)*Math.random()＋a);
//        int c= (int) ((b-a)*Math.random()+a);
//        System.out.println(c);
    }
	
	  public String isempty(String str){
	    	if(org.apache.commons.lang.StringUtils.isEmpty(str)){
	    		return "";
	    	}
	    	return str;
	    }
	  
	  
	  @Override
	  public Map<String,Object> getDriverOrderList(Map<String,Object> parameters){
		    Map<String,Object> resultMap = new HashMap<String,Object>();
		    String userId = (String) parameters.get("userId");
			String startIndex = (String) parameters.get("startIndex");
			String pageSize = (String) parameters.get("pageSize");
			
			if (userId == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(userId)) {
				resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				resultMap.put(CommonConst.RESPONSE_MESSAGE, "userId is null");
				return resultMap;
			}
			if (startIndex == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(startIndex)) {
				startIndex = CommonConst.STRING_ONE;
			}
			if (pageSize == null || CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS.equals(pageSize)) {
				pageSize = CommonConst.STRING_TEN;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			Page page = new Page();
			page.setPageNo(Integer.parseInt(startIndex));
			page.setPageSize(Integer.parseInt(pageSize));
			try {
				PageList<DriverOrderEntity> driverOrderList = this.queryPage(page, map);
				List<DriverOrderEntity> list = driverOrderList.getList();
				List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
				for (DriverOrderEntity driverOrderEntity : list) {
					Map<String, Object> mapTEMP = new HashMap<String, Object>();
					if(driverOrderEntity.getOrderStartTime()==null){
						mapTEMP.put("orderStartTime", driverOrderEntity.getCreateTime().getTime()+"");
					}else{
						mapTEMP.put("orderStartTime", driverOrderEntity.getOrderStartTime().getTime()+"");
					}
					if(driverOrderEntity.getOrderEndTime()==null){
						mapTEMP.put("orderEndTime", "");
					}else{
						mapTEMP.put("orderEndTime", driverOrderEntity.getOrderEndTime().getTime()+"");
					}
					mapTEMP.put("createTime", driverOrderEntity.getCreateTime().getTime()+"");
					mapTEMP.put("originalPlaceName", isempty(driverOrderEntity.getOriginalPlaceName()));
					mapTEMP.put("originalLatitude", isempty(driverOrderEntity.getOriginalLatitude()));
					mapTEMP.put("originalLongtitude", isempty(driverOrderEntity.getOriginalLongtitude()));
					mapTEMP.put("targetPlaceName", isempty(driverOrderEntity.getTargetPlaceName()));
					mapTEMP.put("targetLatitude", isempty(driverOrderEntity.getTargetLatitude()));
					mapTEMP.put("targetLongtitude", isempty(driverOrderEntity.getTargetLongtitude()));
					mapTEMP.put("orderStatus", isempty(driverOrderEntity.getOrderStatus()));
					//传给订单详情的数据
					mapTEMP.put("userId", driverOrderEntity.getUserId());
					mapTEMP.put("mobile", driverOrderEntity.getUserName());//电话
					mapTEMP.put("picUrl", getPicUrlOne(driverOrderEntity.getUserId()));//头像
					mapTEMP.put("userName", getNameByuserIdOne(driverOrderEntity.getUserId()));//名字
					mapTEMP.put("vehicleNo", isempty(driverOrderEntity.getVehicleNo()));
					mapTEMP.put("vehicleBrand", isempty(driverOrderEntity.getVehicleBrand()));
					mapTEMP.put("vehicleColor", isempty(driverOrderEntity.getVehicleColor()));
					mapTEMP.put("vehicleModel", isempty(driverOrderEntity.getVehicleModel()));
					mapTEMP.put("charterFee", isempty(driverOrderEntity.getCharterFee()));//包车费用
					mapTEMP.put("meanExpense", isempty(driverOrderEntity.getMeanExpense()));//人均费用
					mapTEMP.put("orderType",isempty(driverOrderEntity.getOrderType()));//订单类型:1 小汽车 2 巴士 3大货车'
					mapTEMP.put("vehicleLevel", isempty(driverOrderEntity.getVehicleLevel()));
					mapTEMP.put("driverOrderId", driverOrderEntity.getDriverOrderId());//订单id
					data.add(mapTEMP);
				}
				resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				resultMap.put(CommonConst.RESPONSE_MESSAGE, "success...");
				resultMap.put(CommonConst.RESPONSE_DATA, data);
				return resultMap;
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "fail...");
			return resultMap;
	  }
}