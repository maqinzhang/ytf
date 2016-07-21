package com.kyyc.common.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.kyyc.core.model.BaseModel;

/**
 * 餐饮信息
 *
 * @author MaQinZh 2016年7月6日下午4:10:40
 */
public class Meal extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 餐饮名称
	 */
	private String name;

	/**
	 * 餐食类目
	 */
	private String type;

	/**
	 * 图片地址
	 */
	private String picUrl;

	/**
	 * 餐饮日期（格式：yyyy-MM-dd）
	 */
	private String mealDate;

	/**
	 * 订餐数量限制
	 */
	private Integer orderLimit;

	/**
	 * 当前预约人数
	 */
	@Transient
	private Integer orderNum;

	/**
	 * 录入时间
	 */
	private Date createTime;

	/**
	 * 餐饮内容
	 */
	private String content;

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
	 * 获取餐饮名称
	 *
	 * @return NAME - 餐饮名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置餐饮名称
	 *
	 * @param name
	 *            餐饮名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取餐食类目
	 *
	 * @return TYPE -餐食类目
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置餐食类目
	 *
	 * @param type
	 *            餐食类目
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取图片地址
	 *
	 * @return PIC_URL - 图片地址
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 设置图片地址
	 *
	 * @param picUrl
	 *            图片地址
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 获取餐饮日期（格式：yyyy-MM-dd）
	 *
	 * @return COURSE_DATE - 餐饮日期（格式：yyyy-MM-dd）
	 */
	public String getMealDate() {
		return mealDate;
	}

	/**
	 * 设置餐饮日期（格式：yyyy-MM-dd）
	 *
	 * @param mealDate
	 *            餐饮日期（格式：yyyy-MM-dd）
	 */
	public void setMealDate(String mealDate) {
		this.mealDate = mealDate;
	}

	/**
	 * 获取订餐数量限制
	 *
	 * @return PERSON_LIMIT - 订餐数量限制
	 */
	public Integer getOrderLimit() {
		return orderLimit;
	}

	/**
	 * 设置订餐数量限制
	 *
	 * @param orderLimit
	 *            订餐数量限制
	 */
	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	/**
	 * 获取订餐数量
	 *
	 * @return PERSON_NUM - 订餐数量
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * 设置订餐数量
	 *
	 * @param orderNum
	 *            订餐数量
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取录入时间
	 *
	 * @return CREATE_TIME - 录入时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置录入时间
	 *
	 * @param createTime
	 *            录入时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取餐饮内容
	 *
	 * @return CONTENT - 餐饮内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置餐饮内容
	 *
	 * @param content
	 *            餐饮内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
}