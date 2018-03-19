package com.lvdao.car.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);
	
	/**
	 * 收款通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoMoney", method = RequestMethod.GET)
	public ModelAndView infoMoney(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/infoMoney");
		return mav;
	}
	
	/**
	 * 消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoPage", method = RequestMethod.GET)
	public ModelAndView infoPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/infoPage");
		return mav;
	}
	
	/**
	 * 系统通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/infoSystem", method = RequestMethod.GET)
	public ModelAndView infoSystem(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/infoSystem");
		return mav;
	}
}
