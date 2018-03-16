package com.lvdao.common.util.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lvdao.common.CommonConst;

@SuppressWarnings({ "deprecation", "unused" })
public class HttpUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int CONNECT_TIME_OUT = 5000; //链接超时时间3秒

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT).build();

    private static SSLContext wx_ssl_context = null; //微信支付ssl证书

    static{
        Resource resource = new ClassPathResource("apiclient_cert.p12");
        try {
            KeyStore keystore = KeyStore.getInstance("PKCS12");
          //  char[] keyPassword = ConfigUtil.getProperty("wx.mchid").toCharArray(); //证书密码
            char[] keyPassword = CommonConst.MCHID.toCharArray(); //证书密码
            keystore.load(resource.getInputStream(), keyPassword);
            wx_ssl_context = SSLContexts.custom().loadKeyMaterial(keystore, keyPassword).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final String GET = "get";
    public static final String POST = "post";
    
    private static DefaultHttpClient client;
    
    public static String getResponseAsString(String uri, Map<String, String> parameters, String method) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
        HttpMethod hm = HttpUtils.getHttpMethod(uri, parameters, method);
        client.executeMethod(hm);
        client = null;
        String resp = hm.getResponseBodyAsString();
        hm.releaseConnection();
        return resp;
    }
    
    public static InputStream getResponseAsStream (String uri, Map<String, String> parameters, String method) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod hm = HttpUtils.getHttpMethod(uri, parameters, method);
        client.executeMethod(hm);
        client = null;
        InputStream is = hm.getResponseBodyAsStream();
        return is;
    }
    
    public static byte[] getResponseBody(String uri, Map<String, String> parameters, String method) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod hm = HttpUtils.getHttpMethod(uri, parameters, method);
        client.executeMethod(hm);
        client = null;
        byte[] b = hm.getResponseBody();
        hm.releaseConnection();
        return b;
    }
    
    private static HttpMethod getHttpMethod(String uri, Map<String, String> parameters, String method) {
        HttpMethod hm = null;
        String url = uri;
        if(GET.equals(method)) {
            if(null != parameters) {
                StringBuffer param = new StringBuffer();
                param.append("?");
                for(Map.Entry<String, String> entry : parameters.entrySet()) {
                    param.append(entry.getKey() + "=" + entry.getValue() + "&");
                }
                
                url += param.substring(0, param.length() - 1);
            }
            hm = new GetMethod(url);
        } else if(POST.equals(method)){
            PostMethod post = new PostMethod(uri);
            if(null != parameters && parameters.size() > 0) {
                NameValuePair[] nvps = new NameValuePair[parameters.size()];
                NameValuePair nvp = null;
                int i=0;
                for(Map.Entry<String, String> entry : parameters.entrySet()) {
                    nvp = new NameValuePair(entry.getKey(), entry.getValue());
                    nvps[i] = nvp;
                    i++;
                }
                post.setRequestBody(nvps);
            }
            hm = post;
        }
        return hm;
    }
    
    // Slow Implementation from sharejs.com
    private static String inputStreamToString(InputStream is) throws IOException {
        String s = "";
        String line = "";
         
        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
         
        // Read response until the end
        while ((line = rd.readLine()) != null) { s += line; }
         
        // Return full string
        return s;
    }


    /**
     * @Title: getIpAddr
     * @author kaka  www.zuidaima.com
     * @Description: 获取客户端IP地址
     * @param @return
     * @return String
     * @throws
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }
    
    public static String sendXMLDataByPost(String url, String xmlData)
            throws ClientProtocolException, IOException {
           if (client == null) {
               // Create HttpClient Object
               client = new DefaultHttpClient();
           }
           // Send data by post method in HTTP protocol,use HttpPost instead of
           // PostMethod which was occurred in former version
           HttpPost post = new HttpPost(url);
           // Construct a string entity
           HttpEntity e = (HttpEntity)new StringEntity(xmlData, "UTF-8");
           // Set XML entity
           post.setEntity(e);
           // Set content type of request header
           post.setHeader("Content-Type", "text/xml;charset=UTF-8");
         // Execute request and get the response
           HttpResponse response = client.execute(post);
           // Response Header - StatusLine - status code
           return HttpUtils.inputStreamToString(response.getEntity().getContent());
         }

    /**
     * @description 功能描述: post https请求，服务器双向证书验证
     * @param url 请求地址
     * @param params 参数
     * @return 请求失败返回null
     */
    public static String posts(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        List<org.apache.http.NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .setSSLSocketFactory(getSSLConnectionSocket())
                    .build();
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * @description 功能描述: post https请求，服务器双向证书验证
     * @param url 请求地址
     * @param s 参数xml
     * @return 请求失败返回null
     */
    public static String posts(String url, String s) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .setSSLSocketFactory(getSSLConnectionSocket())
                    .build();
            httpPost.setEntity(new StringEntity(s, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * 
     * 得到Http请求结果 , 以get方式提交
     * 
     * @param url请求地址
     * 
     * @param parms请求参数
     * 
     * @return
     * 
     */
	@SuppressWarnings("resource")
	public static String getBodyAsString(String url) {
        if (org.apache.commons.lang.StringUtils.isBlank(url)) {
            return "";
        }

        String body = null;

        // 构造HttpClient的实例

        DefaultHttpClient httpclient = new DefaultHttpClient();

        httpclient.getParams().setParameter(
                CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);
        httpclient.getParams().setParameter(
                CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");

        if (url.toUpperCase().startsWith("HTTPS")) {
            // add ssl
            httpclient = useTrustingTrustManager(httpclient);
        }

        HttpGet httpget = new HttpGet(url);
        httpget.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        httpget.getParams().setParameter( CoreProtocolPNames.USE_EXPECT_CONTINUE, Boolean.FALSE);

        try {
            HttpResponse response = httpclient.execute(httpget);

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    body = EntityUtils.toString(entity,"utf-8");
                }

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ignore) {
            }

        }

        return body;

    }
    
	private static DefaultHttpClient useTrustingTrustManager(
            DefaultHttpClient httpClient) {
        try {
            // First create a trust manager that won't care.
            X509TrustManager trustManager = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                        String authType) throws CertificateException {
                    // Don't do anything.
                }

                public void checkServerTrusted(X509Certificate[] chain,
                        String authType) throws CertificateException {
                    // Don't do anything.
                }

                public X509Certificate[] getAcceptedIssuers() {
                    // Don't do anything.
                    return null;
                }
            };

            // Now put the trust manager into an SSLContext.
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { trustManager }, null);

            // Use the above SSLContext to create your socket factory
            // (I found trying to extend the factory a bit difficult due to a
            // call to createSocket with no arguments, a method which doesn't
            // exist anywhere I can find, but hey-ho).
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            // If you want a thread safe client, use the ThreadSafeConManager,
            // but
            // otherwise just grab the one from the current client, and get hold
            // of its
            // schema registry. THIS IS THE KEY THING.
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry schemeRegistry = ccm.getSchemeRegistry();

            // Register our new socket factory with the typical SSL port and the
            // correct protocol name.
            schemeRegistry.register(new Scheme("https", sf, 443));

            // Finally, apply the ClientConnectionManager to the Http Client
            // or, as in this example, create a new one.
            return new DefaultHttpClient(ccm, httpClient.getParams());
        } catch (Throwable t) {
            // AND NEVER EVER EVER DO THIS, IT IS LAZY AND ALMOST ALWAYS WRONG!
            t.printStackTrace();
            return null;
        }
    }
    
	 /**
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestXml(SortedMap<String,Object> parameters){
	        StringBuffer sb = new StringBuffer();
	        sb.append("<xml>");
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();
	        while(it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            String k = (String)entry.getKey();
	            String v = (String)entry.getValue();
	            if("sign".equalsIgnoreCase(k)){

	            }
	            else if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)) {
	                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
	            }
	            else {
	                sb.append("<"+k+">"+v+"</"+k+">");
	            }
	        }
	        sb.append("<"+"sign"+">"+"<![CDATA["+parameters.get("sign")+"]]></"+"sign"+">");
	        sb.append("</xml>");
	        return sb.toString();
	    }

    //获取ssl connection链接
    private static SSLConnectionSocketFactory getSSLConnectionSocket() {
        return new SSLConnectionSocketFactory(wx_ssl_context, new String[] {"TLSv1", "TLSv1.1", "TLSv1.2"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }
	
	}
   

