package com.lvdao.common;

public class MessageConst {
	
	/******************** Reminder Message *****************/
	public static final String REMINDER_SEND_SUCCESS = "发送成功!";
	public static final String REMINDER_REGISTER_SUCCESS = "用户注册成功!";
	public static final String REMINDER_REGISTER_FAIL = "用户注册失败!";
	public static final String REMINDER_LOGIN_SUCCESS = "用户登录成功!";
	public static final String REMINDER_LOGIN_FAIL = "用户登录失败!";
	public static final String REMINDER_VALIDATE_SUCCESS = "校验成功!";
	public static final String REMINDER_ACTIVE_SUCCESS = "用户激活成功!";
	public static final String REMINDER_ACTIVE_FAIL = "用户激活失败!";
	public static final String REMINDER_EDIT_SUCCESS = "用户信息修改成功!";
	public static final String REMINDER_EDIT_FAIL = "用户信息修改失败!";
	public static final String REMINDER_USER_DELETE_SUCCESS = "用户删除成功!";
	public static final String REMINDER_USER_DELETE_FAIL = "用户删除失败!";
	public static final String REMINDER_CHANGE_PASSWORD_SUCCESS = "修改密码成功!";
	public static final String REMINDER_ROLE_UPDATE_SUCCESS = "角色更新成功!";
	public static final String REMINDER_ROLE_UPDATE_FAIL = "角色更新失败!";
	public static final String REMINDER_PERMISSION_UPDATE_SUCCESS = "权限更新成功!";
	public static final String REMINDER_PERMISSION_UPDATE_FAIL = "权限更新失败!";
	public static final String REMINDER_CHANGE_PASSWORD_FAIL = "修改密码失败!";
	public static final String REMINDER_ROLE_ADD_SUCCESS = "角色新增成功";
	public static final String REMINDER_ROLE_ADD_FAIL = "角色新增失败";
	public static final String REMINDER_PERMISSION_ADD_SUCCESS = "权限新增成功";
	public static final String REMINDER_PERMISSION_ADD_FAIL = "权限新增失败";
	public static final String REMINDER_ROLE_DEL_SUCCESS = "角色删除成功";
	public static final String REMINDER_ROLE_DEL_FAIL = "角色删除失败";
	public static final String REMINDER_PERMISSION_DEL_SUCCESS = "权限删除成功";
	public static final String REMINDER_PERMISSION_DEL_FAIL = "权限删除失败";
	public static final String REMINDER_RELEASE_NOTICE_SUCCESS = "公告发布成功";
	public static final String REMINDER_RELEASE_NOTICE_FAIL = "公告发布失败";
	public static final String REMINDER_SYSTEM_MESSAGE_ADD_FAIL = "新增留言失败";
	public static final String REMINDER_SYSTEM_MESSAGE_ADD_SUCCESS = "新增留言成功";
	public static final String REMINDER_REPLY_MESSAGE_FAIL = "回复失败";
	public static final String REMINDER_REPLY_MESSAGE_SUCCESS = "回复成功";
	public static final String REMINDER_WITHDRAW_SUCCESS = "提现成功";
	public static final String REMINDER_WITHDRAW_FAIL = "提现失败";
	public static final String REMINDER_USER_ACCOUNT_UPDATE_SUCCESS = "用户账户修改成功";
	public static final String REMINDER_USER_ACCOUNT_UPDATE_FAIL = "用户账户修改失败";

	public static final String REMINDER_USER_IS_UPPER = "用户群已达到上限";
	public static final String REMINDER_USER_IS_NOT_UPPER = "用户群未达到上限";

	public static final String REMINDER_USER_ROLE_VALIDATE_FAIL = "用户角色校验失败";
	public static final String REMINDER_USER_ROLE_UPDATE_SUCCESS = "用户角色更新成功";

	/*******************************************************/
	
	/******************** Warn Message ********************/
	public static final String WARN_USER_IS_NOT_EXIST = "该会员账号还未被注册！";
	public static final String WARN_USER_CAN_REGISTER = "会员账号可以注册！";
	public static final String WARN_USER_IS_EXIST = "会员账号已存在！";
	public static final String WARN_USER_NAME_IS_NULL = "会员账号不能为空!";
	public static final String WARN_ROLE_NAME_IS_NULL = "角色不能为空!";
	public static final String WARN_USER_NAME_IS_INVALID = "会员帐号必须为字母和数字的组合!";
	public static final String WARN_USER_NAME_LENGTH_IS_INVALID = "会员账号长度只能在6位和16位之间!";
	
	public static final String WARN_USER_DEGREE_IS_NULL = "会员等级不能为空!";
	
	public static final String WARN_USER_DESC_IS_NULL = "会员昵称不能为空!";
	public static final String WARN_USER_DESC_IS_TOO_LONG = "会员昵称长度不能大于30位!";
	
	public static final String WARN_USER_MAIL_IS_NULL = "会员邮箱不能为空!";
	public static final String WARN_USER_MAIL_IS_NOT_EXIST = "会员邮箱不存在!";
	public static final String WARN_USER_MAIL_IS_NOT_MATCH = "所填的邮箱跟会员注册的邮箱不一致!";
	public static final String WARN_USER_MAIL_IS_TOO_LONG = "会员邮箱长度不能大于50位!";
	
	public static final String WARN_USER_MOBILE_IS_NULL = "手机号码不能为空!";
	public static final String WARN_USER_MOBILE_IS_ALWAYS = "此帐号已经注册!";
	public static final String WARN_USER_MOBILE_IS_TOO_LONG = "手机号码长度不能大于16位!";
	
	public static final String WARN_USER_PARENT_NAME_IS_NULL = "推荐人账号不能为空!";
	public static final String WARN_USER_PARENT_NAME_IS_INVALID = "推荐人帐号必须为字母和数字的组合!";
	public static final String WARN_USER_PARENT_NAME_LENGTH_IS_INVALID = "推荐人帐号长度只能在6位和16位之间!";
	
	public static final String WARN_MEMBER_CENTER_ID_IS_NULL = "报单中心不能为空!";
	public static final String WARN_MEMBER_CENTER_ID_IS_INVALID = "报单中心必须为字母和数字的组合!";
	public static final String WARN_MEMBER_CENTER_ID_LENGTH_IS_INVALID = "报单中心长度只能在6位和16位之间!";
	
	public static final String WARN_USER_PASSWORD_IS_NULL = "登录密码不能为空!";
	public static final String WARN_USER_VALIDATE_CODE_IS_NULL = "验证码不能为空!";
	public static final String WARN_USER_VALIDATE_CODE_IS_F = "验证码不正确!";
	public static final String WARN_USER_PASSWORD_IS_NOT_MATCH = "登录密码不正确!";
	public static final String WARN_USER_PASSWORD_IS_INVALID = "登录密码必须为字母和数字的组合!";
	public static final String WARN_USER_PASSWORD_LENGTH_IS_INVALID = "登录密码长度只能在6位和16位之间!";
	
	public static final String WARN_USER_SECOND_PASSWORD_IS_NULL = "交易密码不能为空!";
	public static final String WARN_USER_SECOND_PASSWORD_IS_NOT_MATCH = "交易密码不正确!";
	public static final String WARN_USER_SECOND_PASSWORD_IS_INVALID = "交易密码必须为字母和数字的组合!";
	public static final String WARN_USER_SECOND_PASSWORD_LENGTH_IS_INVALID = "交易密码长度只能在6位和16位之间!";
	
	public static final String WARN_USER_THIRD_PASSWORD_IS_NULL = "三级密码不能为空!";
	public static final String WARN_USER_PARENT_IS_NULL = "所填推荐人不存在!";
	public static final String WARN_USER_THIRD_PASSWORD_IS_NOT_MATCH = "三级密码不正确!";
	public static final String WARN_USER_THIRD_PASSWORD_IS_INVALID = "三级密码必须为字母和数字的组合!";
	public static final String WARN_USER_THIRD_PASSWORD_LENGTH_IS_INVALID = "三级密码长度只能在6位和16位之间!";
	
	public static final String WARN_PASSWORD_TYPE_IS_NULL = "请选择密码类型！";
	public static final String WARN_NEW_PASSWORD_IS_NULL = "新密码不能为空！";
	
	public static final String WARN_PERMISSION_NAME_IS_NULL = "权限不能为空!";
	
	public static final String WARN_MESSAGE_ID_IS_NULL = "留言Id不能为空!";
	public static final String WARN_MESSAGE_TITLE_IS_NULL = "留言标题不能为空!";
	public static final String WARN_MESSAGE_CONTENT_IS_NULL = "留言内容不能为空!";
	public static final String WARN_MESSAGE_REPLY_IS_NULL = "回复内容不能为空!";
	public static final String WARN_ID_IS_NULL = "id不能为空!";
	public static final String WARN_FARE_NOT_LOGIN = "乘客非法登录!";
	public static final String WARN_ROLE_IS_NULL = "用户角色不能存在!";
	
	/*******************************************************/
	
	/******************** Error Message ********************/
	public static final String ERROR_USER_PASSWORD = "登录密码不正确！";
	public static final String ERROR_USER_SECOND_PASSWORD = "交易密码不正确！";
	public static final String ERROR_USER_THIRD_PASSWORD = "三级密码不正确！";
	public static final String ERROR_USER_PRIMARY_PASSWORD = "原始密码不正确！";
	/*******************************************************/
	
	/******************** Failed Message ********************/
	public static final String FAILED_ENCRYPT_STRING = "Failed to encrypt string.";
	public static final String FAILED_DECRYPT_STRING = "Failed to decrypt string.";
	/*******************************************************/

	/******************** Mail ********************/
	public static final String MAIL_SUBJECT_FIND_PASSORD = "找回密码邮件（勿回）";
	/*******************************************************/
	
	
	/*************************   主页提示信息          *********************************/ 
	public static final String ATTENTIONSUCCESS = "关注成功";
	public static final String ATTENTIONFAIL = "关注失败";
	
	/******************** Error MessageCategory ********************/
	public static final String ERROR_MESSAGE_CATEGORY_PARAM_NULL = "交易密码不正确！";
	public static final String ERROR_MESSAGE_CATEGORY_QUERY_RESULT_NULL = "查询结果为空！";
	/*******************************************************/
	public static final Object WARN_USER_ACCOUNT_NULL = "";
}
