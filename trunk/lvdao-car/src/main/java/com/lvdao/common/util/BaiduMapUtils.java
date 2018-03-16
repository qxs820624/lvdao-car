package com.lvdao.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

/**
 * Baidu Map Util
 * 
 * @author hexiang
 * @since 2017-05-02 14:51
 */
public class BaiduMapUtils {
	private static final String BAIDU_MAP_LVDAO_AK = "Df2ktefjw7cWyU3BTt0mEKzaQOx96Wiy"; 
	
	private static final String BAIDU_MAP_YINGYAN_LVDAO_SERVICE_ID = "139424";
	
	private static final String BAIDU_MAP_YINGYAN_URL = "http://yingyan.baidu.com/api/v3";
	
	public static boolean registerBaiduMapYingYanUser(String userName) throws URISyntaxException { 
		try {
			HttpPost httpPost = new HttpPost(BAIDU_MAP_YINGYAN_URL + "/entity/add");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("ak", BAIDU_MAP_LVDAO_AK));
			nvps.add(new BasicNameValuePair("service_id", BAIDU_MAP_YINGYAN_LVDAO_SERVICE_ID));
			nvps.add(new BasicNameValuePair("entity_name", userName));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "GB2312"));
			@SuppressWarnings("resource")
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return true;
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

		return true;
			
    }
	
	
	// 对Map内所有value作utf8编码，拼接返回结果
	public String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
		StringBuffer queryString = new StringBuffer();
		for (Entry<?, ?> pair : data.entrySet()) {
			queryString.append(pair.getKey() + "=");
			queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
		}
		if (queryString.length() > 0) {
			queryString.deleteCharAt(queryString.length() - 1);
		}
		return queryString.toString();
	}

	// 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			boolean b = registerBaiduMapYingYanUser("18717874378");
			System.out.println("=================="+b);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}