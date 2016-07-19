package com.kyyc.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Condition;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyyc.common.model.BaseCourse;
import com.kyyc.common.model.Course;
import com.kyyc.common.model.UserCourseRecord;
import com.kyyc.common.model.UserInfo;
import com.kyyc.common.service.BaseCourseService;
import com.kyyc.common.service.CourseService;
import com.kyyc.common.service.UserCourseRecordService;
import com.kyyc.common.service.UserInfoService;
import com.kyyc.core.model.Constants;

/**
 * WEB端课程管理
 * 
 * @author MaQinZh 2016年7月6日下午4:53:34
 */
@RequestMapping("/web/course")
@Controller("webCourseController")
public class CourseController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String VIEW_TO_LIST = "web/course/courseList";
	private static final String VIEW_COURSE_CONTENT = "web/course/coursePageContent";
	private static final String VIEW_TO_COURSE_INFO = "web/course/courseInfo";
	private static final String VIEW_TO_COURSE_DETAIL = "web/course/courseDetail";
	private static final String VIEW_TO_ORDER_RECORD = "web/course/orderRecord";
	private static final String VIEW_TO_ORDER_DETAIL = "web/course/orderDetail";

	@Resource
	private CourseService courseService;
	@Resource
	private BaseCourseService baseCourseService;
	@Resource
	private UserCourseRecordService userCourseRecordService;
	@Resource
	private UserInfoService userInfoService;

	/**
	 * 课程列表
	 */
	@RequestMapping("/list")
	public String list(String beginDate, String endDate,
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {

		try {

			/**
			 * 预约开始时间
			 */
			if (StringUtils.isEmpty(beginDate)) {

				beginDate = DateTime.now().minusDays(DateTime.now().getDayOfWeek() - 1).toString(Constants.DATETIME_10);
			}

			/**
			 * 预约结束时间
			 */
			if (StringUtils.isEmpty(endDate)) {
				endDate = DateTime.now().toString(Constants.DATETIME_10);
			}

			/**
			 * 设置查询条件
			 */
			Condition condition = new Condition(Course.class);
			condition.createCriteria().andBetween("courseDate", beginDate, endDate);

			/**
			 * 查询课程列表
			 */
			PageHelper.startPage(pageNo, pageSize);
			PageHelper.orderBy("COURSE_DATE ASC, BEGIN_TIME ASC, END_TIME ASC");
			List<Course> courseList = courseService.selectByCondition(condition);

			/**
			 * 迭代课程， 设置当前报考人数
			 */
			for (Course _course : courseList) {
				UserCourseRecord record = new UserCourseRecord();
				record.setCourseId(_course.getId());
				int cnt = userCourseRecordService.count(record);
				_course.setPersonNum(cnt);
			}

			PageInfo<Course> page = new PageInfo<Course>(courseList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endDate", endDate);
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
	public String coursePageContent(String beginDate, String endDate,
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {

		try {

			/**
			 * 预约开始时间
			 */
			if (StringUtils.isEmpty(beginDate)) {
				beginDate = DateTime.now().minusDays(DateTime.now().getDayOfWeek() - 1).toString(Constants.DATETIME_10);
			}

			/**
			 * 预约结束时间
			 */
			if (StringUtils.isEmpty(endDate)) {
				endDate = DateTime.now().toString(Constants.DATETIME_10);
			}

			/**
			 * 设置查询条件
			 */
			Condition condition = new Condition(Course.class);
			condition.createCriteria().andBetween("courseDate", beginDate, endDate);

			/**
			 * 查询课程列表
			 */
			PageHelper.startPage(pageNo, pageSize);
			PageHelper.orderBy("COURSE_DATE ASC, BEGIN_TIME ASC, END_TIME ASC");
			List<Course> courseList = courseService.selectByCondition(condition);

			/**
			 * 迭代课程， 设置当前报考人数
			 */
			for (Course _course : courseList) {
				UserCourseRecord record = new UserCourseRecord();
				record.setCourseId(_course.getId());
				int cnt = userCourseRecordService.count(record);
				_course.setPersonNum(cnt);
			}

			PageInfo<Course> page = new PageInfo<Course>(courseList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("跳转到课程列表页面的时候发生错误：", e);
		}
		return VIEW_COURSE_CONTENT;
	}

	/**
	 * 预约课程新增、修改
	 */
	@RequestMapping("/info")
	public String info(String id, Model mode) {
		try {
			Course course = new Course();
			if (StringUtils.isNotEmpty(id)) {
				/**
				 * 获取预约记录
				 */
				course = courseService.selectById(id);
			}

			/**
			 * 基础课程列表
			 */
			List<BaseCourse> baseCourseList = baseCourseService.selectAll();

			mode.addAttribute("course", course);
			mode.addAttribute("baseCourseList", baseCourseList);
		} catch (Exception e) {
			LOG.error("预约课程新增、修改出错！", e);
		}
		return VIEW_TO_COURSE_INFO;
	}

	/**
	 * 保存课程信息
	 */
	@RequestMapping("/saveCourse")
	@ResponseBody
	public Object saveCourse(Course course) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			if (ObjectUtils.isEmpty(course.getId())) {
				course.setCreateTime(DateTime.now().toDate());
				courseService.save(course);
			} else {
				courseService.updateNotNull(course);
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("保存课程信息的时候发生错误：", e);
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

				/**
				 * 删除课程
				 */
				courseService.deleteById(id);

				/**
				 * 删除预约记录
				 */
				UserCourseRecord record = new UserCourseRecord();
				record.setCourseId(id);
				userCourseRecordService.delete(record);
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("删除课程信息的时候发生错误：", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
	}

	/**
	 * 课程详情
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Integer id, Model mode) {
		try {
			if (ObjectUtils.isEmpty(id)) {
				throw new IllegalArgumentException("请传入正确的课程ID！");
			}

			/**
			 * 获取预约记录
			 */
			Course course = courseService.selectById(id);

			/**
			 * 设置当前报考人数
			 */
			UserCourseRecord _record = new UserCourseRecord();
			_record.setCourseId(id);
			List<UserCourseRecord> userCourseRecordList = userCourseRecordService.select(_record);
			course.setPersonNum(userCourseRecordList.size());

			/**
			 * 设置预约记录的客户昵称
			 */
			for (UserCourseRecord record : userCourseRecordList) {
				UserInfo userInfo = userInfoService.selectById(record.getUserId());
				record.setUserInfo(userInfo);
			}

			mode.addAttribute("course", course);
			mode.addAttribute("userCourseRecordList", userCourseRecordList);
		} catch (Exception e) {
			LOG.error("课程详情出错！", e);
		}
		return VIEW_TO_COURSE_DETAIL;
	}

	/**
	 * 预约记录
	 */
	@RequestMapping("/orderRecord")
	public String orderRecord(String courseDate, String beginDate, String endDate, Model mode) {
		try {
			/**
			 * 开课时间
			 */
			if (StringUtils.isEmpty(courseDate)) {
				courseDate = DateTime.now().toString(Constants.DATETIME_10);
			}

			/**
			 * 预约开始时间
			 */
			if (StringUtils.isEmpty(beginDate)) {
				beginDate = DateTime.now().toString(Constants.DATETIME_10) + " 00:00:00";
			}

			/**
			 * 预约结束时间
			 */
			if (StringUtils.isEmpty(endDate)) {
				endDate = DateTime.now().toString(Constants.DATETIME_10) + " 23:59:59";
			}
			/**
			 * 转换日期
			 */
			Date _beginDate = DateTime.parse(beginDate, DateTimeFormat.forPattern(Constants.DATETIME_14_COMMON))
					.toDate();
			Date _endDate = DateTime.parse(endDate, DateTimeFormat.forPattern(Constants.DATETIME_14_COMMON)).toDate();

			/**
			 * 设置查询条件
			 */
			Condition condition = new Condition(UserCourseRecord.class);
			condition.createCriteria().andBetween("createTime", _beginDate, _endDate)
					.andEqualTo("courseDate", courseDate);
			/**
			 * 查询
			 */
			List<UserCourseRecord> recordList = userCourseRecordService.selectByCondition(condition);

			/**
			 * 迭代获取课程信息
			 */
			for (UserCourseRecord _record : recordList) {

				Course course = courseService.selectById(_record.getCourseId());

				/**
				 * 设置当前报考人数
				 */
				UserCourseRecord rcd = new UserCourseRecord();
				rcd.setCourseId(course.getId());
				int cnt = userCourseRecordService.count(rcd);
				course.setPersonNum(cnt);

				/**
				 * 设置课程信息
				 */
				_record.setCourse(course);

				/**
				 * 设置用户信息
				 */
				UserInfo userInfo = userInfoService.selectById(_record.getUserId());
				_record.setUserInfo(userInfo);
			}

			mode.addAttribute("courseRecordList", recordList);
		} catch (Exception e) {
			LOG.error("查询预约课程记录出错！", e);
		}
		return VIEW_TO_ORDER_RECORD;
	}

	/**
	 * 预约课程详情
	 */
	@RequestMapping("/orderDetail/{id}")
	public String orderDetail(@PathVariable int id, Model mode) {
		try {

			/**
			 * 获取预约记录
			 */
			UserCourseRecord record = userCourseRecordService.selectById(id);

			/**
			 * 获取课程信息
			 */
			Course course = courseService.selectById(record.getCourseId());

			/**
			 * 设置当前报考人数
			 */
			UserCourseRecord _record = new UserCourseRecord();
			_record.setCourseId(record.getCourseId());
			int cnt = userCourseRecordService.count(_record);
			course.setPersonNum(cnt);

			/**
			 * 设置课程
			 */
			record.setCourse(course);

			/**
			 * 设置用户信息
			 */
			UserInfo userInfo = userInfoService.selectById(_record.getUserId());
			_record.setUserInfo(userInfo);

			mode.addAttribute("userCourseRecord", record);
		} catch (Exception e) {
			LOG.error("预约课程详情出错！", e);
		}
		return VIEW_TO_ORDER_DETAIL;
	}

}
