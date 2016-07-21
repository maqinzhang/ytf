package com.kyyc.common.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.kyyc.core.model.BaseModel;

/**
 * 用户预定餐食记录
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
	 * 餐食ID
	 */
	private Integer mealId;

	/**
	 * 预定数量
	 */
	private Integer orderNum;

	/**
	 * 是否需要配送（0：否，1：是）
	 */
	private String isDelivery;

	/**
	 * 配送地址
	 */
	private String deliveryAddress;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 餐食信息
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
	 * 获取餐食ID
	 *
	 * @return MEAL_ID - 餐食ID
	 */
	public Integer getMealId() {
		return mealId;
	}

	/**
	 * 设置餐食ID
	 *
	 * @param mealId
	 *            餐食ID
	 */
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	/**
	 * 获取预定数量
	 * 
	 * @return ORDER_NUM 预定数量
	 */
	public Integer getOrderNum() {
		return this.orderNum;
	}

	/**
	 * 设置预定数量
	 * 
	 * @param orderNum
	 *            预定数量
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取是否需要配送（0：否，1：是）
	 *
	 * @return IS_STANDBY - 是否需要配送（0：否，1：是）
	 */
	public String getIsDelivery() {
		return isDelivery;
	}

	/**
	 * 设置是否需要配送（0：否，1：是）
	 *
	 * @param isDelivery
	 *            是否需要配送（0：否，1：是）
	 */
	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}

	/**
	 * 获取配送地址
	 *
	 * @return DELIVERY_ADDRESS - 配送地址
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * 设置配送地址
	 *
	 * @param deliveryAddress
	 *            配送地址
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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