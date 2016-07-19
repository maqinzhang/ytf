package com.kyyc.common.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.kyyc.core.model.BaseModel;

/**
 * 用户预定课程记录
 *
 * @author MaQinZh 2016年7月6日下午4:15:11
 */
public class UserMealRecord extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7322506470775865999L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 课程ID
	 */
	private Integer mealId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 课程信息
	 */
	@Transient
	private Meal meal;

	/**
	 * 用户信息
	 */
	@Transient
	private UserInfo userInfo;

	/**
	 * 获取主键
	 *
	 * @return ID - 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id
	 *            主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取用户ID
	 *
	 * @return USER_ID - 用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 *
	 * @param userId
	 *            用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取课程ID
	 *
	 * @return COURSE_ID - 课程ID
	 */
	public Integer getMealId() {
		return mealId;
	}

	/**
	 * 设置课程ID
	 *
	 * @param mealId
	 *            课程ID
	 */
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	/**
	 * 获取创建时间
	 *
	 * @return CREATE_TIME - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the meal
	 */
	public Meal getMeal() {
		return this.meal;
	}

	/**
	 * @param meal
	 *            the meal to set
	 */
	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	/**
	 * @param userInfo
	 *            the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}