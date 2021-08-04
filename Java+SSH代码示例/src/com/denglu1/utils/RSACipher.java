package com.denglu1.utils;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSACipher {

	public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

	/**
	 * RSA解密方法
	 *
	 * @param str        待解密密文
	 * @param privateKey 私钥
	 * @return 解密后的明文
	 */
	public static String decrypt(String str, String privateKey) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] inputByte = decoder.decode(str.getBytes("UTF-8"));
		byte[] decoded = decoder.decode(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
	
}
