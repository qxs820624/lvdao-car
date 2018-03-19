package com.lvdao.web.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	 * 车辆信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/carInfo", method = RequestMethod.GET)
	public ModelAndView carInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/carInfo");
		return mav;
	}
}
