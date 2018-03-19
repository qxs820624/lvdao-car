package com.lvdao.car.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
   /**
    * 添加银行卡
    * 
    * @param request
    * @return
    */
	@RequestMapping(value = "/addBank", method = RequestMethod.GET)
	public ModelAndView addBank(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/addBank");
		return mav;
	}
	
	/**
	 * 现金提现
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cashWithdraw", method = RequestMethod.GET)
	public ModelAndView orderList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/cashWithdraw");
		return mav;
	}
	
	/**
	 * 收入明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/incomeDetail", method = RequestMethod.GET)
	public ModelAndView incomeDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/incomeDetail");
		return mav;
	}	
	
	/**
	 * 选择投资项目
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/investment", method = RequestMethod.GET)
	public ModelAndView investment(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/investment");
		return mav;
	}
	
	/**
	 * 个人中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public ModelAndView personal(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/personal");
		return mav;
	}
	
	/**
	 * 推荐人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/reference", method = RequestMethod.GET)
	public ModelAndView reference(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/reference");
		return mav;
	}
	
	/**
	 * 返还明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/returnDetail", method = RequestMethod.GET)
	public ModelAndView returnDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/returnDetail");
		return mav;
	}
	
	/**
	 * 奖励明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rewardDetail", method = RequestMethod.GET)
	public ModelAndView rewardDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/rewardDetail");
		return mav;
	}
	
	
}
