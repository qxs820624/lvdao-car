package com.lvdao.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.lvdao.common.CommonConst;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class DataUtils {

	private static ThreadPoolExecutor threadPool = null;
	private static final double EARTH_RADIUS = 6378137;
    private Image img;  
    private int width;  
    private int height;
	
    /**
     * Get Pool Instance
     * 
     * @author zhxihu2008
     * @since 2014-09-28 15:41
     * @return ThreadPoolExecutor
     */
	public static ThreadPoolExecutor getPoolInstance() {
		if (threadPool == null) {
			threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * CommonConst.DIGIT_TWO,
					Runtime.getRuntime().availableProcessors() * CommonConst.DIGIT_TWO, 60L, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>());
		}
		return threadPool;
	}
	
	public static void produceStringResponse(HttpServletResponse response, Object data) {
		JSONObject object = new JSONObject();

		try {
			if (data instanceof String) {
				object.put("data", data);
			} else {
				object.put("data",
						com.alibaba.fastjson.JSONObject.toJSONStringWithDateFormat(data, CommonConst.TIME_FORMAT));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		response.setContentType("text/Xml;charset=utf8");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.println(object.toString());

		} catch (IOException ex1) {
			ex1.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	public static void produceMessageResponse(HttpServletResponse response, Object message, HashMap<String, Object> map) {
		JSONObject object = new JSONObject();
		
		try {
			object.put("message", message);
			object.put("map", map);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/Xml;charset=utf8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(object.toString());
		} catch (IOException ex1) {
			ex1.printStackTrace();
		} finally {
			out.close();
		}
	}
    
    public void compressAndResizeImg(File oldFile, File newFile, int newWidth, int newHeight) throws IOException {
    	img = ImageIO.read(oldFile);
    	width = img.getWidth(null);
    	height = img.getHeight(null);
        if (width / height > newWidth / newHeight) {  
            resizeByWidth(oldFile, newFile, newWidth);
        } else {  
            resizeByHeight(oldFile, newFile, newHeight);
        }  
    } 
    
    public void compressImgOnly(File oldFile, File newFile) throws IOException {
    	img = ImageIO.read(oldFile);
    	width = img.getWidth(null);
    	height = img.getHeight(null);
    	resizeOrCompressImg(oldFile, newFile, 0, 0);
    }
    
    public void resizeByWidth(File oldFile, File newFile, int newWidth) throws IOException {  
        int newHeight = (int) (height * newWidth / width);  
        resizeOrCompressImg(oldFile, newFile, newWidth, newHeight);  
    }  
    
    public void resizeByHeight(File oldFile, File newFile, int newHeight) throws IOException {  
        int newWidth = (int) (width * newHeight / height);  
        resizeOrCompressImg(oldFile, newFile, newWidth, newHeight);  
    }
    
	public void resizeOrCompressImg(File oldFile, File newFile, int newWidth, int newHeight) {
		FileOutputStream out = null;
		try {
			BufferedImage image = null;
			if (newWidth == 0 || newHeight == 0) {
				image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				image.getGraphics().drawImage(img, 0, 0, width, height, null);
			} else {
				image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				image.getGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);
			}
			out = new FileOutputStream(newFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
    /**
     * read properties
     */
    public static String getProperValue(String properPath, String key) {
    	try {
			InputStream in = DataUtils.class.getResourceAsStream(properPath);
			Properties prop = new Properties();
			prop.load(in);
			String value = prop.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**  
     * 根据地址获得数据的字节流  
     * @param strUrl 网络连接地址  
     * @return  
     */    
    public static InputStream getImageFromNetByUrl(String strUrl){    
        try {    
            URL url = new URL(strUrl);    
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
            conn.setRequestMethod("GET");    
            conn.setConnectTimeout(5 * 1000);    
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据    
            return inStream;
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return null;
    } 
    
	/**
	 * 返回map对象的信息(flag,message)
	 * @param flag
	 * @param message
	 * @return
	 */
	public static Map<String, String> returnMessage(String flag,String message){
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", flag);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 返回map对象的信息(flag,message)
	 * @param flag
	 * @param message
	 * @return
	 */
	public static Map<String, Object> returnMessage(String flag,String message,Object o){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("message", message);
		map.put("obj", o);
		return map;
	}
	
	/** 
	 * 计算地球上任意两点(经纬度)距离 
	 *  
	 * @param long1 
	 *            第一点经度 
	 * @param lat1 
	 *            第一点纬度 
	 * @param long2 
	 *            第二点经度 
	 * @param lat2 
	 *            第二点纬度 
	 * @return 返回距离 单位：米 
	 */  
	public static double getDistance(double long1, double lat1, double long2,  double lat2) {  
	    double a, b;  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}
	
	/**  
     * 生成以中心点为中心的四方形经纬度  
     *   
     * @param lat 纬度  
     * @param lon 经度  
     * @param raidus 半径（以米为单位）  
     * @return  
     */    
    public static Map<String, Object> getAround(double lat, double lon, int raidus) {    
    	Map<String, Object> map = new HashMap<String, Object>();
        Double latitude = lat;    
        Double longitude = lon;    
    
        Double degree = (24901 * 1609) / 360.0;    
        double raidusMile = raidus;    
    
        Double dpmLat = 1 / degree;    
        Double radiusLat = dpmLat * raidusMile;    
        Double minLat = latitude - radiusLat;    
        Double maxLat = latitude + radiusLat;    
    
        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));    
        Double dpmLng = 1 / mpdLng;                 
        Double radiusLng = dpmLng * raidusMile;     
        Double minLon = longitude - radiusLng;      
        Double maxLon = longitude + radiusLng;      
        map.put("minLat", minLat);
        map.put("minLon", minLon);
        map.put("maxLat", maxLat);
        map.put("maxLon", maxLon);
        return map;
    }

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
       } 
       return ip; 
   } 
    
	
	/** 
     *  获取网络图片
     * @param imgUrl 图片地址 
     * @return  
     */  
    public static BufferedImage getBufferedImage(String imgUrl) {  
        URL url = null;  
        InputStream is = null;  
        BufferedImage img = null;  
        try {  
            url = new URL(imgUrl);  
            is = url.openStream();  
            img = ImageIO.read(is);  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return img;  
    }
    
    /**
     * 解析xls文件
     * 
     * @author zhxihu2008
     * @since 2016-11-03 11:21
     */
    public static List<List<String>> readXls(String filePath) throws Exception {
    	InputStream is = new FileInputStream(filePath);
    	HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
    	
    	List<List<String>> result = new ArrayList<List<String>>();
    	for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
    		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
    		
    		if(null == hssfSheet) {
    			continue;
    		}
    		
    		//处理当前sheet
    		for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
    			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
    			
    			int minCellNum = hssfRow.getFirstCellNum();
    			int maxCellNum = hssfRow.getLastCellNum();
    			List<String> rowList = new ArrayList<String>();
    			
    			//遍历该行获取处理每个cell元素
    			for(int cellNum = minCellNum; cellNum < maxCellNum; cellNum++) {
    				HSSFCell cell = hssfRow.getCell(cellNum);
    				
    				if(null == cell) {
    					continue;
    				}
    				cell.setCellType(Cell.CELL_TYPE_STRING);
    				rowList.add(cell.getStringCellValue());
    			}
    			result.add(rowList);
    		}
    	}
    	
    	return result;
    }
    
	public static void main(String[] args) {
//		double d = getDistance(116.407288, 39.904549, 108.952736, 34.264648);
//		System.out.println("======================"+d);
//		BufferedImage image = getBufferedImage("http://motian-oss.oss-cn-shenzhen.aliyuncs.com/alioss_4a36dfcd-903e-4569-8862-0365fa04040a.jpg");
//		System.out.println("========height====="+image.getHeight()+"================width==========" + image.getWidth());
		String filePath = "E:/Projects/大数据/1-企业数据最近更新20161103/北京经理.xls";
		try {
			DataUtils.readXls(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
