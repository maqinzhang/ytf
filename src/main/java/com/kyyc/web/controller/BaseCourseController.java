package com.kyyc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyyc.common.model.BaseCourse;
import com.kyyc.common.service.BaseCourseService;
import com.kyyc.core.model.Constants;

/**
 * WEB端课程管理
 * 
 * @author MaQinZh 2016年7月18日下午4:53:34
 */
@RequestMapping("/web/baseCourse")
@Controller("webBaseCourseController")
public class BaseCourseController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String VIEW_TO_LIST = "web/baseCourse/courseList";
	private static final String VIEW_COURSE_CONTENT = "web/baseCourse/coursePageContent";

	@Resource
	private BaseCourseService baseCourseService;

	/**
	 * 课程列表
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {

		try {

			/**
			 * 查询课程列表
			 */
			PageHelper.startPage(pageNo, pageSize);
			PageHelper.orderBy("ID ASC");
			List<BaseCourse> courseList = baseCourseService.selectAll();

			PageInfo<BaseCourse> page = new PageInfo<BaseCourse>(courseList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("查询课程列表出错！", e);
		}
		return VIEW_TO_LIST;
	}

	/**
	 * 跳转到课程列表页面
	 */
	@RequestMapping("/coursePageContent")
	public String coursePageContent(
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {

		try {
			/**
			 * 查询课程列表
			 */
			PageHelper.startPage(pageNo, pageSize);
			PageHelper.orderBy("ID ASC");
			List<BaseCourse> courseList = baseCourseService.selectAll();

			PageInfo<BaseCourse> page = new PageInfo<BaseCourse>(courseList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("跳转到课程列表页面的时候发生错误：", e);
		}
		return VIEW_COURSE_CONTENT;
	}

	/**
	 * 保存基础课程信息
	 */
	@RequestMapping("/saveCourse")
	@ResponseBody
	public Object saveCourse(BaseCourse baseCourse) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			if (ObjectUtils.isEmpty(baseCourse.getId())) {
				baseCourse.setCreateTime(DateTime.now().toDate());
				baseCourseService.save(baseCourse);
			} else {
				baseCourseService.updateNotNull(baseCourse);
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("保存基础课程信息的时候发生错误：", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
	}

	/**
	 * 删除基础课程信息
	 */
	@RequestMapping("/deleteCourse")
	@ResponseBody
	public Object deleteCourse(Integer id) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			if (!ObjectUtils.isEmpty(id)) {
				baseCourseService.deleteById(id);
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("删除基础课程信息的时候发生错误：", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
	}
}
