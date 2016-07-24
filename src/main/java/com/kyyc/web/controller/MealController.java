package com.kyyc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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
import com.kyyc.common.model.Meal;
import com.kyyc.common.model.UserInfo;
import com.kyyc.common.model.UserMealRecord;
import com.kyyc.common.service.MealService;
import com.kyyc.common.service.UserInfoService;
import com.kyyc.common.service.UserMealRecordService;
import com.kyyc.core.model.Constants;

/**
 * WEB端餐饮管理
 * 
 * @author MaQinZh 2016年7月6日下午4:53:34
 */
@RequestMapping("/web/meal")
@Controller("webMealController")
public class MealController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String VIEW_TO_LIST = "web/meal/mealList";
	private static final String VIEW_MEAL_CONTENT = "web/meal/mealPageContent";
	private static final String VIEW_TO_MEAL_INFO = "web/meal/mealInfo";
	private static final String VIEW_TO_MEAL_DETAIL = "web/meal/mealDetail";

	@Resource
	private MealService mealService;
	@Resource
	private UserMealRecordService userMealRecordService;
	@Resource
	private UserInfoService userInfoService;

	/**
	 * 餐食列表
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

				beginDate = DateTime.now().toString(Constants.DATETIME_10);
			}

			/**
			 * 预约结束时间
			 */
			if (StringUtils.isEmpty(endDate)) {
				endDate = DateTime.now().plusDays(7 - DateTime.now().getDayOfWeek()).toString(Constants.DATETIME_10);
			}

			/**
			 * 设置查询条件
			 */
			Condition condition = new Condition(Meal.class);
			condition.createCriteria().andBetween("mealDate", beginDate, endDate);

			/**
			 * 查询餐食列表
			 */
			PageHelper.startPage(pageNo, pageSize);
			PageHelper.orderBy("MEAL_DATE ASC");
			List<Meal> mealList = mealService.selectByCondition(condition);

			/**
			 * 迭代餐食， 设置当前预定人数
			 */
			for (Meal _meal : mealList) {
				UserMealRecord record = new UserMealRecord();
				record.setMealId(_meal.getId());
				int cnt = userMealRecordService.count(record);
				_meal.setOrderNum(cnt);

				/**
				 * 重新设置订餐时间
				 */
				_meal.setMealDate(_meal.getMealDate() + " "
						+ DateTime.parse(_meal.getMealDate()).dayOfWeek().getAsShortText(Locale.CHINESE));
			}

			PageInfo<Meal> page = new PageInfo<Meal>(mealList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("查询餐食列表出错！", e);
		}
		return VIEW_TO_LIST;
	}

	/**
	 * 跳转到餐食列表页面
	 */
	@RequestMapping("/mealPageContent")
	public String mealPageContent(String beginDate, String endDate,
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {

		try {

			/**
			 * 预约开始时间
			 */
			if (StringUtils.isEmpty(beginDate)) {

				beginDate = DateTime.now().toString(Constants.DATETIME_10);
			}

			/**
			 * 预约结束时间
			 */
			if (StringUtils.isEmpty(endDate)) {
				endDate = DateTime.now().plusDays(7 - DateTime.now().getDayOfWeek()).toString(Constants.DATETIME_10);
			}

			/**
			 * 设置查询条件
			 */
			Condition condition = new Condition(Meal.class);
			condition.createCriteria().andBetween("mealDate", beginDate, endDate);

			/**
			 * 查询餐食列表
			 */
			PageHelper.startPage(pageNo, pageSize);
			PageHelper.orderBy("MEAL_DATE ASC");
			List<Meal> mealList = mealService.selectByCondition(condition);

			/**
			 * 迭代餐食， 设置当前预定人数
			 */
			for (Meal _meal : mealList) {
				UserMealRecord record = new UserMealRecord();
				record.setMealId(_meal.getId());
				int cnt = userMealRecordService.count(record);
				_meal.setOrderNum(cnt);

				/**
				 * 重新设置订餐时间
				 */
				_meal.setMealDate(_meal.getMealDate() + " "
						+ DateTime.parse(_meal.getMealDate()).dayOfWeek().getAsShortText(Locale.CHINESE));
			}

			PageInfo<Meal> page = new PageInfo<Meal>(mealList);

			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("跳转到餐食列表页面的时候发生错误：", e);
		}
		return VIEW_MEAL_CONTENT;
	}

	/**
	 * 预约餐食新增、修改
	 */
	@RequestMapping("/info")
	public String info(String id, Model mode) {
		try {
			Meal meal = new Meal();
			if (StringUtils.isNotEmpty(id)) {
				/**
				 * 获取餐食记录
				 */
				meal = mealService.selectById(id);
			}

			mode.addAttribute("meal", meal);
		} catch (Exception e) {
			LOG.error("预约餐食新增、修改出错！", e);
		}
		return VIEW_TO_MEAL_INFO;
	}

	/**
	 * 保存餐食信息
	 */
	@RequestMapping("/saveMeal")
	@ResponseBody
	public Object saveMeal(Meal meal) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			if (ObjectUtils.isEmpty(meal.getId())) {
				meal.setCreateTime(DateTime.now().toDate());
				mealService.save(meal);
			} else {
				mealService.updateNotNull(meal);
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("保存餐食信息的时候发生错误：", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
	}

	/**
	 * 删除基础餐食信息
	 */
	@RequestMapping("/deleteMeal")
	@ResponseBody
	public Object deleteMeal(Integer id) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			if (!ObjectUtils.isEmpty(id)) {

				/**
				 * 删除餐食
				 */
				mealService.deleteById(id);

				/**
				 * 删除预约记录
				 */
				UserMealRecord record = new UserMealRecord();
				record.setMealId(id);
				userMealRecordService.delete(record);
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("删除餐食信息的时候发生错误：", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
	}

	/**
	 * 餐食详情
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Integer id, Model mode) {
		try {
			if (ObjectUtils.isEmpty(id)) {
				throw new IllegalArgumentException("请传入正确的餐食ID！");
			}

			/**
			 * 获取预约记录
			 */
			Meal meal = mealService.selectById(id);

			/**
			 * 重新设置订餐时间
			 */
			meal.setMealDate(meal.getMealDate() + " " + DateTime.parse(meal.getMealDate()).dayOfWeek().getAsShortText(Locale.CHINESE));

			/**
			 * 设置当前预定人数
			 */
			UserMealRecord _record = new UserMealRecord();
			_record.setMealId(id);
			List<UserMealRecord> userMealRecordList = userMealRecordService.select(_record);
			meal.setOrderNum(userMealRecordList.size());

			/**
			 * 设置预约记录的客户昵称
			 */
			for (UserMealRecord record : userMealRecordList) {
				UserInfo userInfo = userInfoService.selectById(record.getUserId());
				record.setUserInfo(userInfo);
			}

			mode.addAttribute("meal", meal);
			mode.addAttribute("userMealRecordList", userMealRecordList);
		} catch (Exception e) {
			LOG.error("餐食详情出错！", e);
		}
		return VIEW_TO_MEAL_DETAIL;
	}
}
