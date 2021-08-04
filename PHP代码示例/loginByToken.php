<?php

/**
 * 在完成扫码提交后，指定浏览器重定向的页面，需要对前端传过来的sToken(比如sessionId)做登录态检查
 * 
 * 其他比如注册重定向、修改密码重定向的页面可根据自己的业务逻辑自定义
 * 
 * @param string sToken          账号密码校验成功后的会话ID
 */
$sToken = $_GET['sToken'];

try {
  session_id($sToken);
  session_start();

  // 校验客户端重定向时的state(在cookie中)，如果不存在或者state不一致，则出错（遭受到了CSRF攻击）
  $sClientState = $_COOKIE['sState'];
  $sValidState = $_SESSION['sState'];
  if (isset($sValidState) && $sClientState != $sValidState) {
    echo "客户端请求没有携带sState参数，可能遭受到了CSRF攻击";
    exit;
  }

  echo '<br><br>重定向成功，当前用户名是' . $_SESSION['sUserName'];
  echo '<br><br>注：重定向的页面[登录/注册/修改密码这三个的地址不一定一样，根据业务自定义]';
  // 如果token有效，则证明登录成功，此时需要根据业务具体需求保存用户登录易态或者是使token失效
  // 比如直接重定向页面，或者是返回json数据
} catch (Exception $e) {
  var_dump($e);
}
