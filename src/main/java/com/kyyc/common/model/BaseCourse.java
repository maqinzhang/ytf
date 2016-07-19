package com.kyyc.common.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kyyc.core.model.BaseModel;

/**
 * 基础课程信息表
 * 
 * @author MaQinZh 2016年7月18日上午11:27:13
 * @since version
 */
public class BaseCourse extends BaseModel {

	private static final long serialVersionUID = 1851021294721906905L;

	/**
	 * 课程代号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 课程名称
	 */
	private String name;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 获取课程代号
	 *
	 * @return ID - 课程代号
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置课程代号
	 *
	 * @param id
	 *            课程代号
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
}