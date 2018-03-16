package com.lvdao.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.entity.VehicleEntity;
import com.lvdao.service.IVehicleService;

/**
 * 车辆Controller
 */
@Controller
@RequestMapping(value = "/vehicle")
public class VehicleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private IVehicleService vehicleService;



	/**
	 * 说明：跳转到车辆信息展示页
	 *
	 * @author fqb
	 * @since 2018-03-08 13:21
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/forwardVehicle", method = RequestMethod.GET)
	public ModelAndView orderList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("vehicle/vehicle_list");
		return mav;
	}

	/**
	 * 说明：车辆分页查询
	 *
	 * @author fqb
	 * @since 2018-03-09 15:21
	 * @param
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/vehicleList", method = RequestMethod.POST)
	@ResponseBody
	public String vehicleList(HttpServletRequest request, @RequestParam String aoData) {
		LOGGER.info("VehicleController vehicleList... ");
		Object user = request.getSession().getAttribute(CommonConst.SESSION_USER);
		//	未登录返回空数据user
		List<VehicleEntity> vehicleList=new ArrayList<>();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sEcho", CommonConst.STRING_ONE);
		jsonObj.put("iTotalRecords", CommonConst.STRING_ZERO);
		jsonObj.put("iTotalDisplayRecords", CommonConst.STRING_ZERO);
		jsonObj.put("aaData", vehicleList);
		if (user == null) {
			LOGGER.info("VehicleController vehicleList sessionUser is null... ");
				return jsonObj.toString();
		}
		//	获取请求参数
		JSONArray jsonarray = JSONArray.parseArray(aoData);
		String vehicleType = "";
		String vehicleNo = "";
		String vehicleDriverName = "";
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String sSearch = "";
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho")) {
				sEcho = obj.get("value").toString();
			} else if (obj.get("name").equals("iDisplayStart")) {
				iDisplayStart = obj.getIntValue("value");
			} else if (obj.get("name").equals("iDisplayLength")) {
				iDisplayLength = obj.getIntValue("value");
			} else if (obj.get("name").equals("sSearch")) {
				sSearch = obj.get("value").toString();
			} else if (obj.get("name").equals("vehicleType")) {
				vehicleType = obj.get("value").toString();
			} else if (obj.get("name").equals("vehicleNo")) {
				vehicleNo = obj.get("value").toString();
			} else if (obj.get("name").equals("vehicleDriverName")) {
				vehicleDriverName = obj.get("value").toString();
			}
		}

		Page page = new Page();
		page.setPageNo(iDisplayStart / iDisplayLength + 1);
		page.setPageSize(iDisplayLength);
		int allCount = 0;
		Map<String, Object> serachMap = new HashMap<String, Object>();
		if (StringUtils.isBlank(vehicleType)) {
			serachMap.put("vehicleType", null);
		} else {
			serachMap.put("vehicleType", vehicleType);
		}
		if (StringUtils.isBlank(vehicleNo)) {
			serachMap.put("vehicleNo", null);
		} else {
			serachMap.put("vehicleNo", vehicleNo);
		}
		if (StringUtils.isBlank(vehicleDriverName)) {
			serachMap.put("vehicleDriverName", null);
		} else {
			serachMap.put("vehicleDriverName", vehicleDriverName);
		}
		PageList<VehicleEntity> pageList = vehicleService.queryPage(page, serachMap);
		if (null == pageList || pageList.getList().size() > CommonConst.DIGIT_ZERO) {
			allCount = (int) pageList.getPage().getTotalCount();
		}
		jsonObj.clear();
		jsonObj.put("sEcho", sEcho);
		jsonObj.put("iTotalRecords", allCount);
		jsonObj.put("iTotalDisplayRecords", allCount);
		jsonObj.put("aaData", pageList.getList());
		return jsonObj.toString();
	}


	/**
	 * 说明：跳转到车辆详情展示页
	 *
	 * @author fqb
	 * @since 2018-03-08 16:21
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/forwardVehicleDetail", method = RequestMethod.GET)
	public ModelAndView forwardVehicleDetail(HttpServletRequest request) {
		LOGGER.info("VehicleController forwardVehicleDetail... ");
		Object user = request.getSession().getAttribute(CommonConst.SESSION_USER);
		if(null==user){
			LOGGER.info("VehicleController forwardVehicleDetail sessionUser is null... ");
			return new ModelAndView("user/user_index");
		}
		ModelAndView mav = new ModelAndView("vehicle/vehicle_detail");
		String vehicleId = request.getParameter("vehicleId");
		if (StringUtils.isBlank(vehicleId)) {
			mav.addObject(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			return mav;
		}
		Map<String, Object> searchMap=new HashMap<>();
		searchMap.put("vehicleId", vehicleId);
		searchMap.put("active", vehicleId);
		List<VehicleEntity> vehicleList = vehicleService.queryVehicleAndPhotoInfo(searchMap);
		if (null==vehicleList||vehicleList.isEmpty()) {
			mav.addObject(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			return mav;
		}
		VehicleEntity entity = vehicleList.get(CommonConst.DIGIT_ZERO);
		String vehicleType = entity.getVehicleType();
		if (CommonConst.STRING_ONE.equals(vehicleType)) {
			entity.setVehicleType("小汽车");
		}else if (CommonConst.STRING_TWO.equals(vehicleType)) {
			entity.setVehicleType("巴士");
		}else if (CommonConst.STRING_THREE.equals(vehicleType)) {
			entity.setVehicleType("大货车");
		}else{
			entity.setVehicleType("");
		}
		
		String vehicleLevel = entity.getVehicleLevel();
		if (CommonConst.STRING_ONE.equals(vehicleLevel)) {
			entity.setVehicleLevel("小面包车");
		}else if (CommonConst.STRING_TWO.equals(vehicleLevel)) {
			entity.setVehicleLevel("中面包车");
		}else if (CommonConst.STRING_THREE.equals(vehicleLevel)) {
			entity.setVehicleLevel("小货车");
		}else if (CommonConst.STRING_FOUR.equals(vehicleLevel)) {
			entity.setVehicleLevel("中货车");
		}else {
			entity.setVehicleLevel("");
		}
		mav.addObject(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		mav.addObject("entity",entity);
		return mav;
	}
	
	

	/**
	 * 说明：通过订单号查询订单数据
	 *
	 * @author xing0312
	 * @since 2017-9-19 10:14
	 * @param
	 * @return
	 */
	/*@RequestMapping(value = "/queryOrderByOrderId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryOrderByOrderId(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String orderId = request.getParameter("orderId");
		if (StringUtils.isBlank(orderId)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "获取订单号失败");
			return map;
		}

		map.put("orderId", orderId);

		List<UserOrderEntity> userOrderList = userOrderService.queryList(map);
		if (userOrderList.size() == CommonConst.DIGIT_ZERO || userOrderList.isEmpty()) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "该订单不存在");
			return map;
		}
		map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
		map.put("entity", userOrderList.get(CommonConst.DIGIT_ZERO));
		return map;
	}*/

}
