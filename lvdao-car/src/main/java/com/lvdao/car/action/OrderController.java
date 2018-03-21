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
import com.lvdao.common.util.AliyunOSSUtil;
import com.lvdao.common.util.StringUtil;
import com.lvdao.entity.PictureEntity;
import com.lvdao.entity.PictureGroupEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.service.IPictureGroupService;
import com.lvdao.service.IPictureService;
import com.lvdao.service.IUserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	IPictureService pictureService;
	
	@Autowired
	IPictureGroupService pictureGroupService;
	
	@Autowired
	IUserService userService;
	
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
		//UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		ModelAndView mav = new ModelAndView("/uploadVoucher");
	//	mav.addObject("user",user);
		return mav;
	}
	
	
	
	/**
	 * 上传打款凭证
	 * @author guotao
	 * @since 2018-03-19 20:49
	 * @param request
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/bindRecommendMobile", method = RequestMethod.POST)
	public Map<String,Object> bindRecommendMobile(HttpServletRequest request) {
		UserEntity user = (UserEntity) request.getSession().getAttribute(CommonConst.SESSION_USER);
		String recommendMobile = request.getParameter("recommendMobile");
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(user == null) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}
		
		String userParentId = user.getUserParentId();
		
		//如果没有查到该用户有推荐人 则允许用户自己新增
		if(null == userParentId || StringUtils.isBlank(userParentId)) {
			user .setUserParentId(recommendMobile);
			
			
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "申购成功,请耐心等待审批结果");
			return resultMap;
		}else {
			
		}
		
		if(null != groupPicId && !StringUtils.isBlank(groupPicId)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "申购成功,请耐心等待审批结果");
			return resultMap;
		}else{
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "申购失败，请联系工作人员");
			return resultMap;
		}
	}*/
	
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
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		if(user == null) {
			map.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			map.put(CommonConst.RESPONSE_ERROR_MESSAGE, MessageConst.WARN_USER_NAME_IS_NULL);
			return map;
		}*/
		
		List<MultipartFile> fileList = getMultipartFileList(request);
		String groupPicId = insertPic(fileList,user);
		
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		if(null != groupPicId && !StringUtils.isBlank(groupPicId)) {
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "上传成功");
			return resultMap;
		}else{
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "上传失败");
			return resultMap;
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
	 * 插入图片审批图片组 返回maxGroupId
	 * @param files
	 * @param sessionUserEntity
	 * @return
	 */
	public String insertPic(List<MultipartFile> files, UserEntity sessionUserEntity) {
		String key = "";
		String url = "";
		String picRealName = "";
		String picType = "";
		String maxGroupId = Integer.toString(pictureGroupService.getMaxId());
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
			//		pic.setCreateUserId(sessionUserEntity.getUserId());
			//		pic.setCreateUserName(sessionUserEntity.getUserName());
					int insert = pictureService.insert(pic);
					if (insert > CommonConst.DIGIT_ZERO) {
						LOGGER.info("图片插入成功");
					} else {
						LOGGER.info("图片插入失败");
					}
					// 插入图片数组
					PictureGroupEntity pictureGroupEntity = new PictureGroupEntity();
					pictureGroupEntity.setId(StringUtil.produceUUID());
					pictureGroupEntity.setPicGroupId(maxGroupId);
					// pictureGroupEntity.setPicGroupName(serviceItemName);
					pictureGroupEntity.setPicId(maxId);
					pictureGroupEntity.setCreateTime(new Date());
			//		pictureGroupEntity.setCreateUserId(sessionUserEntity.getUserId());
			//		pictureGroupEntity.setCreateUserName(sessionUserEntity.getUserName());
					// pictureGroupEntity.setPicGroupDesc(serviceItemDesc);
					// pictureGroupEntity.setPicType(picType);
					pictureGroupEntity.setActive(true);
					int updResult = pictureGroupService.insert(pictureGroupEntity);
					if (updResult > CommonConst.DIGIT_ZERO) {
						LOGGER.info("图片组插入成功");
					} else {
						LOGGER.info("图片组插入失败");
					}
				}
			}
		}
		return maxGroupId.toString();
	}
	
	
}
