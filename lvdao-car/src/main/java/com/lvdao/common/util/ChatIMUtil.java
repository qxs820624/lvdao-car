package com.lvdao.common.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 环信工具类
 * 
 * @author hx
 * @since 2016/9/22 18:01
 */
@SuppressWarnings("deprecation")
public class ChatIMUtil {
	
	private static final String EASEMOD_HOST = "https://a1.easemob.com";
	private static final String EASEMOD_ORG = "/sykjsong";
	private static final String EASEMOD_APP = "/shishibang";
	private static final String APP_CLIENT_ID = "YXA6etAEoIYfEeamHm_8yzDwhQ";
	private static final String APP_CLIENT_SECRET = "YXA65j2mry70JlRL0UAmWQ6zHYTs87U";
	//lvdao的
	private static final String EASEMOD_APP_LVDAO = "/lvdao";
	private static final String APP_CLIENT_ID_LVDAO = "YXA6F7v5wCi7Eee7JluTmKa4gg";
	private static final String APP_CLIENT_SECRET_LVDAO = "YXA6wnu9r-CD5uZeVBzsm5c3eaVzJls";
	
	/**  
     * Description: 获取授权
     * @return 成功返回token 
	 * @throws URISyntaxException 
     */    
    public static String getAuthToken() throws URISyntaxException {  
    	
    	Client client = Client.create();  
	    URI u = new URI(EASEMOD_HOST + EASEMOD_ORG + EASEMOD_APP_LVDAO + "/token");  
	    WebResource resource = client.resource(u);  
	    //get  
	    MultivaluedMapImpl params = new MultivaluedMapImpl();  
	    
	    params.add("grant_type", "client_credentials"); 
	    params.add("client_id", APP_CLIENT_ID_LVDAO);
	    params.add("client_secret", APP_CLIENT_SECRET_LVDAO);
	    
	    String result = resource.queryParams(params).get(String.class);  
	    @SuppressWarnings("rawtypes")
		Map map = (Map)com.alibaba.fastjson.JSONObject.parse(result);
	    String token = (String) map.get("access_token");
	    System.out.println("=====token======"+token);
	    return token;
    }  
	
	/**  
     * Description: 单个用户环信注册
     * @param userName 注册用户名 
     * @param password 注册用户密码
     * @return 成功返回true，否则返回false  
	 * @throws URISyntaxException 
     */    
	public static boolean registerIM(String userName, String password) throws URISyntaxException { 
    	
    	String jsonStr;
    	
		try {
			@SuppressWarnings("resource")
			DefaultHttpClient httpClient = new DefaultHttpClient();  
			  
			HttpPost method = new HttpPost(EASEMOD_HOST + EASEMOD_ORG + EASEMOD_APP + "/users"); 
			
			JSONObject jsonParam = new JSONObject();  
			jsonParam.put("username", userName);  
			jsonParam.put("password", password);
      
			StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");    
			method.setEntity(entity); 
			
			HttpResponse httpResponse = httpClient.execute(method);  
			HttpEntity httpEntity = httpResponse.getEntity();
			jsonStr = EntityUtils.toString(httpEntity,"utf-8");
			
			JSONObject jsonObject =  new JSONObject(jsonStr);
			Object infoStr = jsonObject.get("entities");  
			
			if(infoStr == null) {
				return false;
			} else {
				return true;
			}
			
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return false;
    }
	
	/**  
     * Description: 单个用户环信注册
     * @param userName 注册用户名 
     * @param password 注册用户密码
     * @return 成功返回true，否则返回false  
	 * @throws URISyntaxException 
     */    
	public static boolean registerIM(String userName, String password , String type) throws URISyntaxException { 
    	
    	String jsonStr;
    	
		try {
			@SuppressWarnings("resource")
			DefaultHttpClient httpClient = new DefaultHttpClient();  
			HttpPost method = null ; 
			if("lvdao".equals(type)){
				//驴道科技 环信
				method = new HttpPost(EASEMOD_HOST + EASEMOD_ORG + EASEMOD_APP_LVDAO + "/users");
			}else{
				 method = new HttpPost(EASEMOD_HOST + EASEMOD_ORG + EASEMOD_APP + "/users");
			}
			
			JSONObject jsonParam = new JSONObject();  
			jsonParam.put("username", userName);  
			jsonParam.put("password", password);
      
			StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");    
			method.setEntity(entity); 
			
			HttpResponse httpResponse = httpClient.execute(method);  
			HttpEntity httpEntity = httpResponse.getEntity();
			jsonStr = EntityUtils.toString(httpEntity,"utf-8");
			
//			JSONObject jsonObject =  new JSONObject(jsonStr);
			return true;
//			Object infoStr = jsonObject.get("entities");  
			
//			if(infoStr == null) {
//				return false;
//			} else {
//				return true;
//			}
			
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return false;
    }
    
	/**
	 * 发送环信信息
	 * 
	 * @param userName 发送用户
	 * @param content 内容
	 * @param ext 拓展
	 * @return
	 * @throws URISyntaxException
	 */
	public static boolean sendMessage(String userName, String message, JSONObject extJson) throws URISyntaxException { 
    	
    	String jsonStr;
		try {
			@SuppressWarnings("resource")
			DefaultHttpClient httpClient = new DefaultHttpClient();  
			  
			HttpPost method = new HttpPost(EASEMOD_HOST + EASEMOD_ORG + EASEMOD_APP_LVDAO + "/messages"); 
			
			JSONObject jsonParam = new JSONObject();  
			jsonParam.put("target_type", "users");  
			List<String> targets = new ArrayList<String>();
			targets.add(userName);
			jsonParam.put("target", targets);
			Map<String, String> param = new HashMap<String, String>();
			param.put("type", "txt");
			param.put("msg", message);
			jsonParam.put("msg", param);
			extJson.put("attr", "lvdao");
			Map<String, Object> map = new HashMap<String, Object>();
			jsonParam.put("ext", extJson);
      
			StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");   
			method.setEntity(entity); 
			
			method.addHeader("Authorization", "Bearer "+getAuthToken());
			
			HttpResponse httpResponse = httpClient.execute(method);  
			HttpEntity httpEntity = httpResponse.getEntity();
			jsonStr = EntityUtils.toString(httpEntity,"utf-8");
			
//			JSONObject jsonObject =  new JSONObject(jsonStr);
//			Object infoStr = jsonObject.get("entities");  
//			
//			if(infoStr == null) {
//				return false;
//			} else {
//				return true;
//			}
			return true;
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return false;
    }
    
    public static void main(String[] args) {
    	try {
//    		String[] str = new String[]{"admin","13697053308","18717874378","18717870001","18717874379","18717870002","13697053310",
//    				"18717870000","000002","13697053307","13697053305","18681582032","13697053308","13697053300"};
//    		boolean b = false;
//    		for (String string : str) {
//    			b = registerIM(string, "111111", "lvdao");
//    			System.out.println("====="+string+"======"+b);
//				
//			}
////    		String authToken = getAuthToken();
////    		System.out.println(authToken);

    		JSONObject extJson = new JSONObject();
    		extJson.put("userId", "1");
			extJson.put("userName", "18717874379");
			extJson.put("vehicleNo", "粤B 11111");
			extJson.put("vehicleBrand", "雪弗莱");
			extJson.put("vehicleModel", "新科鲁兹");
			extJson.put("vehicleColor", "白色");
			extJson.put("longitude", "113.951149");
			extJson.put("latitude", "22.554003");
			extJson.put("distance", 3);
			extJson.put("userRealName", "何师傅");
	    	boolean b = sendMessage("13612877661", "hello, what's your name", extJson);
	    	System.out.println("================="+b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
