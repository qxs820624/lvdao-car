package com.lvdao.car.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lvdao.common.CommonConst;
import com.lvdao.common.enums.OrderTypeEnum;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserOrderEntity;
import com.lvdao.service.IUserOrderService;
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
	
	@Autowired
	private IUserOrderService userOrderService;

	/**
	 * 车辆信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/carInfo", method = RequestMethod.GET)
	public ModelAndView carInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/carInfo");
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", user.getUserName());
		paramMap.put("orderType", OrderTypeEnum.INVESTMENT_ORDER.getId());
		List<UserOrderEntity> userOrderList = userOrderService.queryList(paramMap);
		mav.addObject("userOrderList", userOrderList);
		int carSize = CommonConst.DIGIT_ZERO;
		if (userOrderList != null && userOrderList.size() > CommonConst.DIGIT_ZERO) {
			carSize = userOrderList.size();
		}
		mav.addObject("carSize", carSize);
		return mav;
	}
	
	/**
	 * 查询凭证信息
	 * 
	 * @author hexiang
	 * @since 2018-03-27 14:33
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewCarVoucher", method = RequestMethod.GET)
	public ModelAndView viewCarVoucher(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/viewCarVoucher");
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		String orderId = request.getParameter("orderId");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", user.getUserName());
		paramMap.put("orderType", OrderTypeEnum.INVESTMENT_ORDER.getId());
		paramMap.put("orderId", orderId);
		List<UserOrderEntity> userOrderList = userOrderService.queryList(paramMap);
		mav.addObject("userOrder", userOrderList.get(CommonConst.DIGIT_ZERO));
		return mav;
	}
	
	
	/**
	 * 我的汽车
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/myCar", method = RequestMethod.GET)
	public ModelAndView myCar(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/myCar");
		return mav;
	}
}
