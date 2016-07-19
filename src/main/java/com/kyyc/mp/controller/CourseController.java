package com.kyyc.mp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.kyyc.common.model.Course;
import com.kyyc.common.model.UserCourseRecord;
import com.kyyc.common.service.CourseService;
import com.kyyc.common.service.UserCourseRecordService;
import com.kyyc.core.model.Constants;
import com.kyyc.mp.service.WeChatMpService;

/**
 * 公众号课程管理
 * 
 * @author MaQinZh 2016年7月6日下午4:53:34
 */
@RequestMapping("/course")
@Controller
public class CourseController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String VIEW_TO_LIST = "mp/course/list";
	private static final String VIEW_TO_DETAIL = "mp/course/detail";
	private static final String VIEW_TO_RESULT = "mp/course/result";
	private static final String VIEW_TO_ORDER_RECORD = "mp/course/orderRecord";
	private static final String VIEW_TO_ORDER_DETAIL = "mp/course/orderDetail";

	@Resource
	private CourseService courseService;
	@Resource
	private UserCourseRecordService userCourseRecordService;
	@Resource
	private WeChatMpService weChatMpService;

	/**
	 * 课程列表
	 */
	@RequestMapping("/list")
	public String list(String courseDate, HttpServletRequest request, HttpServletResponse response, Model mode) {

		try {
			/**
			 * 获取公众号用户惟一标识
			 */
			String openId = weChatMpService.getWeChatOpenId(request, response);
			/**
			 * 为空，返回
			 */
			if (StringUtils.isEmpty(openId)) {
				return null;
			}

			/**
			 * 时间选择列表
			 */
			List<String> dateList = new ArrayList<String>();

			if (StringUtils.isEmpty(courseDate)) {
				courseDate = DateTime.now().toString(Constants.DATETIME_10);
			}
			dateList.add(courseDate + " " + DateTime.parse(courseDate).dayOfWeek().getAsShortText());

			/**
			 * 当前查询后一天
			 */
			dateList.add(DateTime.parse(courseDate).plusDays(1).toString(Constants.DATETIME_10) + " "
					+ DateTime.parse(courseDate).plusDays(1).dayOfWeek().getAsShortText());

			/**
			 * 当前查询后二天
			 */
			dateList.add(DateTime.parse(courseDate).plusDays(2).toString(Constants.DATETIME_10) + " "
					+ DateTime.parse(courseDate).plusDays(2).dayOfWeek().getAsShortText());

			/**
			 * 查询课程列表
			 */
			Course course = new Course();
			course.setCourseDate(courseDate);
			List<Course> courseList = courseService.select(course);

			/**
			 * 迭代课程， 设置当前报考人数
			 */
			for (Course _course : courseList) {
				UserCourseRecord record = new UserCourseRecord();
				record.setCourseId(_course.getId());
				int cnt = userCourseRecordService.count(record);
				_course.setPersonNum(cnt);
			}

			mode.addAttribute("dateList", dateList);
			mode.addAttribute("courseList", courseList);
		} catch (Exception e) {
			LOG.error("查询课程列表出错！", e);
		}
		return VIEW_TO_LIST;
	}

	/**
	 * 课程详情
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model mode) {
		try {
			Course course = courseService.selectById(id);

			/**
			 * 设置当前报考人数
			 */
			UserCourseRecord record = new UserCourseRecord();
			record.setCourseId(id);
			int cnt = userCourseRecordService.count(record);
			course.setPersonNum(cnt);

			mode.addAttribute("course", course);
		} catch (Exception e) {
			LOG.error("查询课程详情出错！", e);
		}
		return VIEW_TO_DETAIL;
	}

	/**
	 * 预约课程
	 */
	@RequestMapping("/order/{id}")
	public String order(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {

			/**
			 * 获取公众号用户惟一标识
			 */
			String openId = weChatMpService.getWeChatOpenId(request, response);
			/**
			 * 为空，返回
			 */
			if (StringUtils.isEmpty(openId)) {
				return null;
			}

			/**
			 * 获取当前课程
			 */
			Course course = courseService.selectById(id);

			UserCourseRecord record = new UserCourseRecord();
			record.setCourseId(id);
			int count = userCourseRecordService.count(record);
			/**
			 * 判断当前预约是否已经满额，如果满额，则为候补人员等待
			 */
			if (course.getPersonLimit() <= count) {
				// model.addAttribute("success", false);
				// model.addAttribute("msg", "该课程已经达到预约人数，请选择其他课程！");
				// return VIEW_TO_RESULT;
				record.setIsStandby("1");
			} else {
				record.setIsStandby("0");
			}

			record.setUserId(openId);
			int recordCount = userCourseRecordService.count(record);

			/**
			 * 判断是否预约过；规则：基础课一人只能预约一次
			 */
			if ("1".equals(course.getIsBase())) {
				if (recordCount > 0) {
					model.addAttribute("success", false);
					model.addAttribute("msg", "该课程您已经预约过，请选择其他课程！");
					return VIEW_TO_RESULT;
				}
			}

			/**
			 * 保存预约记录
			 */
			record.setCreateTime(DateTime.now().toDate());
			userCourseRecordService.save(record);

			model.addAttribute("success", true);
			model.addAttribute("msg", "课程预约成功！");
		} catch (Exception e) {
			LOG.error("预约课程出错！", e);
			model.addAttribute("success", false);
			model.addAttribute("msg", "系统出错，请联系管理员！");
		}
		return VIEW_TO_RESULT;
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

			mode.addAttribute("userCourseRecord", record);
		} catch (Exception e) {
			LOG.error("预约课程详情出错！", e);
		}
		return VIEW_TO_ORDER_DETAIL;
	}

	/**
	 * 取消预约课程
	 */
	@RequestMapping("/cancle/{id}")
	public String cancle(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {

			/**
			 * 获取公众号用户惟一标识
			 */
			String openId = weChatMpService.getWeChatOpenId(request, response);
			/**
			 * 为空，返回
			 */
			if (StringUtils.isEmpty(openId)) {
				return null;
			}

			UserCourseRecord userCourseRecord = userCourseRecordService.selectById(id);

			/**
			 * 为空，返回
			 */
			if (ObjectUtils.isEmpty(userCourseRecord)) {
				throw new NullPointerException("当前预约记录不存在，请传入正确的参数值！");
			}

			/**
			 * 取消预约记录
			 */
			userCourseRecordService.deleteById(id);

			/**
			 * 删除非候补的时候，需要将后续候补置为预约成功
			 */
			if ("0".equals(userCourseRecord.getIsStandby())) {
				/**
				 * 获取第一条排队的记录
				 */
				UserCourseRecord record = new UserCourseRecord();
				record.setCourseId(userCourseRecord.getCourseId());
				record.setIsStandby("1");

				PageHelper.startPage(Integer.parseInt(Constants.DEFAULT_PAGE_NO),
						Integer.parseInt(Constants.DEFAULT_PAGE_SIZE), false);
				record = userCourseRecordService.selectOne(record);

				/**
				 * 非空时，更新为预约成功
				 */
				if (!ObjectUtils.isEmpty(record)) {
					record.setIsStandby("0");
					userCourseRecordService.update(record);
				}
			}

			model.addAttribute("success", true);
			model.addAttribute("msg", "课程取消预约成功！");

		} catch (Exception e) {
			LOG.error("取消预约课程出错！", e);
			model.addAttribute("success", false);
			model.addAttribute("msg", "系统出错，请联系管理员！");
		}
		return VIEW_TO_RESULT;
	}

	/**
	 * 预约记录1
	 */
	@RequestMapping("/orderRecord")
	public String orderRecord(HttpServletRequest request, HttpServletResponse response, Model mode) {
		try {
			/**
			 * 获取公众号用户惟一标识
			 */
			String openId = weChatMpService.getWeChatOpenId(request, response);
			/**
			 * 为空，返回
			 */
			if (StringUtils.isEmpty(openId)) {
				return null;
			}

			/**
			 * 设置当前报考人数
			 */
			UserCourseRecord record = new UserCourseRecord();
			record.setUserId(openId);
			List<UserCourseRecord> recordList = userCourseRecordService.select(record);

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
				_record.setCourse(course);
			}

			mode.addAttribute("courseRecordList", recordList);
		} catch (Exception e) {
			LOG.error("查询课程详情出错！", e);
		}
		return VIEW_TO_ORDER_RECORD;
	}

}
