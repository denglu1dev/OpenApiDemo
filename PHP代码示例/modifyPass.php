<?php

/**
 * 在开放平台记录的修改密码地址
 * @param string sUserName           用户名
 * @param string sOldPassword        旧密码
 * @param string sNewPassword        新密码
 * @param string sEncryptedAESKey    经过RSA加密的AES密钥
 * @param string sState              防止CSRF攻击的随机参数
 */

require_once './util/rsa.php';

$sUserName = $_POST['sUserName'];
$sOldPassword = $_POST['sOldPassword'];
$sNewPassword = $_POST['sNewPassword'];
$sEncryptedAESKey = $_POST['sEncryptedAESKey'];
$sState = $_POST['sState'];

// 进行解密
$sOldPassword = EncryptUtil::rsa_aes_decrypt($sOldPassword, $sEncryptedAESKey);
$sNewPassword = EncryptUtil::rsa_aes_decrypt($sNewPassword, $sEncryptedAESKey);

// 业务逻辑处理
$success = true;
if ($success) {
    session_start();
    $_SESSION['sUserName'] = $sUserName;
    $_SESSION['sState'] = $sState;
    echo json_encode(array('iCode' => 0, 'sToken' => session_id()));
} else {
    echo json_encode(array('iCode' => -800, 'sMsg' => '修改密码错误'));
}
