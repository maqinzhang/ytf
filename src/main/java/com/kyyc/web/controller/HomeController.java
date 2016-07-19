package com.kyyc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 首页内容
 * 
 * <pre>
 * </pre>
 *
 * @author MaQinZh 2015年12月2日下午4:41:08
 * @since version 1.0.0
 */
@Controller
public class HomeController {

	//private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	private final static String VIEW_DASHBOARD = "web/home/dashboard";
	private final static String VIEW_MENU = "web/home/menu";
	private final static String VIEW_TOP = "web/home/top";
	private final static String VIEW_DEFAULT = "web/home/default";

	/**
	 * 主工作区页面
	 */
	@RequestMapping("/dashboard")
	public String dashboard() {

		return VIEW_DASHBOARD;
	}

	/**
	 * 顶部菜单页面
	 */
	@RequestMapping("/top")
	public String top() {

		return VIEW_TOP;
	}

	/**
	 * 左侧菜单页面
	 */
	@RequestMapping("/menu")
	public String menu(Model model) {
		return VIEW_MENU;
	}

	/**
	 * 工作台默认页面
	 */
	@RequestMapping("/default")
	public String _default(Model model) {
		return VIEW_DEFAULT;
	}

}
