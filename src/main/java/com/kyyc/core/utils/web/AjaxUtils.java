package com.kyyc.core.utils.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断AJAX请求
 *
 * @author MaQinZh 2016年1月7日下午1:54:34
 * @since version 1.0.0
 */
public class AjaxUtils {

	public static boolean isAjaxRequest(HttpServletRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(HttpServletRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}
}
