package com.lvdao.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvdao.common.CommonConst;
import com.lvdao.common.MessageConst;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class SecurityUtils {
	  
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);
	private static StringBuffer sbKey = null;
	
	public static void readKeyStore() throws Exception {
		
		if (sbKey != null) {
			return;
		}
		
		InputStream is = SecurityUtils.class.getResourceAsStream(CommonConst.PATH_TO_KEY);
		BufferedReader br = null;
		
		try {
			sbKey = new StringBuffer();

			if (is != null) {
				br = new BufferedReader(new InputStreamReader(
						new BufferedInputStream(is), CommonConst.UTF8));
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					sbKey.append(sCurrentLine);
				}
			}
		} catch (Exception e) {
		} finally {
			if (is != null) {
				is.close();
			}
			if (br != null) {
				br.close();
			}
		}
	}

	private static SecretKey base64DecodeKey(String encodedKey) throws Exception {
		byte[] bytes = new BASE64Decoder().decodeBuffer(encodedKey);
	    ByteArrayInputStream input = new ByteArrayInputStream(bytes);
	    KeyStore ks = KeyStore.getInstance(CommonConst.KEY_STORE_TYPE);
	    ks.load(input, CommonConst.SALT);
	    input.close();
	    
	    return ((SecretKey)ks.getKey("ENCRYPTED_KEY", CommonConst.SALT));
	}

	private static SecretKey getSecretKey() throws Exception {
		readKeyStore();
		return base64DecodeKey(sbKey.toString());
	}
	
	public static String encryptText(String originalStr) {
		String encryptedStr = null;
		try {
			Cipher cipher = Cipher.getInstance(CommonConst.ENCRYPT_METHOD_DES);
			cipher.init(CommonConst.DIGIT_ONE, getSecretKey());
			byte[] stringBytes = originalStr.getBytes(CommonConst.UTF8);
			byte[] raw = cipher.doFinal(stringBytes);
			encryptedStr = new BASE64Encoder().encode(raw);
		} catch (Exception e) {
			LOGGER.error(MessageConst.FAILED_ENCRYPT_STRING + originalStr, e);
		}
		return encryptedStr;
	}
	
	public static String decryptText(String encryptedStr) {
		String result = null;
	    try {
	      Cipher cipher = Cipher.getInstance(CommonConst.ENCRYPT_METHOD_DES);
	      cipher.init(CommonConst.DIGIT_TWO, getSecretKey());
	      byte[] raw = new BASE64Decoder().decodeBuffer(encryptedStr);
	      byte[] stringBytes = cipher.doFinal(raw);
	      result = new String(stringBytes, CommonConst.UTF8);
	    }
	    catch (Exception e) {
	    	LOGGER.error(MessageConst.FAILED_DECRYPT_STRING + encryptedStr);
	    }
	    return result;
	}
	
	
	public static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
		   'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
		   'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
		   'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
		   'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
		   '4', '5', '6', '7', '8', '9', '+', '/', };
	
	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
		   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		   -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
		   60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		   10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
		   -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
		   38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
		   -1, -1 };
	
	public static byte[] decode(String str) {
		byte[] data = str.getBytes();
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;
		while (i < len) {
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1) {
				break;
			}
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1) {
				break;
			}
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
			do {
				b3 = data[i++];
				if (b3 == 61) {
					return buf.toByteArray();
				}
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);

			if (b3 == -1) {
				break;
			}

			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
			do {
				b4 = data[i++];
				if (b4 == 61) {
					return buf.toByteArray();
				}
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);

			if (b4 == -1) {
				break;
			}
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		return buf.toByteArray();
	}

	public static String DecryptString(byte[] encryptArr) {
		String resultStr = CommonConst.PUNCTUATION_DOUBLE_QUOTATION_MARKS;
		for(int i = CommonConst.DIGIT_ZERO, len = encryptArr.length; i < len; i++) {
			int index = encryptArr[i];
			resultStr = resultStr + (char)index;
		}
		return resultStr;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptText("ci88888888"));
		System.out.println(decryptText("V66lH9k3psKtF1+vRUnR5w=="));
	}
}
