package com.lvdao.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.lvdao.common.util.DataUtils;

public class CommonConst {
	
	public static int SUCCESS = 1;
	public static int FAIL = 0;
	
	
	/*********************微信支付***********************/
	public static final String APPID = "wxeb01455888030bcb";//商家唯一标识
	public static final String MCHID = "1415274002";//受理商ID，身份标识
	public static final String APPSECRET = "b4cd118fb2f23348e9693159e9c4fec3";//app秘钥
	public static final String KEY = "b4cd118fb2f23348e9693159e9c4fec3"; //商户支付密钥Key。审核通过后，在微信发送的邮件中查看
	public static final String WEIXINPAY_SUBJECT = "驴道订单"; //商城标题
	public static final String DEFAULT_FEE_TYPE = "CNY"; //默认货币类型
	public static final String DEFAULT_TRADE_TYPE = "APP"; //默认支付平台
	public static  final  String NOTIFY_URL="http://lvdao.motian123.cn/lvdao-app/wxPayController/doNotify.do";
	
	public static  final  String WX_NOTIFY_URL="http://lvdao.motian123.cn/lvdao-app/recharge/wxNotify.do";
	
	/******************** Digit *****************/
	public static final int DIGIT_MINUS_ONE = -1;
	public static final int DIGIT_ZERO = 0;
	public static final int DIGIT_ONE = 1;
	public static final int DIGIT_TWO = 2;
	public static final int DIGIT_THREE = 3;
	public static final int DIGIT_FOUR = 4;
	public static final int DIGIT_FIVE = 5;
	public static final int DIGIT_SIX = 6;
	public static final int DIGIT_SEVEN = 7;
	public static final int DIGIT_EIGHT = 8;
	public static final int DIGIT_NINE = 9;
	public static final int DIGIT_TEN = 10;
	public static final int DIGIT_SIXTEEN = 16;
	public static final int DIGIT_TWENTY = 20;
	public static final int DIGIT_THIRTY = 30;
	public static final int DIGIT_SIXTY = 60;
	public static final int DIGIT_HUNDRED = 100;
	public static final int DIGIT_THOUNDRED = 1000;
	public static final int MANAGER_MAX = 10000;
	public static final int VIP_MAX = 1000000;
	public static final double CHARGE_PERCENT = 0.02;
	/********************************************/
	
	/******************** String *****************/
	public static final String STRING_MINUS_ONE = "-1";
	public static final String STRING_ZERO = "0";
	public static final String STRING_ONE = "1";
	public static final String STRING_TWO = "2";
	public static final String STRING_THREE = "3";
	public static final String STRING_FOUR = "4";
	public static final String STRING_FIVE = "5";
	public static final String STRING_SIX = "6";
	public static final String STRING_SEVEN = "7";
	public static final String STRING_EIGHT = "8";
	public static final String STRING_NINE = "9";
	public static final String STRING_TEN = "10";
	public static final String STRING_SIXTEEN = "16";
	public static final String STRING_TWENTY = "20";
	public static final String STRING_THIRTY = "30";
	public static final String STRING_SIXTY = "60";
	public static final String STRING_HUNDRED = "100";
	public static final String STRING_THOUNDRED = "1000";
	public static final String STRING_TEN_THOUNDRED = "10000";
	/********************************************/
	
	/************************** Punctuation ***********************/
	public static final String PUNCTUATION_DOT = ".";
	public static final String PUNCTUATION_DOUBLE_QUOTATION_MARKS = "";
	public static final String PUNCTUATION_DOUBLE_QUOTATION_MARKS_BACKSPACE = " ";
	public static final String PUNCTUATION_SEMICOLON=";";
	public static final String PUNCTUATION_HORIZONTAL_LINE = "-";
	public static final String PUNCTUATION_UNDER_LINE = "_";
	public static final String PUNCTUATION_COMMA = ",";
	public static final String PUNCTUATION_NUMBER_SIGN = "#";
	public static final String PUNCTUATION_SPCIAL_SIGN = "/";
	public static final String PUNCTUATION_SQL_PERCENT_SIGN = "%";
	public static final String PUNCTUATION_EMAIL_SIGN = "@";
	public static final String PUNCTUATION_LEFT_BRACKET = "(";
	public static final String PUNCTUATION_RIGHT_BRACKET = ")";
	public static final String PUNCTUATION_PLUS = "+";
	public static final String PUNCTUATION_MINUS = "-";
	/***************************************************************/
	
	/************************** Boolean ***********************/
	public static final Boolean TRUE = true;
	public static final Boolean FALSE = false;
	/***************************************************************/
	
	/********************** Time Format ***********************/
	public static final String TIME_FORMAT = "yyyy-MM-dd hh:mm";
	/***********************************************************/
	
	/********************** File Path & Encrypt ***********************/
	public static final String PATH_CODE_CONFIG_FILE = "/generatorConfig.xml";
	public static final String PATH_MAIL_CONFIG_FILE = "/mail.properties";
	public static final String PATH_SYSTEM_CONFIG_FILE = "/config.properties";
	public static final String PATH_TO_KEY = "/key";
	public static final String UTF8 = "UTF8";
	public static final String ENCRYPT_METHOD_DES = "DES";
	public static final String KEY_STORE_TYPE = "JCEKS";
	public static final char[] SALT = {'j', '3', 'd', '4', '1', 'Z', '#' };
	/***********************************************************/
	
	/********************** Collection ***********************/
	public static Map<String, Object> USER_CACHE_MAP = new HashMap<String, Object>();
	public static Map<String, Object> ROLE_CACHE_MAP = new HashMap<String, Object>();
	public static Map<String, Object> PERMISSION_CACHE_MAP = new HashMap<String, Object>();
	public static Map<String, Map<String, String>> ROLE_PERMISSION_MAP = new HashMap<String, Map<String, String>>();
	public static Map<String, Map<String, String>> USER_PERMISSION_MAP = new HashMap<String, Map<String, String>>();
	public static Map<String, List<String>> USER_PERMISSION_CACHE_MAP = new HashMap<String, List<String>>();
	public static Map<String,  Map<String, List<String>>> TICKET_CACHE_MAP = new HashMap<String, Map<String, List<String>>>();
	
	public static final String MONGO_KEY_ACCOUNT = "t_account";
	public static final String MONGO_KEY_BONUS = "t_bonus";
	public static final String MONGO_KEY_BONUS_RULE = "t_rule";
	public static final String MONGO_KEY_DEGREE = "t_degree";
	public static final String MONGO_KEY_DICT = "t_dict";
	public static final String MONGO_KEY_LOG = "t_log";
	public static final String MONGO_KEY_MAIL = "t_mail";
	public static final String MONGO_KEY_MESSAGE = "t_message";
	public static final String MONGO_KEY_PERMISSION = "t_permission";
	public static final String MONGO_KEY_ROLE = "t_role";
	public static final String REDIS_MONGO_KEY_ROLE_PERMISSION = "t_role_permission";
	public static final String MONGO_KEY_STOCK = "t_stock";
	public static final String MONGO_KEY_SYSTEM_NOTICE = "t_system_notice";
	public static final String REDIS_MONGO_KEY_USER = "t_user";
	public static final String MONGO_KEY_USER_ACCOUNT = "t_user_account";
	public static final String MONGO_KEY_USER_BONUS = "t_user_bonus";
	public static final String MONGO_KEY_USER_CASH_RECORD = "t_user_cash_record";
	public static final String MONGO_KEY_USER_PROTECT = "t_user_protect";
	public static final String REDIS_MONGO_KEY_USER_ROLE = "t_user_role";
	public static final String MONGO_KEY_USER_STOCK = "t_user_stock";
	public static final String MONGO_KEY_WITHDRAW = "t_withdraw";
	public static final String MONGO_KEY_USER_PERMISSION = "t_user_permission";
	public static final String MONGO_KEY_USER_ORDER = "t_user_order";
	public static final String MONGO_KEY_DATASYNC = "t_db_info";
	public static final String MONGO_KEY_SMS = "t_sms";
	public static final String MONGO_KEY_PIC = "t_pic";
	public static final String MONGO_KEY_DEAL_LOG = "t_deal_log";
	public static final String MONGO_KEY_LOGIN_LOG = "t_login_log";
	public static final String MONGO_KEY_ANIMAL = "t_animal";
	public static final String MONGO_KEY_ANIMAL_FAV = "t_animal_fav";
	public static final String MONGO_KEY_DATE = "t_date";
	public static final String MONGO_KEY_FAVOURITE = "t_favourite";
	public static final String MONGO_KEY_PRESENT = "t_present";
	public static final String MONGO_KEY_USER_DATE = "t_user_date";
	public static final String MONGO_KEY_USER_ANIMAL = "t_user_animal";
	public static final String MONGO_KEY_USER_FAVOURITE = "t_user_favourite";
	public static final String MONGO_KEY_USER_MAIL = "t_user_mail";
	public static final String MONGO_KEY_USER_MESSAGE = "t_user_message";
	public static final String MONGO_KEY_USER_PIC = "t_user_pic";
	public static final String MONGO_KEY_USER_SMS = "t_user_sms";
	public static final String MONGO_KEY_USER_TOKEN = "t_user_token";
	public static final String MONGO_KEY_USER_RECEIVE_MESSAGE = "t_user_receive_message";
	public static final String MONGO_KEY_USER_VIEDO = "t_user_viedo";
	public static final String MONGO_KEY_USER_VOICE = "t_user_voice";
	public static final String MONGO_KEY_VIEDO = "t_viedo";
	public static final String MONGO_KEY_VOICE = "t_voice";
	public static final String MONGO_KEY_MESSAGE_PIC = "t_message_pic";
	public static final String MONGO_KEY_CATEGORY = "t_category";
	public static final String MONGO_KEY_PUSH_RECEIVE_MESSAGE = "t_push_receive_message";
	public static final String MONGO_KEY_USER_PUSH_MESSAGE = "t_user_push_message";
	public static final String MONGO_KEY_VIDEO = "t_video";
	public static final String MONGO_KEY_MESSAGE_VIDEO = "t_message_video";
	public static final String MONGO_KEY_ATTR_TYPE = "t_attr_type";
	public static final String MONGO_KEY_ATTR_VALUE = "t_attr_value";
	public static final String MONGO_KEY_CATEGORY_ATTR = "t_category_attr";
	public static final String MONGO_KEY_PIC_GROUP = "t_pic_group";
	public static final String MONGO_KEY_MAP_MESSAGE_CATEGORY = "map_message_category";
	public static final String MONGO_KEY_MAP_ATTR_VALUE = "map_attr_value";
	public static final String MONGO_KEY_MAP_ATTR_TYPE = "map_attr_type";
	public static final String MONGO_KEY_MAP_TYPE_CATEGORY_RELATION = "map_type_category_relation";
	public static final String MONGO_KEY_MAP_CATEGORY_TYPE_RELATION = "map_category_type_relation";
	public static final	String MONGO_KEY_MAP_TYPE_VALUE_RELATION = "map_type_value_relation";
	public static final String MONGO_KEY_MAP_VALUE_TYPE_RELATION = "map_value_type_relation";
	public static final String MONGO_KEY_MAP_CATEGORY_ATTR_NAME_RELATION = "map_category_attrName_relation";
	public static final String MONGO_KEY_ATTRIBUTE = "t_attribute";
	public static final String MONGO_KEY_ATTRIBUTE_GROUP = "t_attribute_group";
	public static final String MONGO_KEY_ATTRIBUTE_GROUP_RELATION = "t_attribute_group_relation";
	public static final String MONGO_KEY_ATTRIBUTE_RELATION = "t_attribute_relation";
	public static final String MONGO_KEY_ATTRIBUTE_VALUE = "t_attribute_value";
	public static final String MONGO_KEY_BRAND = "t_brand";
	public static final String MONGO_KEY_FLOOR = "t_floor";
	public static final String MONGO_KEY_KEYWORD = "t_keyword";
	public static final String MONGO_KEY_PRODUCT = "t_product";
	public static final String MONGO_KEY_PROUCT_FLOOR = "t_product_floor";
	public static final String MONGO_KEY_PRODUCT_KEYWORD = "t_product_keyword";
	public static final String MONGO_KEY_PRODUCT_TEMPLATE = "t_product_template";
	public static final String MONGO_KEY_REGION = "t_region";
	public static final String MONGO_KEY_SKU = "t_sku";
	public static final String MONGO_KEY_USER_ADDRESS = "t_user_address";
	public static final String MONGO_KEY_USER_CART = "t_user_cart";
	public static final String MONGO_KEY_USER_PRODUCT = "t_user_product";
	
	public static final String REDIS_MONGO_KEY_ALL_USER_ID_USER_NAME = "allUserIDUserName";
	public static final String REDIS_KEY_ROLE_PERMISSION_MAP = "rolePermissionMap_";
	
	public static final String USER_SELECT_LIST = "userSelectList";
	public static final String ROLE_SELECT_LIST = "roleSelectList";
	public static final String PERMISSION_SELECT_LIST = "permissionSelectList";
	
	public static final String PERMISSION = "permission";
	public static final String ALL_PERMISSION = "allPermission";
	public static final String SESSION_USER = "user";
	public static final String SESSION_ROLE = "userRole";
	public static final String RESPONSE_STATUS = "status";
	public static final boolean RESPONSE_STATUS_SUCCESS = true;
	public static final boolean RESPONSE_STATUS_FAIL = false;
	public static final String RESPONSE_ERROR_MESSAGE = "errorMessage";
	public static final String RESPONSE_MESSAGE = "message";
	public static final String RESPONSE_DATA = "data";
	public static final String RESPONSE_MONEY = "money";
	public static final String RESPONSE_SCORE = "score";
	/***********************************************************/
	public static final String LVDAO_OSSPATH = "https://lvdao-oss.oss-cn-shenzhen.aliyuncs.com/";
	public static final String MOTIAN_OSSPATH = "https://motian-oss.oss-cn-shenzhen.aliyuncs.com/";
	
	/********************** Picture Format ***********************/
	public static final String PIC_FORMAT_JPG = "jpg";
	public static final String PIC_FORMAT_PNG = "PNG";
	/***********************************************************/
	
	/************************汽车类型***********************************/
	public static final String VEHICLE_TYPE_CAR = "1";
	public static final String VEHICLE_TYPE_BUS = "2";
	public static final String VEHICLE_TYPE_TRUCK = "3";
	/***********************************************************/
	
	/******************** Mail ********************/
	public static final String MAIL_SMTP_HOST_URL = "mail.smtp.host";
	public static final String MAIL_PORT = "mail.port";
	public static final String MAIL_ADDRESS_PASSORD = "password.mail";
	public static final String MAIL_ADDRESS_BACKEND = "backend.mail";
	public static final String MAIL_ADDRESS_FUND = "fund.mail";
	public static final String MAIL_ADDRESS_FUND_SERVICE = "fundservice.mail";
	public static final String MAIL_ADDRESS_VIP_HELP = "viphelp.mail";
	public static final String MAIL_USERNAME_PASSWORD = "mail.password.username";
	public static final String MAIL_USERNAME_BACKEND = "mail.backend.username";
	public static final String MAIL_USERNAME_FUND = "mail.fund.username";
	public static final String MAIL_USERNAME_FUND_SERVICE = "mail.fundservice.username";
	public static final String MAIL_USERNAME_VIP_HELPER = "mail.viphelp.username";
	public static final String MAIL_PASSWORD = "mail.password";
	/*******************************************************/
	
	
	/******************** Dictionary ********************/
	public static final String DICTIONARY_SMSSEND_GROUP = "SMS_SEND_MODEL";
	public static final String DICT_ANDRION_DESC = "andriod_desc";
	public static final String DICT_ANDRION_VERSION_NAME = "andriod_version_name";
	public static final String DICT_ANDRION_VERSION_CODE = "andriod_version_code";
	public static final String DICT_ANDRION_DOWNLOAD_URL = "andriod_download_url";
	
	/***************************************************/
	
	/********************** APP user url ***********************/
	public static final String APP_USER_URL = "http://cd.wlsd.com.cn/api/member/?mall_db=592003&auth_code=d2WPQsWvNArX1M0l&data_version=1.0.0&data_type=1";
	/***********************************************************/
	
	/********************** App seller url ***********************/
	public static final String APP_SELLER_URL = "http://cd.wlsd.com.cn/api/store/?mall_db=592003&auth_code=d2WPQsWvNArX1M0l&data_version=&data_type=1";
	/***********************************************************/
	
	/********************** 国内微信数据查询Url ***********************/
	public static final String WECHAT_HOME_QUERY_URL = "http://wx.chwqf.com/Index/wxchwqflog?type=need";
	/********************** 国内微信数据更改Url ***********************/
	public static final String WECHAT_HOME_UPDATE_URL = "http://wx.chwqf.com/Index/wxchwqfchange?mob=";

	/********************** Static config ***********************/
	public static final String STATIC_VERSION = DataUtils.getProperValue(PATH_SYSTEM_CONFIG_FILE, "static.version");
	/***********************************************************/
	
	/********************** 短信手机 ***********************/
	public static final String SEND_URL = "http://www.smsadmin.cn/smsmarketing/wwwroot/api/post_send/";
	public static final String SEND_UID = " shenzhen8288";
	public static final String SEND_PWD = "shen1qazxsw2";
	public static final String SEND_CODE = "sendCode";
	/***********************************************************/
	
	/********************** 短信手机 ***********************/
	public static final String SEND_SMS_CODE_URL = "http://api.smsbao.com/sms";
	public static final String SEND_SMS_CODE_UID = "hangkechina";
	public static final String SEND_SMS_CODE_PWD = "HANGke123@";
	/***********************************************************/
	
	/********************** 默认密码 ***********************/
	public static final String USER_PWD = "111111";
	/***********************************************************/
	
	public static String TRANSFERS_PAY = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers"; // 企业付款
	public static String TRANSFERS_PAY_QUERY = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo"; // 企业付款查询
	
	
	/************************************微信支付开始 *********************************/
	
	//getToken
	public static String GETTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	//获取openId
	public static String GETOPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	//生成二维码数据的连接two-dimensional code data
	public static String CREATEORDERURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//授权Url 获取code
	public static String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	
	public static String ALL_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	
	public static String GETTICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	public static String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	
	/**
	 * 航客中国
	 */
	//商家唯一标识
	public static String HANGKE_APPID = "wxf2959e77344680b1";
	//受理商ID，身份标识
	public static String HANGKE_MCHID = "1452499502";
	//app秘钥
	public static String HANGKE_APPSECRET = "50963d3ba8e6e089dee08fba8104cd3a";
	//商户支付密钥Key。审核通过后，在微信发送的邮件中查看
	public static String HANGKE_KEY = "dc7a38f5af7b49f59bbb1c57b30c64f2";
	//标题
	public static String HANGKE_WEIXINPAY_SUBJECT = "航客科技订单";
	
	
	
	/************************************微信支付结束 *********************************/
	
	/************************************支付宝支付开始 *********************************/
	
	//合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
    public static String partner = "2088221937055572";

    //商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
    public static String private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCxxm5RHLKIM3YykPdSMh0+Ga4qjb1CNGUkBgvp6MasR5ljYPLNQ4HSJ7ZfujEZfEOfEwiJ6uk8WWhJ7ffdm5TPO+6ICbhXdMkDMVi1T8TAynarssWpMJ9Og2DWvcSCcpHEcg/GouKXme76+kgPPkUM79BaRUQxXKs5bor8FEMdJD8xYD2LVgJZCCkgrfYbz/1+PDSBm6UM/xgAlbmWyEWLUxmC8YJXottVPoc9wiAnD/vNxWX3qTan3ph0rblNSAD10FvwsTs2sWulyj8A2AfTXbb5FPf3pl6RCi278bwNDFzG8EEDvfzDKQGc6gSm3GzUaWAoBMUEQ5P1RwH8+1lAgMBAAECggEAN2mXi7nmDaFSt24UzVl+npl2JP7ypGblLtbTeLXGVZQA9jS9SEglto5dyHiOtlEfj+y48avvfcdPMba7oXL0Ek76oNnov4ta4KaMqHEE9dBcVq3DnceYJSmI+kR79eeyDuUI/TwJLksMF0NBl4heXeIDQvr8kwumqH36oY2FOgo7Iv26HHmDQC1KwxzTLjo+nAn6RiqTn8s4aQDVQqtXg7CV8xAqfwMeiUS35Ii3364fbXlW2gq5Ei9ts6r2Pvvru/lP7Df9ibAzfJbGup0EsC+Yh4CWqBIc4Pov02WdlDGtB1TC4K5pRySmg8XQHF1uqlRlUJhxRHoHd+hijQiNbQKBgQC6ZYeJSeKCsvqAj+HHQw0n6GFfQUYS7Kx4P49tgbLiTmstYOFU+aA5mE9wu5vi4dDYsske+/9ECOKaTalkVKKGdQrl131DwkR+bZo89aEs6htaPHbGrsBxm4tRJksEiRk8llpmpVa2JNP5/BJFemfM2zG9GFOqbZrUpNSOTccC2wKBgQCznLgnbNUxfh9nhy1u7aHFPwcWQH4vxNstBj8ZVgyEhWZN2wGyt4pY+3/AEuNjtxh7SnjbaNmNAaJ5L4IAAoausEMzIye9Kn9LQb3TJhJCkudEHZ2GeXOWfwK7w71t7n3tvslaZMFnEDOm/DU6RPWLiAMAJ8lox7F5yT3WM9YkvwKBgEsGkN5+wdCPz+qtf1hgMcxtabwYnucBIo6oLsgKG4GhxNwYEbrNW2OzXc5nhWB2OkTu+asiDWZKGUO4nMUIl/583+0Rppb+e+lTnnCJAd39QRM6ISjpsAZKcHIycqGZuXG8pxiH1nYsLrKBdq1Gjr9TPxr4zrxbr6WFzxSUU9VfAoGACrGkDUTflKjLueBEhjxDy3irNVn1qWZxHkcm7p0+9YzzwGLvwPJInG+s9YSqYT9/h6exdm7RtSGJyOlZNiLwXepEtgC71vl2xsZNRQsZPzl754RT4rPMfANn6EIJP0hZ2xPm7SjekwJV8PVHPgbpD7uzYgj8Zu6s1HF06xH/CAUCgYAObwqoKYrpN07LZzxt1BzBEQbumPJ4+2DvScfEcyHgX4HXz2b/B/t1ClrwDkVHbXLN6d67+3Gk1yOQJFvS1RlEarSRo7EgRatnclOtKBJMa6k0rsVf85gJmSHewF+rBf5vEMYF6j7UUdqx+eWCmq5wLp89dc5wLvXIVWUa5/ihmQ==";
    //支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
    public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhJoqJ93EWVPby08+i9/6C78VEjdZT/qkH5+In5SJ9/kwMJZe9YAKAvtnksRn2UoD5KmyFXu7mLvVk1nY5stqOueu+S2Iubmt/i5Uj8sJc4Vg5WrZjfOjfEt+gC8RV9usiNNT02Ii9Wdd7Sl3+nsJDHL+rjpPN4I8bQx6RgPI4vZYetl8quvfsxw6ISi9SFs2x8JSUNchiCPBjW+fbc2vauiB8/8W4KqQmaPCHpeo1xc1PvZZJq/ZePcp/uAbNTfINwQ1mYbeQpyWnVdBFkiXq1JOrdzGFlAX9WoUSrVFpElknhJWrAbxFH9ex+riykRjrrw9A+wTlFqSTNeVscnUmwIDAQAB";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 接收通知的接口名
    public static String service = "https://api.meida520.com/pay/aliNotify.do";
    //public static String service = "mobile.securitypay.pay";

    //APPID
    public static String app_id="2017111109865927";
	
    /************************************支付宝支付结束 *********************************/
	
	
	/********************** 账户价格 ***********************/
	public static final String OPEN_PRICE = "100";
	public static final String CASH_INTEGRAL_UNIT_PRICE = "1";
	public static final String AWARD_REGISTER_USER = "100";
	public static final Integer USER_OPEN_ACCOUNT = 1;
	public static final Integer USER_CLOSE_ACCOUNT = 0;
	public static final String USER_AWORD_RATIO = "0.3";
	public static final String USER_CASH_AWORD_RATIO = "0.1";
	/***********************************************************/
	
	public static final String TOKEN_PREFIX = "tk";
	
	/********************** app返回详细 ***********************/
	public static final String RESPONSE_MSG_NO_PARAM = "缺少参数";
	public static final String RESPONSE_MSG_NO_DATA = "查询数据为空";
	/***********************************************************/
	
	/********************** 账户金额 ***********************/
	public static final double PARENT_BONUS = 0.2;//推荐人分红比例
	public static final double BUSSINESS_BONUS = 0.2;	//业务佣金
	public static final double MANAGER_BONUS = 0.1;	//经理人分红比例
	public static final double VIPUSER_MANAGER_BONUS = 0.3;	//经理人和vip用户分红比例
	
	public static final double CHARITY_FUND_FEE = 0.01;	//提现慈善基金手续费比例
	public static final double PAY_FEE = 0.02;	//提现服务费比例
	public static final double TAX_FEE = 0.03;	//提现个税比例
	public static final double WITHDRAW_SUM_FEE = CHARITY_FUND_FEE + PAY_FEE + TAX_FEE;	//提现总手续费比例
	
	public static final double MONEY_INTEGRAL_PERCENT = 0.00001;	//积分兑换成金额比例(1元=100000积分)
	public static final int VIP_CHARGE = 100;	//成为vip需充值金额
	public static final int MANAGER_CHARGE = 10000;	//成为经理人需充值金额
	/***********************************************************/
	
	/********************** PassengerOrderType 1，小型汽车；3，货车；2，巴士***********************/
	public static final String PASSENGER_ORDER_BUS = "2";	//乘客订单类型:巴士
	public static final String PASSENGER_ORDER_TRUCK = "3";	//乘客订单类型:货车
	public static final String PASSENGER_ORDER_CAR = "1";	//乘客订单类型:小汽车
	/********************** PassengerOrderType***********************/
	
	
	/********************** DriverOrderType 1，小型汽车；3，货车；2，巴士***********************/
	public static final String DRIVER_ORDER_BUS = "2";	//乘客订单类型:巴士
	public static final String DRIVER_ORDER_TRUCK = "3";	//乘客订单类型:货车
	public static final String DRIVER_ORDER_CAR = "1";	//乘客订单类型:小汽车
	/********************** DriverOrderType***********************/
	
	
	/********************** orderStatus 0表示待出发,1表示已完成,2表示已取消***********************/
	public static final String ORDER_STATUS_NULL= "";	//订单状态: 没发布
	public static final String ORDER_STATUS_READY= "0";	//订单状态:0 待出发
	public static final String ORDER_STATUS_OK = "1";	//订单状态:1 完成
	public static final String ORDER_STATUS_CANCEL = "2";	//订单状态:2 取消
	/********************** DriverOrderType***********************/
	
	/********************** 乘车费用计算常量start***********************/
	public static final String STARTING_DISTANCE_CAR = "5";	//小汽车-起步里程
	public static final String PER_KILOMETER_AMOUNT_CAR = "1";	//小汽车-每公里价格
	public static final String STARTING_AMOUNT_CAR = "3";	//小汽车-起步价
	
	public static final String STARTING_AMOUNT_BUS = "3";	//bus-起步价
	
	public static final String STARTING_MID_TRUCK_AMOUNT = "100";//中货车起步金额
	public static final String STARTING_SMALL_TRUCK_AMOUNT = "70";//小货车
	public static final String STARTING_MID_MINIBUS_AMOUNT = "50";//中面包车
	public static final String STARTING_SMALL_MINIBUS_AMOUNT = "30";//小面包车
	
	public static final String VEHICLELEVEL_MID_TRUCK = "4";//中货车
	public static final String VEHICLELEVEL_SMALL_TRUCK = "3";//小货车
	public static final String VEHICLELEVEL_MID_MINIBUS = "2";//中面包车
	public static final String VEHICLELEVEL_SMALL_MINIBUS = "1";//小面包车
	
	public static final String MID_TRUCK_BASE_INFO="mid_truck_base_info";//中货车
	public static final String SMALL_TRUCK_BASE_INFO="small_truck_base_info";//小货车
	public static final String MID_MINIBUS_BASE_INFO="mid_minibus_base_info";//中面包车
	public static final String SMALL_MINIBUS_BASE_INFO="small_minibus_base_info";//小面包车
	
	public static final String ORDER_OPERATION_STATUS = "operation";
	public static final String ORDER_OPERATION_MATCH = "match";
	public static final String ORDER_OPERATION_RECEIVE = "receive";
	public static final String ORDER_OPERATION_CANCLE = "cancel";
	public static final String ORDER_OPERATION_FINISH = "finish";
	
	public static final int UNIT_PRICE =2;//小汽车每公里的费用
	
	
	/********************** 乘车费用计算常量end***********************/
	
	static {
		InputStream in = CommonConst.class.getResourceAsStream(PATH_MAIL_CONFIG_FILE);
		Properties prop = new Properties();
		try {
			prop.load(in);
			USER_CACHE_MAP.put(MAIL_SMTP_HOST_URL, prop.get(MAIL_SMTP_HOST_URL));
			USER_CACHE_MAP.put(MAIL_PORT, prop.get(MAIL_PORT));
			USER_CACHE_MAP.put(MAIL_ADDRESS_PASSORD, prop.get(MAIL_ADDRESS_PASSORD));
			USER_CACHE_MAP.put(MAIL_ADDRESS_BACKEND, prop.get(MAIL_ADDRESS_BACKEND));
			USER_CACHE_MAP.put(MAIL_ADDRESS_FUND, prop.get(MAIL_ADDRESS_FUND));
			USER_CACHE_MAP.put(MAIL_ADDRESS_FUND_SERVICE, prop.get(MAIL_ADDRESS_FUND_SERVICE));
			USER_CACHE_MAP.put(MAIL_ADDRESS_VIP_HELP, prop.get(MAIL_ADDRESS_VIP_HELP));
			USER_CACHE_MAP.put(MAIL_USERNAME_PASSWORD, prop.get(MAIL_USERNAME_PASSWORD));
			USER_CACHE_MAP.put(MAIL_USERNAME_BACKEND, prop.get(MAIL_USERNAME_BACKEND));
			USER_CACHE_MAP.put(MAIL_USERNAME_FUND, prop.get(MAIL_USERNAME_FUND));
			USER_CACHE_MAP.put(MAIL_USERNAME_FUND_SERVICE, prop.get(MAIL_USERNAME_FUND_SERVICE));
			USER_CACHE_MAP.put(MAIL_USERNAME_VIP_HELPER, prop.get(MAIL_USERNAME_VIP_HELPER));
			USER_CACHE_MAP.put(MAIL_PASSWORD, prop.get(MAIL_PASSWORD));
		} catch (IOException e) {
			System.out.println("Initial Mail Resources Failed.");
			e.printStackTrace();
		}
	}
}