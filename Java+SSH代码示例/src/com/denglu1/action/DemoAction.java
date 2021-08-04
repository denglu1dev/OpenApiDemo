package com.denglu1.action;

import com.denglu1.utils.ResultUtils;

/**
 * 前端请求第三方登录易注册接口，这里可提供拦截或者记录日志
 */
public class DemoAction {

	// 在open.denglu.net.cn上创建应用后会生成一个appId
	private static final String sAppId = "7027c35ca24cc2715937ab3fab3d32fb";

	// 登录易APP保存的网页地址, 注意此地址的域名要和在开放平台添加应用时填写的授权域名相同
	// http://demo.denglu1.cn仅为示例
	private static final String sDomain = "http://demo.denglu1.cn";

	public String demo() {
		return "demo";
	}

	public void loginByDenglu() {

		// sRedirectUrl根据业务需求修改，填入处理登录凭证token的地址
		String sRedirectUrl = "http://demo.denglu1.cn/denglu1/loginByToken";
		String loginUrl = "https://qrconnect.denglu.net.cn/connect.php?sAppId=" + sAppId + "&sUrl=" + sDomain
				+ "&sRedirectUrl=" + sRedirectUrl + "&sType=login&sResType=web";
		ResultUtils.redirectUrl(loginUrl);
	}

	public void registerByDenglu() {

		// sRedirectUrl根据业务需求修改，如果是注册成功之后自动帮助用户登录，可填入登录重定向的地址
		String sRedirectUrl = "http://demo.denglu1.cn/denglu1/loginByToken";
		String registerUrl = "https://qrconnect.denglu.net.cn/connect.php?sAppId=" + sAppId + "&sUrl=" + sDomain
				+ "&sRedirectUrl=" + sRedirectUrl + "&sType=register&sResType=web";
		ResultUtils.redirectUrl(registerUrl);
	}

	public void changePasswordByDenglu() {

		// sRedirectUrl根据业务需求修改，比如修改完密码之后重定向到登录的地址，或者直接重定向到应用主页
		String sRedirectUrl = "http://demo.denglu1.cn/denglu1/loginByToken";
		String changePwdUrl = "https://qrconnect.denglu.net.cn/connect.php?sAppId=" + sAppId + "&sUrl=" + sDomain
				+ "&sRedirectUrl=" + sRedirectUrl + "&sType=change_psw&sResType=web";
		ResultUtils.redirectUrl(changePwdUrl);
	}

}
