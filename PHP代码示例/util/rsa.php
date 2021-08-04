<?php
/**
 * @version 1.0
 * RSA AES 解密类
 * @copyright denglu1 tech
 */
class EncryptUtil {

const PUBLICKKEY = "-----BEGIN PUBLIC KEY-----
MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQBmfmIb9VI4bd1FYsgKCTB0
o9kD9+Yq0ah3o0hf41uBjkhd1Hu4g9VP1VhKhvx9zyBxptQC194XRQj1Si+F+QGd
6ca7gVILy2Qt+yt/8a7tLh9tS+Yvu0u9qd6qzmslwXUg+mkt6gbqsuwFwQjo1uLv
7uQBbmNmaa5TUNN5MGV1TlbCKIOqFvfd6dUhWgj7h+i+sYzhd3lBOH4x6WIL8O+P
5jWYH7k7sW0LdL4oPT+o7pesL/2Ku99IqVd5HCf8TcC+HuVQwxNmdbU7KiOx/659
dwe7wtA2q9krpPa2oQ7MDKsrtzDe7lbUPPtL55pLiS6/FCuzlMYkroD2jJrC0nbh
AgMBAAE=
-----END PUBLIC KEY-----";
const PRIVATEKEY = "-----BEGIN RSA PRIVATE KEY-----
MIIEogIBAAKCAQBmfmIb9VI4bd1FYsgKCTB0o9kD9+Yq0ah3o0hf41uBjkhd1Hu4
g9VP1VhKhvx9zyBxptQC194XRQj1Si+F+QGd6ca7gVILy2Qt+yt/8a7tLh9tS+Yv
u0u9qd6qzmslwXUg+mkt6gbqsuwFwQjo1uLv7uQBbmNmaa5TUNN5MGV1TlbCKIOq
Fvfd6dUhWgj7h+i+sYzhd3lBOH4x6WIL8O+P5jWYH7k7sW0LdL4oPT+o7pesL/2K
u99IqVd5HCf8TcC+HuVQwxNmdbU7KiOx/659dwe7wtA2q9krpPa2oQ7MDKsrtzDe
7lbUPPtL55pLiS6/FCuzlMYkroD2jJrC0nbhAgMBAAECggEAJwwvRIeJ84Wnry+b
Fne5xjZGD4T6Y4XMsQe8IwvzaMBUQasaF4niUq/tGzeiKiwnKCVu0EZ9jk/2wbyp
rljXjNnrpLP4FhY+7GWshOoGwsW3o2QvJjAtAu0cr+ZEW/YAMbg1KhDm4bOYx/hq
9Iopm09f/kuYAwRwLb21U45kmPPpdgbCtS+y/UFCGb9Jf2y8GJ6N85hIONrlCpZ0
OLsDSryoFpRWh8nfz2vgkddYq+9yfF9JcFMm8NqLRvLFEt/JpSw/cTRjhUAajMWG
TLJiwL2k8Mgp22Q1fty+rYVZjypqwxZyn3IEBjf0wQKpddJgdIrlbjMOkes2sGS1
aPuJgQKBgQDHOV6j81f2UF3jivDDGJzz0PQwDzEzfwY9tUhvqUH3dW3lXtbnpsRU
NmDLH+Brd9KiiLcjvuvMHqbHQygZ85O3TDP7Fk8pYxcRTQKr2SoZVlxiUxOSwe7T
tyUAwmrC3vwQIzfc7xXAfGAqKuIEqcGF3ZoxSh/G/F4YZIjKoF6klQKBgQCDs+5z
EoHlSRghipXcf1uKfMOcc2PDKcMMLkJNXAwbua5WwKmOR2iMeWsPWsxwanGGnJrU
4O2cg8/pW0tzxOyZmBSzTDaCzxUl49F5dvix6VXPVv8v96uKd9cDtf7wa/+wTaHN
RgG6bors7PEii8IEg4YD/2TJ9Eq+FeBRl/wKHQKBgE87+PD56mUTZ5QFAWaGln2+
ADBRCn9xqLjILcwDJDJYKOINyykMwY7ApdSDlA0J9eMgvr3fxAZFto0Pn5Zassg2
7YGSSN+sAlOmLkbMsxqqp0mPwvweV28qqvPXXwCsENIWP7SF/j+098YtkgDKxyYH
kbjCw0qvuni3G1SDXcDVAoGALpSV8lcKYBjRExPb2wekTb/Aw3XARAdiN82on4m9
spSih4ZOscapwH01E1Iu2lebXVvIGyCOiXvJLiSe9AwjDlPOhnefE/a/6Q3BSa99
WBdJxTJkwQdcwXWPM33L9CaOYUGjx2FKPTDZq+BFjM1XSpdl78YFVLQ1NzOvicwy
VTUCgYEAiQa6YTj9FAjmEDmQBmQejwNrH2Z4NUjh+9YDIcy5DuB3I/ZRfW2pDHVw
vSN0ueqd/Gl4Fqq00iqM7dNjd9co7B7l13KsK4ttX9ts9LKLRHLiZDIB+4aVoOf0
fH1GOHRQ3YXyNk866aVWSiKbCwQqfSCEROQcfv510F+b6oaqjGo=
-----END RSA PRIVATE KEY-----";

    /**
     * aes编码
     * @param  string $sPassword 密码
     * @param  string $sData     明文
     * @param  string $sIV       加密向量
     * @param  string $sMethod   加密方式(默认：AES-256-CFB)
     * @return string            密文
     */
    public static function aesEncrypt($sPassword, $sData, $sMethod = "AES-256-CFB8") {
        $sIV = chr(0x16) . chr(0x61) . chr(0x0F) . chr(0x3A) . chr(0x37) . chr(0x3D) . chr(0x1B) . chr(0x51) . chr(0x4A) . chr(0x39) . chr(0x5A) . chr(0x79) . chr(0x29) . chr(0x08) . chr(0x01) . chr(0x22);
        $sPassword = base64_decode($sPassword);
        $sPassword = substr(hash('sha256', $sPassword, true), 0, 32);
        $sEncrypted = base64_encode(openssl_encrypt($sData, $sMethod, $sPassword, OPENSSL_RAW_DATA, $sIV));
        return $sEncrypted;
    }

    /**
     * aes解码
     * @param  string $sPassword 密码
     * @param  string $sData     密文
     * @param  string $sIV       加密向量
     * @param  string $sMethod   加密方式(默认：AES-256-CFB8)
     * @return string            明文
     */
    public static function aesDecrypt($sPassword, $sData, $sMethod = "AES-256-CFB8") {
        $sIV = chr(0x16) . chr(0x61) . chr(0x0F) . chr(0x3A) . chr(0x37) . chr(0x3D) . chr(0x1B) . chr(0x51) . chr(0x4A) . chr(0x39) . chr(0x5A) . chr(0x79) . chr(0x29) . chr(0x08) . chr(0x01) . chr(0x22);
        $sPassword = base64_decode($sPassword);
        $sDecrypted = openssl_decrypt(base64_decode($sData), $sMethod, $sPassword, OPENSSL_RAW_DATA, $sIV);
        return $sDecrypted;
    }

    /**
     * rsa公钥加密
     * @param  string $sPublicKey 公钥
     * @param  string $sData      明文
     * @return string             密文
     */
    public static function rsaPublicKeyEncrypt($sPublicKey, $sData) {
        $res = openssl_get_publickey($sPublicKey);
        //$sData = base64_encode($sData);
        openssl_public_encrypt($sData, $sEncrypt, $res);
        openssl_free_key($res);
        $sEncrypt = base64_encode($sEncrypt);
        return $sEncrypt;
    }

    /**
     * rsa密钥解密
     * @param  string $sPrivateKey 密钥
     * @param  string $sData       密文
     * @return string              明文
     */
    public static function rsaPrivateKeyDecrypt($sPrivateKey, $sData) {
        $res = openssl_get_privatekey($sPrivateKey);
        $sEncrypt = base64_decode($sData);
        openssl_private_decrypt($sEncrypt, $sDecrypt, $res);
        openssl_free_key($res);
        return $sDecrypt;
    }
    /**
     * @param string $sTextz            明文
     * @param string $sRasEncryptedKey  经过RSA加密后的密文
     * @return string                   明文
     */
    public static function rsa_aes_decrypt($sText, $sRasEncryptedKey) {
        return self::aesDecrypt(self::rsaPrivateKeyDecrypt(self::PRIVATEKEY, $sRasEncryptedKey), $sText);
    }
}