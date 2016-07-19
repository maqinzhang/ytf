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
	 * 课程名称
	 */
	private String name;

	/**
	 * 是否基础课（0：否，1：是）
	 */
	private String isBase;

	/**
	 * 开课日期（格式：yyyy-MM-dd）
	 */
	private String mealDate;

	/**
	 * 开始时间（如12:00）
	 */
	private String beginTime;

	/**
	 * 结束时间（如13:00）
	 */
	private String endTime;

	/**
	 * 教练
	 */
	private String coach;

	/**
	 * 上课人数限制
	 */
	private Integer personLimit;

	/**
	 * 上课人数限制
	 */
	private String isStandby;

	/**
	 * 当前预约人数
	 */
	@Transient
	private Integer personNum;

	/**
	 * 录入时间
	 */
	private Date createTime;

	/**
	 * 课程内容
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
	 * 获取课程名称
	 *
	 * @return NAME - 课程名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置课程名称
	 *
	 * @param name
	 *            课程名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取是否基础课（0：否，1：是）
	 *
	 * @return IS_BASE - 是否基础课（0：否，1：是）
	 */
	public String getIsBase() {
		return isBase;
	}

	/**
	 * 设置是否基础课（0：否，1：是）
	 *
	 * @param isBase
	 *            是否基础课（0：否，1：是）
	 */
	public void setIsBase(String isBase) {
		this.isBase = isBase;
	}

	/**
	 * 获取开课日期（格式：yyyy-MM-dd）
	 *
	 * @return COURSE_DATE - 开课日期（格式：yyyy-MM-dd）
	 */
	public String getMealDate() {
		return mealDate;
	}

	/**
	 * 设置开课日期（格式：yyyy-MM-dd）
	 *
	 * @param mealDate
	 *            开课日期（格式：yyyy-MM-dd）
	 */
	public void setMealDate(String mealDate) {
		this.mealDate = mealDate;
	}

	/**
	 * 获取开始时间（如12:00）
	 *
	 * @return BEGIN_TIME - 开始时间（如12:00）
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置开始时间（如12:00）
	 *
	 * @param beginTime
	 *            开始时间（如12:00）
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取结束时间（如13:00）
	 *
	 * @return END_TIME - 结束时间（如13:00）
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间（如13:00）
	 *
	 * @param endTime
	 *            结束时间（如13:00）
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取教练
	 *
	 * @return COACH - 教练
	 */
	public String getCoach() {
		return coach;
	}

	/**
	 * 设置教练
	 *
	 * @param coach
	 *            教练
	 */
	public void setCoach(String coach) {
		this.coach = coach;
	}

	/**
	 * 获取上课人数限制
	 *
	 * @return PERSON_LIMIT - 上课人数限制
	 */
	public Integer getPersonLimit() {
		return personLimit;
	}

	/**
	 * 设置上课人数限制
	 *
	 * @param personLimit
	 *            上课人数限制
	 */
	public void setPersonLimit(Integer personLimit) {
		this.personLimit = personLimit;
	}

	/**
	 * 获取 是否候补（0：否，1：是）
	 *
	 * @return IS_STANDBY - 是否候补（0：否，1：是）
	 */
	public String getIsStandby() {
		return isStandby;
	}

	/**
	 * 设置是否候补（0：否，1：是）
	 *
	 * @param isStandby
	 *            是否候补（0：否，1：是）
	 */
	public void setIsStandby(String isStandby) {
		this.isStandby = isStandby;
	}

	/**
	 * 获取上课人数
	 *
	 * @return PERSON_NUM - 上课人数
	 */
	public Integer getPersonNum() {
		return personNum;
	}

	/**
	 * 设置上课人数
	 *
	 * @param personNum
	 *            上课人数
	 */
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
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
	 * 获取课程内容
	 *
	 * @return CONTENT - 课程内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置课程内容
	 *
	 * @param content
	 *            课程内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
}