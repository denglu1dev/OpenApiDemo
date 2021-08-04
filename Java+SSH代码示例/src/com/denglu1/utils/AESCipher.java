package com.denglu1.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCipher {
	
	private static final byte[] IV = {
	        0x16, 0x61, 0x0F, 0x3A, 0x37, 0x3D, 0x1B, 0x51,
	        0x4A, 0x39, 0x5A, 0x79, 0x29, 0x08, 0x01, 0x22
	};

    /**
     * AES解密方法
     * 
     * @param key 密钥
     * @param enc 待解密内容
     * @return
     */
    public static String decrypt(byte[] key, String enc) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/CFB8/NOPADDING");
        IvParameterSpec iv = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, seckey, iv);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] result = cipher.doFinal(decoder.decode(enc));
        return new String(result);
    }

}
