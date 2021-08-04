package com.denglu1.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * 获取POST或GET请求的数据,不支持数组形式数据,一般使用场景是对方使用POST方式传递数据过来
 * @return Map<String,String>
 */
public class Request {

	public static Map<String, String> getReqData() {
        Map<String, String> param = new HashMap<String, String>();

        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                param.put(key, values[i]);
            }
        }
        return param;
    }
	
}
