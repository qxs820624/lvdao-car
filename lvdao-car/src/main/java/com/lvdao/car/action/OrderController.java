package com.lvdao.car.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	/**
	 * 确认订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderForm", method = RequestMethod.GET)
	public ModelAndView orderForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/orderForm");
		return mav;
	}
	
	/**
	 * 提交成功
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadSucceed", method = RequestMethod.GET)
	public ModelAndView uploadSucceed(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/uploadSucceed");
		return mav;
	}
	
	/**
	 * 上传付款凭证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadVoucher", method = RequestMethod.GET)
	public ModelAndView uploadVoucher(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/uploadVoucher");
		return mav;
	}
}
