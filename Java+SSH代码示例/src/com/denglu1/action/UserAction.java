package com.denglu1.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.denglu1.pojo.User;
import com.denglu1.service.UserService;
import com.denglu1.utils.EncryptUtil;
import com.denglu1.utils.MySessionContext;
import com.denglu1.utils.Request;
import com.denglu1.utils.ResultUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 第三方服务器状态码: 
 *  0   成功 
 * -700 第三方服务器返回参数错误 
 * -800 用户名或者密码错误 
 * -801 用户名已经存在 
 * -900 服务器错误 
 * -901 网络错误
 */
public class UserAction {

	UserService userService;
	List<User> list;

	/**
	 * 开放平台注册
	 * 
	 * @param string sUserName 用户名
	 * @param string sPassword 密码
	 * @param string sEncryptedAESKey 经过RSA公钥加密的AES密钥
	 */
	public void register() {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> param = Request.getReqData();

		String sUserName = param.get("sUserName");
		String sPassword = param.get("sPassword");
		String sEncryptedAESKey = param.get("sEncryptedAESKey");

		sPassword = EncryptUtil.rsa_aes_decrypt(sPassword, sEncryptedAESKey);

		if (userService.getUser(sUserName) == null) {
			User user = new User();
			user.setsUserName(sUserName);
			user.setsPwd(sPassword);
			userService.add(user);

			String sessionId = ServletActionContext.getRequest().getSession().getId();
			Map<String, Object> m = ActionContext.getContext().getSession();
			m.put("sUserName", sUserName);

			result.put("iCode", 0);
			result.put("sToken", sessionId);
			ResultUtils.toJson(result);
		} else {
			result.put("iCode", -801);
			result.put("sMsg", "用户已存在");
			ResultUtils.toJson(result);
		}
	}

	/**
	 * 开放平台登录
	 * 
	 * @param string sUserName 用户名
	 * @param string sPassword 密码
	 * @param string sEncryptedAESKey 经过RSA加密的AES密钥
	 */
	public void login() {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> param = Request.getReqData();

		String sUserName = param.get("sUserName");
		String sPassword = param.get("sPassword");
		String sEncryptedAESKey = param.get("sEncryptedAESKey");

		sPassword = EncryptUtil.rsa_aes_decrypt(sPassword, sEncryptedAESKey);

		User user = userService.getUser(sUserName);

		if (user != null) {
			if (sPassword.equals(user.getsPwd())) {
				String sessionId = ServletActionContext.getRequest().getSession().getId();
				// 用户登录态保存，比如存入session或者redis
				Map<String, Object> m = ActionContext.getContext().getSession();
				m.put("sUserName", sUserName);

				result.put("iCode", 0);
				result.put("sMsg", "SUCCESS");
				result.put("sToken", sessionId);
				ResultUtils.toJson(result);
			} else {
				result.put("iCode", -800);
				result.put("sMsg", "账号名或密码错误");
				ResultUtils.toJson(result);
			}
		} else {
			result.put("iCode", -800);
			result.put("sMsg", "账号名或密码错误");
			ResultUtils.toJson(result);
		}
	}

	/**
	 * 开放平台修改密码
	 * 
	 * @param string sUserName 用户名
	 * @param string sOldPassword 旧密码
	 * @param string sNewPassword 新密码
	 * @param string sEncryptedAESKey 经过RSA加密的AES密钥
	 */
	public void modifyPass() {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> param = Request.getReqData();

		String sUserName = param.get("sUserName");
		String sOldPassword = param.get("sOldPassword");
		String sNewPassword = param.get("sNewPassword");
		String sEncryptedAESKey = param.get("sEncryptedAESKey");

		sOldPassword = EncryptUtil.rsa_aes_decrypt(sOldPassword, sEncryptedAESKey);
		sNewPassword = EncryptUtil.rsa_aes_decrypt(sNewPassword, sEncryptedAESKey);

		User user = new User();
		user.setsUserName(sUserName);
		user.setsPwd(sOldPassword);
		List<User> list = userService.getUser(user);

		if (list.size() != 0) {
			User newUser = list.get(0);
			newUser.setsPwd(sNewPassword);
			userService.update(newUser);

			String sessionId = ServletActionContext.getRequest().getSession().getId();
			// 用户登录态保存，比如存入session或者redis
			Map<String, Object> m = ActionContext.getContext().getSession();
			m.put("sUserName", sUserName);

			result.put("iCode", 0);
			result.put("sToken", sessionId);
			ResultUtils.toJson(result);
		} else {
			result.put("iCode", -800);
			result.put("sMsg", "修改密码错误");
			ResultUtils.toJson(result);
		}
	}

	/**
	 * 在完成扫码提交后，指定浏览器重定向的页面，需要对前端传过来的sToken(比如sessionId)做登录态检查
	 * 其他比如注册重定向、修改密码重定向的页面可根据自己的业务逻辑自定义
	 * 
	 * @param String sToken 账号密码校验成功后的会话ID
	 */
	public void loginByToken() {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> param = Request.getReqData();

		String sToken = param.get("sToken");

		// 根据 sessionId 获取 session
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession session = myc.getSession(sToken);

		if (session != null) {
			result.put("sMsg", "重定向成功，当前用户名是: " + (String) session.getAttribute("sUserName"));
		} else {
			result.put("sMsg", "重定向失败，当前用户名未登录！");
		}
		ResultUtils.toJson(result);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

}
