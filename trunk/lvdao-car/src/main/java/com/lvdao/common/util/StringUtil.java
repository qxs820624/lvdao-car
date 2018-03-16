package com.lvdao.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.lvdao.common.CommonConst;


@SuppressWarnings("deprecation")
public class StringUtil {

	public static boolean containsNumber(String str) {
		
		char[] ch = str.toCharArray();
		
		if(null != ch && ch.length > CommonConst.DIGIT_ZERO) {
			for(int i = CommonConst.DIGIT_ZERO, len = ch.length; i < len; i++) {
				boolean isNumber = Character.isDigit(ch[i]);
				if(!isNumber){
					return false;
				}
			}
		}
		return true;
	}
	
	public static String produceUUID() {
		return String.valueOf(java.util.UUID.randomUUID()).replaceAll(CommonConst.PUNCTUATION_HORIZONTAL_LINE, CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS);
	}
	
	/**
     * 产生随机字符串
     * 
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
     } 
    
    /**
     * 判断传入的多个参数是否都不为空
     * @author sududa
     * @date 2016年10月14日
     * @param objects 传入的多个参数
     * @return 都不为空返回true，否则返回false
     */
    public static boolean isnotObjectsNotEmpty(Object ...objects){
        if(objects == null){
            return false;
        }
        for(Object object:objects){
            if(null == object || "".equals(object) || "null".equals(object)){
                return false;
            }
        }
        return true;
    }
	
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyMMddHHmmss为格式的当前系统时间+2位随机数
     */
    public static String getOrderSn() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
        String sn = df.format(date);
        sn += randomNumber(2);
        return sn;
    }
    
	public static int send(String mobile, String message) {

		try {
			HttpPost httpPost = new HttpPost(CommonConst.SEND_URL);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("uid", CommonConst.SEND_UID));
			nvps.add(new BasicNameValuePair("pwd", CommonConst.SEND_PWD));
			nvps.add(new BasicNameValuePair("mobile", mobile));
			nvps.add(new BasicNameValuePair("msg", message));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "GB2312"));
			@SuppressWarnings("resource")
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return 1;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 1;

	}
	
	public static int sendSmsCode(String mobile, String message){
//        String testPhone = "13000000000";
//        String testContent = "【探米电商】您的验证码是1234,５分钟内有效。若非本人操作请忽略此消息。"; // 注意测试时，也请带上公司简称或网站签名，发送正规内容短信。千万不要发送无意义的内容：例如 测一下、您好。否则可能会收不到
		if(org.apache.commons.lang.StringUtils.isEmpty(mobile)||org.apache.commons.lang.StringUtils.isEmpty(message)){
			return CommonConst.DIGIT_ZERO;
		}
        StringBuffer httpArg = new StringBuffer();
        httpArg.append("u=").append(CommonConst.SEND_SMS_CODE_UID).append("&");
        httpArg.append("p=").append(md5(CommonConst.SEND_SMS_CODE_PWD)).append("&");
        httpArg.append("m=").append(mobile).append("&");
        httpArg.append("c=").append(encodeUrlString(message, "UTF-8"));
 
        String result = request(CommonConst.SEND_SMS_CODE_URL, httpArg.toString());
        
        if(!"0".equals(result)) {
        	return CommonConst.DIGIT_ZERO;
        }
        return CommonConst.DIGIT_ONE;
	}
	
	public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
 
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = reader.readLine();
            if (strRead != null) {
                sbf.append(strRead);
                while ((strRead = reader.readLine()) != null) {
                    sbf.append("\n");
                    sbf.append(strRead);
                }
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/**
     * 获取count个随机数
     * @param count 随机数个数
     * @return
     */
    public static String randomNumber(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }
    
    /**
     * 从1-count中随机获取整数
     * @param count
     * @return
     */
    public static Integer getRandomNo(int count) {
    	Random random = new Random();
    	int result = random.nextInt(count);
    	return result + 1;
    }
    
    public static String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
 
    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null)
            return str;
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }
    
    /**
     * 比较两个字符数组是否含有相同元素
     * @param strArr1
     * @param strArr2
     * @return true 存在  false 不存在
     */
    public static boolean compareStringArr(String[] leftArr, String[] rightArr) {
    	for (int i = 0, len = leftArr.length; i < len; i++) {
			for (int j = 0, leng = rightArr.length; j < leng; j++) {
				String leftStr = leftArr[i];
				String rightStr = rightArr[j];
				
				if(leftStr.equals(rightStr)) {
					return true;
				}
			}
		}
    	return false;
    }
    
    public static String cutPunctuation(String message) {
    	StringBuilder sb = new StringBuilder();
    	char[] chars = message.toCharArray();  
        for(int i = 0; i < chars.length; i ++) {  
            if((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90)) {  
            	sb.append(chars[i]);
            }  
        } 
        return sb.toString();
    }
    
    public static void main(String[] args) {
//		String[] arr1 = new String[]{"8", "1", "6", "7", "5", "4", "9", "3", "2"};
//		String[] arr2 = new String[]{"1"};
//		boolean b = compareStringArr(arr2, arr1);
//		System.out.println("========="+b);
//    	String punctuation = cutPunctuation("我喜欢，￥。。。..&@,Java");
//    	System.out.println(produceUUID());
    	sendSmsCode("18516525576", "【航客中国】你好");
	}
    
    /**
     * 判断为空 返回str
     * @param str
     * @return
     */
    public String isempty(String str){
    	if(org.apache.commons.lang.StringUtils.isEmpty(str)){
    		return "";
    	}
    	return str;
    }
    
    public static String valueOf(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

	/**
	 * 字符串集合转换为('A','A')格式
	 */
    public static String assembleParam(List<String> ids) {
		StringBuffer sb = null;

		if (null == ids || CommonConst.DIGIT_ZERO >= ids.size()) {
			return null;
		}

		if (CommonConst.DIGIT_ONE == ids.size()) {
			sb = new StringBuffer(CommonConst.PUNCTUATION_LEFT_BRACKET).append(ids.get(CommonConst.DIGIT_ZERO))
					.append(CommonConst.PUNCTUATION_RIGHT_BRACKET);
		} else {
			sb = new StringBuffer(CommonConst.PUNCTUATION_LEFT_BRACKET);
			for (String id : ids) {
				sb.append(id).append(CommonConst.PUNCTUATION_COMMA);
			}
			sb.deleteCharAt(sb.length() - 1); // 删除最后一个逗号
			sb.append(CommonConst.PUNCTUATION_RIGHT_BRACKET);
		}
		return sb.toString();
	}
}
