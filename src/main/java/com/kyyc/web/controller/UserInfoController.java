package com.kyyc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyyc.common.model.UserInfo;
import com.kyyc.common.service.UserInfoService;
import com.kyyc.core.model.Constants;

@RequestMapping("/web/userInfo")
@Controller("webUserInfoController")
public class UserInfoController {
	private static final Logger LOG = LoggerFactory.getLogger(UserInfoController.class);

	/** 关注用户列表信息视图 **/
	private static final String VIEW_USER_LIST = "web/userInfo/userInfoList";
	/** 关注用户列表分页内容页面视图 **/
	private static final String VIEW_USER_CONTENT = "web/userInfo/userInfoPageContent";

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 跳转到关注用户列表页面
	 */
	@RequestMapping("/userInfoList")
	public String requestList(@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {
		try {
			PageHelper.startPage(pageNo, pageSize);
			List<UserInfo> userInfoList = userInfoService.select(null);
			PageInfo<UserInfo> page = new PageInfo<UserInfo>(userInfoList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("page", page);

		} catch (Exception e) {
			LOG.error("查询关注用户列表的时候发生错误！", e);
		}
		return VIEW_USER_LIST;
	}

	/**
	 * 跳转到关注用户列表页面
	 */
	@RequestMapping("/userInfoPageContent")
	public String requestPageContent(UserInfo userInfo,
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {

		try {
			Condition condition = new Condition(UserInfo.class);
			Criteria criteria = condition.createCriteria();
			if (StringUtils.isNotEmpty(userInfo.getNickName())) {
				criteria.andLike("nickName", "%" + userInfo.getNickName() + "%");
			}
			PageHelper.startPage(pageNo, pageSize);
			List<UserInfo> userInfoList = userInfoService.selectByCondition(condition);
			PageInfo<UserInfo> page = new PageInfo<UserInfo>(userInfoList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("跳转到关注用户列表页面的时候发生错误：", e);
		}
		return VIEW_USER_CONTENT;
	}

	/**
	 * 从微信端更新关注用户列表
	 */
	@RequestMapping("/transferFromWexin")
	@ResponseBody
	public Object transferFromWexin() {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			userInfoService.transferFromWexin();
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("查询询价顾问列表的时候发生错误：", e);
			resMap.put("success", false);
		}
		return resMap;
	}
}
