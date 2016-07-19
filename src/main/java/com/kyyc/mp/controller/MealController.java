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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyyc.common.model.Meal;
import com.kyyc.common.model.UserMealRecord;
import com.kyyc.common.service.MealService;
import com.kyyc.common.service.UserMealRecordService;
import com.kyyc.core.model.Constants;
import com.kyyc.mp.service.WeChatMpService;

/**
 * 订餐管理
 * 
 * @author MaQinZh 2016年7月6日下午4:53:34
 */
@RequestMapping("/meal")
@Controller
public class MealController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String VIEW_TO_LIST = "mp/meal/list";
	private static final String VIEW_TO_DETAIL = "mp/meal/detail";
	private static final String VIEW_TO_RESULT = "mp/meal/result";
	private static final String VIEW_TO_ORDER_RECORD = "mp/meal/orderRecord";
	private static final String VIEW_TO_ORDER_DETAIL = "mp/meal/orderDetail";

	@Resource
	private MealService mealService;
	@Resource
	private UserMealRecordService userMealRecordService;
	@Resource
	private WeChatMpService weChatMpService;

	/**
	 * 课程列表
	 */
	@RequestMapping("/list")
	public String list(String mealDate, HttpServletRequest request, HttpServletResponse response, Model mode) {

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

			if (StringUtils.isEmpty(mealDate)) {
				mealDate = DateTime.now().toString(Constants.DATETIME_10);
			}
			dateList.add(mealDate + " " + DateTime.parse(mealDate).dayOfWeek().getAsShortText());

			/**
			 * 当前查询后一天
			 */
			dateList.add(DateTime.parse(mealDate).plusDays(1).toString(Constants.DATETIME_10) + " "
					+ DateTime.parse(mealDate).plusDays(1).dayOfWeek().getAsShortText());

			/**
			 * 当前查询后二天
			 */
			dateList.add(DateTime.parse(mealDate).plusDays(2).toString(Constants.DATETIME_10) + " "
					+ DateTime.parse(mealDate).plusDays(2).dayOfWeek().getAsShortText());

			/**
			 * 查询课程列表
			 */
			Meal meal = new Meal();
			meal.setMealDate(mealDate);
			List<Meal> mealList = mealService.select(meal);

			/**
			 * 迭代课程， 设置当前报考人数
			 */
			for (Meal _meal : mealList) {
				UserMealRecord record = new UserMealRecord();
				record.setMealId(_meal.getId());
				int cnt = userMealRecordService.count(record);
				_meal.setPersonNum(cnt);
			}

			mode.addAttribute("dateList", dateList);
			mode.addAttribute("mealList", mealList);
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
			Meal meal = mealService.selectById(id);

			/**
			 * 设置当前报考人数
			 */
			UserMealRecord record = new UserMealRecord();
			record.setMealId(id);
			int cnt = userMealRecordService.count(record);
			meal.setPersonNum(cnt);

			mode.addAttribute("meal", meal);
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
			Meal meal = mealService.selectById(id);

			UserMealRecord record = new UserMealRecord();
			record.setMealId(id);
			int count = userMealRecordService.count(record);
			/**
			 * 判断当前预约是否已经满额
			 */
			if (meal.getPersonLimit() <= count) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "该课程已经达到预约人数，请选择其他课程！");
				return VIEW_TO_RESULT;
			}

			record.setUserId(openId);
			int recordCount = userMealRecordService.count(record);
			/**
			 * 判断是否预约过；规则：基础课一人只能预约一次
			 */
			if ("1".equals(meal.getIsBase())) {
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
			userMealRecordService.save(record);

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
			UserMealRecord record = userMealRecordService.selectById(id);

			/**
			 * 获取课程信息
			 */
			Meal meal = mealService.selectById(record.getMealId());

			/**
			 * 设置当前报考人数
			 */
			UserMealRecord _record = new UserMealRecord();
			_record.setMealId(record.getMealId());
			int cnt = userMealRecordService.count(_record);
			meal.setPersonNum(cnt);

			/**
			 * 设置课程
			 */
			record.setMeal(meal);

			mode.addAttribute("userMealRecord", record);
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

			/**
			 * 取消预约记录
			 */
			userMealRecordService.deleteById(id);

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
			UserMealRecord record = new UserMealRecord();
			record.setUserId(openId);
			List<UserMealRecord> recordList = userMealRecordService.select(record);

			/**
			 * 迭代获取课程信息
			 */
			for (UserMealRecord _record : recordList) {
				Meal meal = mealService.selectById(_record.getMealId());
				/**
				 * 设置当前报考人数
				 */
				UserMealRecord rcd = new UserMealRecord();
				rcd.setMealId(meal.getId());
				int cnt = userMealRecordService.count(rcd);
				meal.setPersonNum(cnt);
				_record.setMeal(meal);
			}

			mode.addAttribute("mealRecordList", recordList);
		} catch (Exception e) {
			LOG.error("查询课程详情出错！", e);
		}
		return VIEW_TO_ORDER_RECORD;
	}

}
