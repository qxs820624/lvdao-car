package com.lvdao.car.action;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;
import com.lvdao.common.enums.AccountEnum;
import com.lvdao.common.enums.OrderTypeEnum;
import com.lvdao.common.enums.PaymentMethodEnum;
import com.lvdao.common.util.AliyunOSSUtil;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.DictEntity;
import com.lvdao.entity.OrderTypeEntity;
import com.lvdao.entity.PictureEntity;
import com.lvdao.entity.PictureGroupEntity;
import com.lvdao.entity.UserAccountEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.entity.UserOrderEntity;
import com.lvdao.entity.UserPicEntity;
import com.lvdao.entity.UserWithdrawEntity;
import com.lvdao.service.IDictService;
import com.lvdao.service.IPictureGroupService;
import com.lvdao.service.IPictureService;
import com.lvdao.service.IUserAccountService;
import com.lvdao.service.IUserOrderService;
import com.lvdao.service.IUserPicService;
import com.lvdao.service.IUserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private IPictureService pictureService;
	
	@Autowired
	private IPictureGroupService pictureGroupService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserOrderService userOrderService;
	
	@Autowired
    private IUserAccountService userAccountService;
	
	@Autowired
	private IUserPicService userPicService;
	
	@Autowired
	private IDictService dictService;
	
	/**
	 * 确认订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderForm", method = RequestMethod.GET)
	public ModelAndView orderForm(HttpServletRequest request) {
		// 投资级别 参数要带到上传凭证页面
		String inversType = request.getParameter("inversType");

		if (null == inversType || StringUtils.isBlank(inversType)) {
			return null;
		}

		ModelAndView mav = new ModelAndView("/orderForm");
		request.getSession().removeAttribute("orderType");
		request.getSession().setAttribute("orderType", inversType);
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
		request.getSession().removeAttribute("orderType");
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
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		ModelAndView mav = new ModelAndView("/uploadVoucher");
		mav.addObject("user", user);
		String orderType = request.getParameter("orderType");
		String submitStatus = request.getParameter("submitStatus");
		if (StringUtils.isBlank(orderType) && CommonConst.STRING_ONE.equals(submitStatus)) {
			request.getSession().removeAttribute("orderType");
			mav = new ModelAndView("/uploadSucceed");
		}
		mav.addObject("orderType", orderType);
		if (!StringUtils.isBlank(user.getUserParentName())) {
			String userParentName = user.getUserParentName();
			UserEntity userEntity = userService.queryByUserName(user.getUserParentName());
			if (userEntity != null && !StringUtils.isBlank(userEntity.getUserRealName())) {
				userParentName = userEntity.getUserRealName();
			}
			mav.addObject("userParentName", userParentName);
		}
		return mav;
	}
	
	/**
	 * 加盟申请
	 * @author guotao
	 * @since 2018-03-21 10:20
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addOrzApply", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrzApply(HttpServletRequest request) {
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		Map<String, Object> map = new HashMap<String, Object>();
		if(user == null) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}
		
		//何种加盟类型 0自主加盟 1代人申购
		String addType = request.getParameter("addType");
		//支付方式
		String payMethod = request.getParameter("payMethod");
		//订单价格
		String orderType = request.getParameter("orderType");
		//打款金额(用以财务审核通过是校验所用)
		String paymentAmount = request.getParameter("paymentAmount");
		//推荐人手机号
		String recommendUserMoblile = request.getParameter("recommendMoblile");
		//加盟人手机号
		String addUserMoblile = request.getParameter("addUserMoblile");
		//打款凭证图片
		String picUrl = request.getParameter("picUrl");
		String picRealUrl = request.getParameter("picRealUrl");
		//默认银联支付
		if(null == payMethod || StringUtils.isBlank(payMethod)) {
			payMethod = PaymentMethodEnum.PAYMENT_METHOD_OFFLINE_BANK.getId();
		}
		
		if(null == addType || StringUtils.isBlank(addType)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "加盟类型未选择");
			return map;
		}
		
		if(null == picUrl || StringUtils.isBlank(picUrl)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "图片未选择");
			return map;
		}
		
		if (StringUtils.isBlank(orderType)) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "投资级别未选择");
			return map;
		}
		
		Boolean picUrlisExist = checkPicUrlisExist(picUrl);
		if(picUrlisExist == false) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "图片未上传,请先上传打款凭证");
			return map;
		}
		
		UserOrderEntity userOrderEntity = new UserOrderEntity();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dictId", "investment" + orderType);
		paramMap.put("dictGroupId", "investment_vehicle");
		List<DictEntity> dictList = dictService.queryList(paramMap);
		if (dictList == null || dictList.isEmpty()) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "该投资级别未配置");
			return map;
		}
		String orderAmount = dictList.get(CommonConst.DIGIT_ZERO).getDictValue();
		
		//代人申购  必须要加盟人手机号
		if(addType.equals(CommonConst.STRING_ONE)) {
			if(null == addUserMoblile || StringUtils.isBlank(addUserMoblile)) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "代人加盟 加盟人的手机号为空,请填写");
				return map;
			}
	     
			Boolean checkMoblile = checkMobileSecond(addUserMoblile);
			
			if(checkMoblile) {
				UserEntity mobileUser = getMobileUser(addUserMoblile);
				if(null == mobileUser) {
					map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
					map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "代人加盟 加盟人的手机号为空,请填写");
					return map;
				}
				userOrderEntity.setId(StringUtil.produceUUID());
				userOrderEntity.setOrderType(OrderTypeEnum.INVESTMENT_ORDER.getId());
				userOrderEntity.setUserId(mobileUser.getUserId());
				userOrderEntity.setUserName(mobileUser.getUserName());
				userOrderEntity.setAddMethod(OrderTypeEnum.INVESTMENT_ORDER.getId()); //订单类型
				userOrderEntity.setPaymentMethod(payMethod);//支付方式
				userOrderEntity.setOrderId(StringUtil.getOrderSn()); //订单号
				userOrderEntity.setCreateTime(new Date());
				userOrderEntity.setPaidStatus(CommonConst.DIGIT_ZERO);//订单状态
				userOrderEntity.setOrderMoney(orderAmount); //加盟金额 
				userOrderEntity.setPaymentAmount(paymentAmount); //打款金额 
				userOrderEntity.setAddMethod(CommonConst.STRING_ONE);
				userOrderEntity.setOtherPersonMobile(user.getUserName());
				userOrderEntity.setOtherPersonRealName(user.getUserRealName());
				userOrderEntity.setRemark(picRealUrl);//凭证图片 存放组id
				userOrderEntity.setActive(true);
				int insert = userOrderService.insert(userOrderEntity);
				
				if(insert == CommonConst.DIGIT_ZERO) {
					map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
					map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "加盟申请成功 请耐心等待工作人员审批");
					return map;
				} else {
					map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
					map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "加盟申请成功 请耐心等待工作人员审批");
					return map;
				}
				
			} else {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "代人加盟 加盟人的手机号未注册 请先注册加盟人账户");
				return map;
			}
		}
		
		// 自主加盟 推荐人可有可无
		if (addType.equals(CommonConst.STRING_ZERO)) {
			userOrderEntity.setId(StringUtil.produceUUID());
			userOrderEntity.setOrderType(OrderTypeEnum.INVESTMENT_ORDER.getId());
			userOrderEntity.setUserId(user.getUserId());
			userOrderEntity.setUserName(user.getUserName());
			userOrderEntity.setAddMethod(OrderTypeEnum.INVESTMENT_ORDER.getId()); // 订单类型
			userOrderEntity.setPaymentMethod(payMethod);// 支付方式
			userOrderEntity.setOrderId(StringUtil.getOrderSn()); // 订单号
			userOrderEntity.setCreateTime(new Date());
			userOrderEntity.setPaidStatus(CommonConst.DIGIT_ZERO);// 订单状态
			userOrderEntity.setOrderMoney(orderAmount); // 加盟金额
			userOrderEntity.setPaymentAmount(paymentAmount); //打款金额 
			userOrderEntity.setAddMethod(CommonConst.STRING_ZERO);
			userOrderEntity.setRemark(picRealUrl);// 凭证图片 存放组id
			userOrderEntity.setActive(true);
			int insert = userOrderService.insert(userOrderEntity);

			if (insert == CommonConst.DIGIT_ZERO) {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				map.put(CommonConst.RESPONSE_ERROR_MESSAGE, "加盟申请失败,请重试！");
				return map;
			} else {
				map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				map.put(CommonConst.RESPONSE_MESSAGE, "加盟申请成功 请耐心等待工作人员审批");
				return map;
			}
		}
		
		map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
		map.put(CommonConst.RESPONSE_MESSAGE, "加盟申请失败 请联系工作人员审批");
		return map;

	}
	
	/**
	 * 上传打款凭证
	 * @author guotao
	 * @since 2018-03-19 20:49
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String,Object> uploadPic(HttpServletRequest request) {
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(user == null) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}
		
		List<MultipartFile> fileList = getMultipartFileList(request);
		Map<String, Object> picMap = insertPic(fileList,user);
		
		String picId = (String) picMap.get("maxPicId");
		String url = (String) picMap.get("url"); 
		String picName = (String) picMap.get("picName");
		
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		if(null != picId && !StringUtils.isBlank(picId)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "上传成功");
			resultMap.put("url", url);
			resultMap.put("picName", picName);
			return resultMap;
		}else{
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "上传失败");
			return resultMap;
		}
	}
	
	
	/**
	 * 验证图片是否已上传
	 * 
	 * @param request
	 * @return
	 */
	private Boolean checkPicUrlisExist(String picName) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("picName", picName);
		List<PictureEntity> picList = pictureService.queryList(map);

		if (null == picList || picList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * 验证手机号是否存在
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkMobile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkMobile(HttpServletRequest request) {
		LOGGER.info("Entering checkMobile...");
		String userName = request.getParameter("userName");

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("userMobile", userName);
		List<UserEntity> list = userService.queryList(map);

		if (null == list || list.isEmpty()) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_IS_NOT_EXIST);
		} else {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			map.put(CommonConst.RESPONSE_MESSAGE, MessageConst.WARN_USER_IS_EXIST);
		}

		LOGGER.info("Exiting checkUserName...");
		return map;
	}
	
	
	/**
	 * 验证手机号是否存在 后台校验
	 * 
	 * @param request
	 * @return
	 */
	public Boolean checkMobileSecond(String userName) {
		LOGGER.info("Entering checkMobileSecond...");

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("userMobile", userName);
		List<UserEntity> list = userService.queryList(map);

		if (null == list || list.isEmpty()) {
			LOGGER.info("Exiting checkMobileSecond...");
			return false;
		} else {
			LOGGER.info("Exiting checkMobileSecond...");
			return true;
		}
	}
			
	/**
	 * 验证手机号反回userEntity
	 * 
	 * @param request
	 * @return
	 */
	public UserEntity getMobileUser(String userName) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("userMobile", userName);
		List<UserEntity> list = userService.queryList(map);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(CommonConst.DIGIT_ZERO);
		}
	}
	
	
	/**
	 * 获取当前请求中的文件列表
	 * 
	 * @param request
	 * @return
	 */
	protected List<MultipartFile> getMultipartFileList(HttpServletRequest request) {
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (request instanceof MultipartHttpServletRequest) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				// 检查form中是否有enctype="multipart/form-data"
				if (multipartResolver.isMultipart(request) && iter.hasNext()) {
					// 获取multiRequest 中所有的文件名
					while (iter.hasNext()) {
						// 一次遍历所有文件
						// MultipartFile file =
						// multiRequest.getFile(iter.next().toString());
						// if (file != null) {
						// files.add(file);
						// }
						// 适配名字重复的文件
						List<MultipartFile> fileRows = multiRequest.getFiles(iter.next().toString());
						if (fileRows != null && fileRows.size() != 0) {
							for (MultipartFile file : fileRows) {
								if (file != null && !file.isEmpty()) {
									files.add(file);
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.info("解析MultipartRequest错误", ex);
		}
		return files;
	}
	
	/**
	 * 插入 返回maxPicId
	 * 
	 * @param files
	 * @param sessionUserEntity
	 * @return
	 */
	public Map<String, Object> insertPic(List<MultipartFile> files, UserEntity sessionUserEntity) {
		String key = "";
		String url = "";
		String picRealName = "";
		String picType = "";
		HashMap<String, Object> map = new HashMap<String, Object>();
		String maxGroupId = Integer.toString(pictureService.getMaxId());
		if (files != null && files.size() > 0) {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					key = StringUtil.produceUUID();
					try {
						AliyunOSSUtil.uploadMultipartFile(key, file);
					} catch (OSSException e) {
						LOGGER.info("OSSException");
					} catch (ClientException e) {
						LOGGER.info("ClientException");
					} catch (FileNotFoundException e) {
						LOGGER.info("FileNotFoundException");
					}
					url = AliyunOSSUtil.getUrl(key).toString();
					String maxId = Integer.toString(pictureService.getMaxId());
					picRealName = file.getOriginalFilename();
					picType = file.getContentType();
					BigDecimal b1 = new BigDecimal(file.getSize());
					BigDecimal b2 = new BigDecimal(1048576);
					String fileSize = b1.divide(b2, CommonConst.DIGIT_NINE, BigDecimal.ROUND_HALF_UP).toString();
					// 插入图片
					PictureEntity pic = new PictureEntity();
					pic.setId(StringUtil.produceUUID());
					pic.setPicId(maxId);
					pic.setPicName(key);
					pic.setPicRealName(picRealName);
					pic.setPicUrl(url);
					pic.setPicType(picType);
					pic.setPicSize(fileSize);
					pic.setActive(true);
					pic.setCreateTime(new Date());
					// pic.setCreateUserId(sessionUserEntity.getUserId());
					// pic.setCreateUserName(sessionUserEntity.getUserName());
					int insert = pictureService.insert(pic);
					if (insert > CommonConst.DIGIT_ZERO) {
						UserPicEntity userPicEntity = new UserPicEntity();
						userPicEntity.setActive(true);
						userPicEntity.setCreateTime(new Date());
						userPicEntity.setId(StringUtil.produceUUID());
						userPicEntity.setPicId(maxId);
						userPicEntity.setPicUrl(url);
						userPicEntity.setPicUse(31);
						userPicEntity.setUserId(sessionUserEntity.getUserId());
						userPicEntity.setUserName(sessionUserEntity.getUserName());
						userPicEntity.setVersion(1);
						insert = userPicService.insert(userPicEntity);
						map.put("url", url);
						map.put("picName", key);
						LOGGER.info("图片插入成功");
					} else {
						LOGGER.info("图片插入失败");
					}
				}
			}
		}
		map.put("maxPicId", maxGroupId.toString());
		return map;
	}
	
	
}
