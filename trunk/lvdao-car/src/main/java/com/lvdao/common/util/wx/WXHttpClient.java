package com.lvdao.common.util.wx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.lvdao.common.CommonConst;

public class WXHttpClient {

private static CloseableHttpClient httpClient = HttpClients.createDefault();

protected static org.apache.log4j.Logger LOGGER = org.apache.log4j.LogManager.getLogger(WXHttpClient.class);
  
  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> doXMLParse(String strXml) throws Exception {
		if(null == strXml || "".equals(strXml)) {
			return null;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		SAXBuilder builder = new SAXBuilder();
		
		InputStream in = new ByteArrayInputStream(strXml.getBytes());
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
		
			List children = e.getChildren();
			
			String v = "";
			if(null == children || children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			map.put(k, v);
		}
		//关闭流
		in.close();
		
		return map;
	}
	
    /**
     * 
     * @param dataMap
     * @return
     */
    public static String mapToXml(Map<String, String> dataMap) {
        {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<xml>");
            Set<String> objSet = dataMap.keySet();
            for (Object key : objSet)
            {
                if (key == null)
                {
                    continue;
                }
                strBuilder.append("\n");
                strBuilder.append("<").append(key.toString()).append(">\n");
                String value = dataMap.get(key);
                strBuilder.append(value);
                strBuilder.append("</").append(key.toString()).append(">\n");
            }
            strBuilder.append("</xml>");
            return strBuilder.toString();
        }
     }
	  
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	
	
	
    public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
  
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }
    
    /**
	   * 获取本机Ip 
	   *  
	   *  通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。
	   *  获得符合 <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	   * @return
	*/
	@SuppressWarnings("rawtypes")
	public static String localIp() {
		String ip = null;
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
				
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
				
			}
		} catch (SocketException e) {
			LOGGER.warn("获取本机Ip失败:异常信息:" + e.getMessage());
		}
		return ip;
	}
    
    /**
     * 验证签名
     *
     * @param 
     * @param 
     * @throws Exception
     */
    public static boolean validateSign(Map<String, Object> resultMap) throws Exception {
    	SortedMap<String, String> packageParams = new TreeMap<String, String>();
		
		packageParams.put("appid", resultMap.get("appid").toString());
		packageParams.put("mch_id", resultMap.get("appid").toString());
		packageParams.put("product_id", resultMap.get("product_id").toString()); // 商户根据自己业务传递的参数 必填
		packageParams.put("nonce_str", resultMap.get("product_id").toString());
		packageParams.put("body", resultMap.get("body").toString());
		packageParams.put("out_trade_no", resultMap.get("out_trade_no").toString());
		packageParams.put("total_fee", resultMap.get("total_fee").toString());
		packageParams.put("spbill_create_ip", resultMap.get("spbill_create_ip").toString());
		packageParams.put("notify_url", resultMap.get("notify_url").toString());
		packageParams.put("trade_type", resultMap.get("trade_type").toString());
		packageParams.put("time_start", resultMap.get("time_start").toString());
		packageParams.put("time_expire", resultMap.get("time_expire").toString());
		LOGGER.info(packageParams.toString());
		
		String getSign = resultMap.get("sign").toString();
		String creatSign = MD5Util.createSign(packageParams, CommonConst.KEY);
		if(!getSign.equals(creatSign)){
			return false;
		} else {
			return true;
		}
    }
    
    public static HttpURLConnection conn(String url, String requestUrl) throws IOException {
    	LOGGER.info("url:"+url+"requestUrl:"+requestUrl);
    	URL orderUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) orderUrl.openConnection();
		conn.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒)
		conn.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒)
		conn.setDoOutput(true); // post请求参数要放在http正文内，顾设置成true，默认是false
		conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
		conn.setUseCaches(false); // Post 请求不能使用缓存
		// 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestMethod("POST");// 设定请求的方法为"POST"，默认是GET
		conn.setRequestProperty("Content-Length",requestUrl.length()+"");
		String encode = "utf-8";
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), encode);
		out.write(requestUrl.toString());
		out.flush();
		out.close();
		return conn;
    }
    
    
    public static HttpResponse doPost(String url, String object2Xml) {
    	HttpPost httpPost = new HttpPost(url);
    	//解决XStream对出现双下划线的bug
    	//将要提交给API的数据对象转换成XML格式数据Post给API
    	//得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
    	StringEntity postEntity = new StringEntity(object2Xml, "UTF-8");
    	httpPost.addHeader("Content-Type", "text/xml");
    	httpPost.setEntity(postEntity);
    	//设置请求器的配置
    	LOGGER.info("executing request" + httpPost.getRequestLine());
    	try {
    		HttpResponse response = httpClient.execute(httpPost);
    		//HttpEntity entity = response.getEntity();
    		return response;
    		//result = EntityUtils.toString(entity, "UTF-8");
    	} catch (ConnectionPoolTimeoutException e) {
    		LOGGER.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
    	} catch (ConnectTimeoutException e) {
    		LOGGER.error("http get throw ConnectTimeoutException", e);
    	} catch (SocketTimeoutException e) {
    		LOGGER.error("http get throw SocketTimeoutException", e);
    	} catch (Exception e) {
    		LOGGER.error("http get throw Exception", e);
    	} finally {
    		httpPost.abort();
    	}
    	return null;
    }
}