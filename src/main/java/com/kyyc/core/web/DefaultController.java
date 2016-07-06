package com.kyyc.core.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统异常处理/系统默认处理器
 * 
 * <pre>
 * 1、加载页面出错的时候,对应跳转的处理页面
 * 2、系统默认跳转页面、等业务处理均可放在此处
 * 3、...
 * </pre>
 * 
 * @author MaQinZh 2014-11-12上午11:32:25
 * @since version 1.0.0
 */
@Controller
public class DefaultController {

	/** 全局404错误页面 **/
	private final static String VIEW_GLOBAL_ERROR_404 = "global/404";
	/** 全局500错误页面 **/
	private final static String VIEW_GLOBAL_ERROR_500 = "global/500";

	/**
	 * 系统异常处理(404)
	 * 
	 * <pre>
	 * 加载页面出错的时候,对应跳转的处理页面
	 * </pre>
	 * 
	 * @param map
	 *            Model对象
	 * @return 返回400处理页面
	 */
	@RequestMapping("/404")
	public String handleNotFoundRequest(Model model) {
		model.addAttribute("http-error-code", HttpStatus.INTERNAL_SERVER_ERROR);
		return VIEW_GLOBAL_ERROR_404;
	}

	/**
	 * 系统异常处理(500)
	 * 
	 * <pre>
	 * 加载页面出错的时候,对应跳转的处理页面
	 * </pre>
	 * 
	 * @param map
	 *            Model对象
	 * @return 返回500处理页面
	 */
	@RequestMapping("/500")
	public String handleInternalServerError(Model model) {
		model.addAttribute("http-error-code", HttpStatus.INTERNAL_SERVER_ERROR);
		return VIEW_GLOBAL_ERROR_500;
	}
}
