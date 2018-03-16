package com.lvdao.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class YouDaoUtil {

	private static String url = "http://fanyi.youdao.com/openapi.do";

	private static String keyfrom = "zssj-mall";
	
	private static String key = "843086223";

	private static String doctype = "xml";

	public static String sendGet(String str) {

	  // 编码成UTF-8
	  try {
		  str = URLEncoder.encode(str, "utf-8");
	  } catch(UnsupportedEncodingException e) {
		  e.printStackTrace();
	  }

	  StringBuffer buf = new StringBuffer();
	  buf.append("keyfrom=");
	  buf.append(keyfrom);
	  buf.append("&key=");
	  buf.append(key);
	  buf.append("&type=data&doctype=");
	  buf.append(doctype);
	  buf.append("&version=1.1&q=");
	  buf.append(str);

	  String param =buf.toString();

	  try {
		  String urlName = url + "?" + param;
		  URL realUrl =new URL(urlName);
		  
		  //打开和URL之间的连接
		  URLConnection connection = realUrl.openConnection();
		  connection.addRequestProperty("encoding", "UTF-8");
          connection.setDoInput(true);
          connection.setDoOutput(true);
          connection.connect();

          OutputStream os = connection.getOutputStream();
          OutputStreamWriter osw = new OutputStreamWriter(os);
          BufferedWriter bw = new BufferedWriter(osw);


          bw.write(urlName);
          bw.flush();

          InputStream is = connection.getInputStream();
          InputStreamReader isr = new InputStreamReader(is,"UTF-8");
          BufferedReader br = new BufferedReader(isr);

          String line;
          StringBuilder builder = new StringBuilder();
          while((line = br.readLine()) != null){
              builder.append(line);
          }

          bw.close();
          osw.close();
          os.close();

          br.close();
          isr.close();
          is.close();
          return builder.toString();


      } catch (MalformedURLException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
	  return "";
  }
	public static String getYouDaoValue(String str) {
		String result = null;

		// 发送GET请求翻译
		result = sendGet(str);

		// 处理XML中的值
		int re1 = result.indexOf("<errorCode>");
		int re2 = result.indexOf("</errorCode>");
		String in = result.substring(re1 + 11, re2);
		System.out.println("===========翻译是否成功============" + in);

		if (in.equals("0")) {
			System.out.println("翻译正常");

			re1 = result.indexOf("<paragraph><![CDATA[");
			re2 = result.indexOf("]]></paragraph>");
			in = result.substring(re1 + 20, re2);
			System.out.println("==========有道翻译================" + in);

//			re1 = result.indexOf("<ex><![CDATA[");
//			re2 = result.indexOf("]]></ex>");
//			in = result.substring(re1 + 13, re2);
//			System.out.println("==========有道词典-网络释义================" + in);

		} else if (in.equals("20")) {
			System.out.println("要翻译的文本过长");
			return "要翻译的文本过长";
		} else if (in.equals("30")) {
			System.out.println("无法进行有效的翻译");
			return "无法进行有效的翻译";
		} else if (in.equals("40")) {
			System.out.println("不支持的语言类型");
			return "不支持的语言类型";
		} else if (in.equals("50")) {
			System.out.println("无效的key");
			return "无效的key";
		}

		return result;
	}
	
//	public static void main(String[] args) {
//		String value = getYouDaoValue("中文");
//	}
	
}
