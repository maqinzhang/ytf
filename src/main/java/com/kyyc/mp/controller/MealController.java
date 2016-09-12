package com.kyyc.mp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyyc.common.model.Meal;
import com.kyyc.common.model.UserInfo;
import com.kyyc.common.model.UserMealRecord;
import com.kyyc.common.model.WeChatConstants;
import com.kyyc.common.service.MealService;
import com.kyyc.common.service.UserInfoService;
import com.kyyc.common.service.UserMealRecordService;
import com.kyyc.core.model.Constants;
import com.kyyc.mp.service.WeChatMpService;

/**
 * 公众号餐食管理
 * 
 * @author MaQinZh 2016年7月6日下午4:53:34
 */
@RequestMapping("/meal")
@Controller
public class MealController {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	private static final String VIEW_TO_LIST = "mp/meal/list";
	private static final String VIEW_TO_LIST_CONTENT = "mp/meal/listContent";
	private static final String VIEW_TO_DETAIL = "mp/meal/detail";
	private static final String VIEW_TO_RESULT = "mp/meal/result";
	private static final String VIEW_TO_ORDER_RECORD = "mp/meal/orderRecord";
	private static final String VIEW_TO_ORDER_DETAIL = "mp/meal/orderDetail";
	private static final String VIEW_TO_TIPS = "mp/common/tips";

	@Resource
	private UserInfoService userInfoService;
	@Resource
	private MealService mealService;
	@Resource
	private UserMealRecordService userMealRecordService;
	@Resource
	private WeChatMpService weChatMpService;

	/**
	 * 餐食列表
	 */
	@RequestMapping("/list")
	public String list(String mealDate, HttpServletRequest request, HttpServletResponse response, Model model) {

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
			 * 判断是否会员
			 */
			UserInfo userInfo = userInfoService.selectById(openId);
			if (ObjectUtils.isEmpty(userInfo) || StringUtils.isEmpty(userInfo.getCardNo())) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "很抱歉，YTF会员服务系统仅向会员开放使用！");
				return VIEW_TO_TIPS;
			}

			/**
			 * 时间选择列表
			 */
			List<String> dateList = new ArrayList<String>();

			if (StringUtils.isEmpty(mealDate)) {
				mealDate = DateTime.now().toString(Constants.DATETIME_10);
			}
			dateList.add(mealDate + " " + DateTime.parse(mealDate).dayOfWeek().getAsShortText(Locale.CHINESE));

			/**
			 * 当前查询后一天
			 */
			dateList.add(DateTime.parse(mealDate).plusDays(1).toString(Constants.DATETIME_10) + " "
					+ DateTime.parse(mealDate).plusDays(1).dayOfWeek().getAsShortText(Locale.CHINESE));

			/**
			 * 当前查询后二天
			 */
			dateList.add(DateTime.parse(mealDate).plusDays(2).toString(Constants.DATETIME_10) + " "
					+ DateTime.parse(mealDate).plusDays(2).dayOfWeek().getAsShortText(Locale.CHINESE));

			/**
			 * 查询餐食列表
			 */
			Meal meal = new Meal();
			meal.setMealDate(mealDate);
			List<Meal> mealList = mealService.select(meal);

			/**
			 * 迭代餐食， 设置当前预定份数
			 */
			for (Meal _meal : mealList) {
				Integer cnt = userMealRecordService.countOrderNum(_meal.getId());
				if (ObjectUtils.isEmpty(cnt)) {
					cnt = 0;
				}
				_meal.setOrderNum(cnt);
			}

			model.addAttribute("dateList", dateList);
			model.addAttribute("mealList", mealList);
		} catch (Exception e) {
			LOG.error("查询餐食列表出错！", e);
		}
		return VIEW_TO_LIST;
	}

	/**
	 * 餐食列表内容
	 */
	@RequestMapping("/listContent")
	public String listContent(String mealDate, HttpServletRequest request, HttpServletResponse response, Model model) {

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

			if (StringUtils.isEmpty(mealDate)) {
				mealDate = DateTime.now().toString(Constants.DATETIME_10);
			}

			/**
			 * 查询餐食列表
			 */
			Meal meal = new Meal();
			meal.setMealDate(mealDate);
			List<Meal> mealList = mealService.select(meal);

			/**
			 * 迭代餐食， 设置当前预定份数
			 */
			for (Meal _meal : mealList) {
				Integer cnt = userMealRecordService.countOrderNum(_meal.getId());
				if (ObjectUtils.isEmpty(cnt)) {
					cnt = 0;
				}
				_meal.setOrderNum(cnt);
			}

			model.addAttribute("mealList", mealList);
		} catch (Exception e) {
			LOG.error("查询餐食列表内容出错！", e);
		}
		return VIEW_TO_LIST_CONTENT;
	}

	/**
	 * 餐食详情
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		try {
			Meal meal = mealService.selectById(id);

			/**
			 * 设置当前预定份数
			 */
			Integer cnt = userMealRecordService.countOrderNum(id);
			if (ObjectUtils.isEmpty(cnt)) {
				cnt = 0;
			}
			meal.setOrderNum(cnt);

			model.addAttribute("meal", meal);
		} catch (Exception e) {
			LOG.error("查询餐食详情出错！", e);
		}
		return VIEW_TO_DETAIL;
	}

	/**
	 * 预约餐食
	 */
	@RequestMapping("/order")
	public String order(UserMealRecord userMealRecord, HttpServletRequest request, HttpServletResponse response,
			Model model) {
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
			 * 是否预约过
			 */
			UserMealRecord _record = new UserMealRecord();
			_record.setUserId(openId);
			_record.setMealId(userMealRecord.getMealId());
			int recordCount = userMealRecordService.count(_record);
			
			/**
			 * 判断是否预约过
			 */
			if (recordCount > 0) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "该餐食您已经预约过，请选择其他餐食！");
				return VIEW_TO_RESULT;
			}

			/**
			 * 获取当前餐食
			 */
			Meal meal = mealService.selectById(userMealRecord.getMealId());

			Integer cnt = userMealRecordService.countOrderNum(userMealRecord.getMealId());
			if (ObjectUtils.isEmpty(cnt)) {
				cnt = 0;
			}
			/**
			 * 判断当前预约是否已经满额
			 */
			if (meal.getOrderLimit() <= cnt) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "该餐食已经定完，请选择其他餐食！");
				return VIEW_TO_RESULT;
			}

			/**
			 * 判断当前预约是否已经满额
			 */
			if (meal.getOrderLimit() < cnt + userMealRecord.getOrderNum()) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "您预约的数量超过最大的库存量！");
				return VIEW_TO_RESULT;
			}

			/**
			 * 保存预约记录
			 */
			userMealRecord.setUserId(openId);
			userMealRecord.setCreateTime(DateTime.now().toDate());
			userMealRecordService.save(userMealRecord);

			model.addAttribute("success", true);
			model.addAttribute("msg", "餐食预约成功！");
		} catch (Exception e) {
			LOG.error("预约餐食出错！", e);
			model.addAttribute("success", false);
			model.addAttribute("msg", "系统出错，请联系管理员！");
		}
		return VIEW_TO_RESULT;
	}

	/**
	 * 预约餐食详情
	 */
	@RequestMapping("/orderDetail/{id}")
	public String orderDetail(@PathVariable int id, Model model) {
		try {

			/**
			 * 获取预约记录
			 */
			UserMealRecord record = userMealRecordService.selectById(id);

			/**
			 * 获取餐食信息
			 */
			Meal meal = mealService.selectById(record.getMealId());

			/**
			 * 设置当前预定份数
			 */
			Integer cnt = userMealRecordService.countOrderNum(record.getMealId());
			if (ObjectUtils.isEmpty(cnt)) {
				cnt = 0;
			}
			meal.setOrderNum(cnt);

			/**
			 * 设置餐食
			 */
			record.setMeal(meal);

			model.addAttribute("userMealRecord", record);
		} catch (Exception e) {
			LOG.error("预约餐食详情出错！", e);
		}
		return VIEW_TO_ORDER_DETAIL;
	}

	/**
	 * 取消预约餐食
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
			 * 获取预约记录
			 */
			UserMealRecord record = userMealRecordService.selectById(id);

			/**
			 * 获取餐食信息
			 */
			Meal meal = mealService.selectById(record.getMealId());

			/**
			 * 截止时间判断是否可以进行取消预约操作
			 */
			Date limitDate = DateTime.parse(meal.getMealDate() + " " + WeChatConstants.MEAL_CANCLE_TIME_LIMIT,
					DateTimeFormat.forPattern(Constants.DATETIME_14_COMMON)).toDate();
			if (DateTime.now().toDate().after(limitDate)) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "已经超过餐食取消的截止日期了！");
				return VIEW_TO_RESULT;
			}

			/**
			 * 取消预约记录
			 */
			userMealRecordService.deleteById(id);

			model.addAttribute("success", true);
			model.addAttribute("msg", "餐食取消预约成功！");

		} catch (Exception e) {
			LOG.error("取消预约餐食出错！", e);
			model.addAttribute("success", false);
			model.addAttribute("msg", "系统出错，请联系管理员！");
		}
		return VIEW_TO_RESULT;
	}

	/**
	 * 预约记录1
	 */
	@RequestMapping("/orderRecord")
	public String orderRecord(HttpServletRequest request, HttpServletResponse response, Model model) {
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
			 * 判断是否会员
			 */
			UserInfo userInfo = userInfoService.selectById(openId);
			if (ObjectUtils.isEmpty(userInfo) || StringUtils.isEmpty(userInfo.getCardNo())) {
				model.addAttribute("success", false);
				model.addAttribute("msg", "很抱歉，YTF会员服务系统仅向会员开放使用！");
				return VIEW_TO_TIPS;
			}

			/**
			 * 订餐记录
			 */
			UserMealRecord record = new UserMealRecord();
			record.setUserId(openId);
			List<UserMealRecord> recordList = userMealRecordService.select(record);

			/**
			 * 迭代获取餐食信息
			 */
			for (UserMealRecord _record : recordList) {
				Meal meal = mealService.selectById(_record.getMealId());
				/**
				 * 设置当前预定份数
				 */
				Integer cnt = userMealRecordService.countOrderNum(meal.getId());
				if (ObjectUtils.isEmpty(cnt)) {
					cnt = 0;
				}
				meal.setOrderNum(cnt);
				_record.setMeal(meal);
			}

			model.addAttribute("mealRecordList", recordList);
		} catch (Exception e) {
			LOG.error("查询餐食详情出错！", e);
		}
		return VIEW_TO_ORDER_RECORD;
	}

}
