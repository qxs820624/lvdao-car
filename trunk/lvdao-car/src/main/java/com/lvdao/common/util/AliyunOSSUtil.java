package com.lvdao.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * OSS Util
 * 
 * @author zhxihu2008
 * @since 2016-09-28 16:51
 */
public class AliyunOSSUtil {

	private static final String ACCESS_ID = "LTAIY4CDOWLTRMAJ";
	private static final String ACCESS_KEY = "BMrFhvB60TRHPBPP2SYrxCmmhchBXJ";
	private static final String END_POINT = "http://oss-cn-shenzhen.aliyuncs.com";
	private static final String BUCKET_NAME = "lvdao-oss";
	
	//Data Expire Time is 100 years
	private static final Date EXPIRE_TIME = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 100);
	
	//OSS Client
	private static final OSSClient OSSCLIENT = new OSSClient(END_POINT, ACCESS_ID, ACCESS_KEY);

	private AliyunOSSUtil() {
		// do something
	}

	/**
	 * Singleton Model to get OSS Client
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:18
	 * @return ossclient
	 */
	public static OSSClient getOSSClient() {
		return OSSCLIENT;
	}

	/**
	 * Create A bucket
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:17
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void ensureBucket() throws OSSException, ClientException {
		try {
			// 创建bucket, 判断bucket是否存在
			boolean exists = OSSCLIENT.doesBucketExist(BUCKET_NAME);
			if (!exists) {
				OSSCLIENT.createBucket(BUCKET_NAME);
			} else {

			}
		} catch (ServiceException e) {
			System.out.println(e.getErrorCode() + "  " + e.getErrorMessage());
			throw e;
		}
	}

	/**
	 * Get All Object Information in this bucket
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:17
	 * @param client
	 * @param bucketName
	 */
	public static void getObjects(OSSClient client, String bucketName) {
		ObjectListing listing = client.listObjects(bucketName);
		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
		}
	}

	/**
	 * Delete Bucket and Objects in Bucket
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:16
	 * @param client
	 * @param bucketName
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void deleteBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

		ObjectListing ObjectListing = client.listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			// 如果不为空，先删除bucket下的文件
			client.deleteObject(bucketName, objectName);
		}
		client.deleteBucket(bucketName);
	}

	/**
	 * Set Bucket Read Permission
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:58
	 * @param client
	 * @param bucketName
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void setBucketPublicReadable(OSSClient client, String bucketName)
			throws OSSException, ClientException {
		// 创建bucket
		client.createBucket(bucketName);

		// 设置bucket的访问权限，public-read-write权限
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
	}

	/**
	 * Upload File
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:16
	 * @param key
	 * @param file
	 * @throws OSSException
	 * @throws ClientException
	 * @throws FileNotFoundException
	 */
	public static void uploadFile(String key, File file) throws OSSException, ClientException, FileNotFoundException {
		// 获取指定文件的输入流
		InputStream content = new FileInputStream(file);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		meta.setContentType("image/jpeg");
		OSSCLIENT.putObject(BUCKET_NAME, key, content, meta);
	}

	/**
	 * Upload Multipart File
	 * 
	 * @author zhxihu2008
	 * @since since 2016-09-28 16:26
	 * @param key;
	 * @param multipartFile
	 * @throws OSSException
	 * @throws ClientException
	 * @throws FileNotFoundException
	 */
	public static void uploadMultipartFile(String key, MultipartFile multipartFile)
			throws OSSException, ClientException, FileNotFoundException {

		try {
			// 获取指定文件的输入流
			InputStream content = multipartFile.getInputStream();
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(multipartFile.getSize());
			meta.setContentType("image/jpeg");
			OSSCLIENT.putObject(BUCKET_NAME, key, content, meta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get URL
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:15
	 * @param key
	 * @return
	 */
	public static URL getUrl(String key) {
		URL url = OSSCLIENT.generatePresignedUrl(BUCKET_NAME, key, EXPIRE_TIME);
		System.out.println(url);
		return url;
	}

	/**
	 * Download File
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:13
	 * @param client
	 * @param bucketName
	 * @param key
	 * @param fileDownloadPath(Local Path)
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void downloadFile(OSSClient client, String bucketName, String key, String fileDownloadPath)
			throws OSSException, ClientException {
		client.getObject(new GetObjectRequest(bucketName, key), new File(fileDownloadPath));
	}

	/**
	 * Create Bucket Folder
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:14
	 * @param dir
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void createFileDir(String dir) throws OSSException, ClientException {
		ensureBucket();
		try {
			OSSCLIENT.getObject(BUCKET_NAME, dir);
		} catch (ServiceException e) {
			if (e.getErrorCode().equals("NoSuchKey")) {
				// 要创建的文件夹名称,在满足Object命名规则的情况下以"/"结尾
				String objectName = dir + "/";
				ObjectMetadata objectMeta = new ObjectMetadata();

				/*
				 * 这里的size为0,注意OSS本身没有文件夹的概念,这里创建的文件夹本质上是一个size为0的Object,
				 * dataStream仍然可以有数据
				 */
				byte[] buffer = new byte[0];
				ByteArrayInputStream in = new ByteArrayInputStream(buffer);
				objectMeta.setContentLength(0);
				try {
					// 创建文件上传目录
					OSSCLIENT.putObject(BUCKET_NAME, objectName, in, objectMeta);
				} catch (ServiceException se) {
					System.out.println(se.getErrorCode() + "  " + se.getErrorMessage());
					throw e;
				} finally {
					try {
						in.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Test Main Method
	 * 
	 * @author zhxihu2008
	 * @since 2016-09-28 16:50
	 * @param args
	 */
	public static void main(String[] args) {
//		ensureBucket();
		
		//list all objects in bucket
//		getObjects(OSSCLIENT, BUCKET_NAME);
		
		//create folder
//		createFileDir("headpic");
		
		//upload file
		String uuid = "";
		File file = new File("C:/Users/Administrator/Desktop/login.jpg");
		try {
			uuid = StringUtil.produceUUID();
			uploadFile("login.jpg", file);
			System.out.println("http://lvdao-oss.oss-cn-shenzhen.aliyuncs.com/" + "login.jpg");
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		
//		//Get URL
//		URL url = getUrl(uuid + ".png");
		
		//Dowwload file
		//File downloadFile(OSSClient client, String bucketName, String key, String fileDownloadPath)
//		long beginTime = System.currentTimeMillis();
//		for(int i = 0; i < 1; i++) {
//			downloadFile(OSSCLIENT, BUCKET_NAME, "3531530388284ca7abae694983c99cb4.png", "E:/Projects/优越/11111.png");
//		}
//		long endTime = System.currentTimeMillis();
//		System.out.println(endTime - beginTime);
		
	}
}