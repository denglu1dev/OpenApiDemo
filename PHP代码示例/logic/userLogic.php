<?php

class userLogic {
  /**
   * 登录校验
   *
   * @param [type] $sUserName
   * @param [type] $sPassword
   * @return void
   */
  public static function login($sUserName, $sPassword) {
    // 这里进行登录校验，比如查询数据库
    $arrTestUser = array('testUser' => 'testPass');
    if ($arrTestUser[$sUserName] === $sPassword) {
      return true;
    } else {
      return false;
    }
  }
}