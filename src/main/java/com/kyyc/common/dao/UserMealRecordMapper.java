package com.kyyc.common.dao;

import com.kyyc.common.model.UserMealRecord;
import com.kyyc.core.dao.BaseMapper;

/**
 * 用户订餐记录持久层
 *
 * @author MaQinZh 2016年7月6日下午4:17:08
 */
public interface UserMealRecordMapper extends BaseMapper<UserMealRecord> {

	/**
	 * 餐饮订购总和
	 * 
	 * 
	 * @param mealId
	 *            餐饮ID
	 * @return
	 */
	public Integer countOrderNum(int mealId);
}