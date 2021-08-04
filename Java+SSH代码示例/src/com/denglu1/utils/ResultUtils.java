package com.denglu1.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

public class ResultUtils {

	public static void toJson(Object data) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Gson gson = new Gson();
		String result = gson.toJson(data);
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void redirectUrl(String url) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", url);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}