<?php

/**
 * 在开放平台记录的登录地址
 * @param string sUserName           用户名
 * @param string sPassword           密码
 * @param string sEncryptedAESKey    经过RSA加密的AES密钥
 * @param string sState              防止CSRF攻击的随机参数
 */
/**
 * 第三方服务器状态码
 * 0        成功
 * -700     第三方服务器返回参数错误
 * -800     用户名或者密码错误
 * -801     用户名已经存在
 * -900     服务器错误
 * -901     网络错误
 */

require_once './util/rsa.php';
require_once './logic/userLogic.php';

$sUserName = $_POST['sUserName'];
$sPassword = $_POST['sPassword'];
$sEncryptedAESKey = $_POST['sEncryptedAESKey'];
$sState = $_POST['sState'];

// 进行解密
$sPassword = EncryptUtil::rsa_aes_decrypt($sPassword, $sEncryptedAESKey);

// 业务逻辑处理
if (userLogic::login($sUserName, $sPassword)) {
    // 用户登录态保存，可存入session或者redis，以存入session为例
    session_start();
    $_SESSION['sUserName'] = $sUserName;
    // 存储由前端转发过来的state值（state值已由前端写入cookie中，以后请求会自动携带），在重定向接口会做校验，防止CSRF攻击（黑客窃取token）
    $_SESSION['sState'] = $sState;
    // 可以自定义为其他值，只要重定向后的接口能够处理这个值，具体请查看开放平台
    $sToken = session_id();
    echo json_encode(array('iCode' => 0, 'sMsg' => 'SUCCESS', 'sToken' => $sToken));

} else {
    echo json_encode(array('iCode' => -800, 'sMsg' => '账号名或密码错误'));
}
