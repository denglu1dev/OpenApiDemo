<?php

/**
 * 在开放平台记录的注册地址
 * @param string sUserName           用户名
 * @param string sPassword           密码
 * @param string sEncryptedAESKey    经过RSA加密的AES密钥
 * @param string sState              防止CSRF攻击的随机参数
 */

require_once './util/rsa.php';

$sUserName = $_POST['sUserName'];
$sPassword = $_POST['sPassword'];
$sEncryptedAESKey = $_POST['sEncryptedAESKey'];
$sState = $_POST['sState'];

// 进行解密
$sPassword = EncryptUtil::rsa_aes_decrypt($sPassword, $sEncryptedAESKey);

// 业务逻辑处理
$success = true;
if ($success) {
    session_start();
    $_SESSION['userName'] = $sUserName;
    $_SESSION['sState'] = $sState;
    echo json_encode(array('iCode' => 0, 'sToken' => session_id()));
} else {
    echo json_encode(array('iCode' => -801, 'sMsg' => '用户已存在'));
}
