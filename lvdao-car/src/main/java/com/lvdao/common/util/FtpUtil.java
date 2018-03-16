package com.lvdao.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp工具类
 * 
 * @author hx
 * @since 2016/9/22 18:01
 *
 */
public class FtpUtil {
	
	private static final String FTP_HOST = "120.76.74.187";
	
	private static final int FTP_PORT = 21;
	
	private static final String FTP_USER = "ssbftp";
	
	private static final String FTP_PASSWORD = "ZXH520zxh!@#";
	
	private static final String FTP_BASEPATH = "/usr/FTP/";
	
	public static final String FTP_BASEURL = "http://120.76.74.187:8088/";
	
	/**  
     * Description: 向FTP服务器上传文件  
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath 
     * @param filename 上传到FTP服务器上的文件名  
     * @param input 输入流  
     * @return 成功返回true，否则返回false  
     */    
    public static boolean uploadFile(String filePath, String filename, InputStream input) {  
        boolean result = false;  
        FTPClient ftp = new FTPClient();  
          
        try {  
            int reply;  
            ftp.connect(FTP_HOST, FTP_PORT);// 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
            ftp.login(FTP_USER, FTP_PASSWORD);// 登录  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                return result;  
            }  
            //切换到上传目录  
            if (!ftp.changeWorkingDirectory(FTP_BASEPATH+filePath)) {  
                //如果目录不存在创建目录  
                String[] dirs = filePath.split("/");  
                String tempPath = FTP_BASEPATH;  
                for (String dir : dirs) {  
                    if (null == dir || "".equals(dir)) continue;  
                    tempPath += "/" + dir;  
                    if (!ftp.changeWorkingDirectory(tempPath)) {  
                        if (!ftp.makeDirectory(tempPath)) {  
                            return result;  
                        } else {  
                            ftp.changeWorkingDirectory(tempPath);  
                        }  
                    }  
                }  
            }  
            //设置上传文件的类型为二进制类型  
            ftp.setFileType(FTP.BINARY_FILE_TYPE);  
            //上传文件  
            if (!ftp.storeFile(filename, input)) {  
                return result;  
            }  
            input.close();  
            ftp.logout();  
            result = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return result;  
    }  
    
	/**  
     * Description: 从FTP服务器下载文件  
     * @param host FTP服务器hostname  
     * @param port FTP服务器端口  
     * @param username FTP登录账号  
     * @param password FTP登录密码  
     * @param remotePath FTP服务器上的相对路径  
     * @param fileName 要下载的文件名  
     * @param localPath 下载后保存到本地的路径  
     * @return  
     */    
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,  
            String fileName, String localPath) {  
        boolean result = false;  
        FTPClient ftp = new FTPClient();  
        try {  
            int reply;  
            ftp.connect(host, port);  
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器  
            ftp.login(username, password);// 登录  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
                return result;  
            }  
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录  
            FTPFile[] fs = ftp.listFiles();  
            for (FTPFile ff : fs) {  
                if (ff.getName().equals(fileName)) {  
                    File localFile = new File(localPath + "/" + ff.getName());  
  
                    OutputStream is = new FileOutputStream(localFile);  
                    ftp.retrieveFile(ff.getName(), is);  
                    is.close();  
                }  
            }  
  
            ftp.logout();  
            result = true;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
        return result;  
    } 
    
    public static void main(String[] args) {
    	File file = new File("D:/dev.jpg");
    	InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	String filePath = DateUtils.format(new Date(), DateUtils.DATE_FORMAT_YEAR_MONTH_DAY);
    	String filename = StringUtil.produceUUID() + ".jpg";
    	boolean b = uploadFile(filePath, filename, input);
    	System.out.println("=========================="+b);
	}
}
