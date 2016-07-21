package com.kyyc.common.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.OrderBy;

import com.kyyc.core.model.BaseModel;

/**
 * 用户信息
 *
 * @author MaQinZh 2016年7月6日下午4:11:43
 */
public class UserInfo extends BaseModel {

	private static final long serialVersionUID = -5072443903632603108L;

	/**
	 * 用户ID
	 */
	@Id
	private String userId;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 性别 （1：男，2：女，0：未知）
	 */
	private String sex;

	/**
	 * 证件号
	 */
	private String cardNo;

	/**
	 * 出生日期
	 */
	private String birthday;

	/**
	 * 微信号
	 */
	private String weixin;

	/**
	 * QQ号
	 */
	private String qq;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 注册时间
	 */
	@OrderBy("DESC")
	private Date regTime;

	/**
	 * 用户状态（1：有效，2：失效）
	 */
	private String status;

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
	 * 获取姓名
	 *
	 * @return USER_NAME - 姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置姓名
	 *
	 * @param userName
	 *            姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return PASSWORD
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取昵称
	 *
	 * @return NICK_NAME - 昵称
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 设置昵称
	 *
	 * @param nickName
	 *            昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 获取性别 （1：男，2：女，0：未知）
	 *
	 * @return SEX - 性别 （1：男，2：女，0：未知）
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别 （1：男，2：女，0：未知）
	 *
	 * @param sex
	 *            性别 （1：男，2：女，0：未知）
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取证件号
	 *
	 * @return CARD_NO - 证件号
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 设置证件号
	 *
	 * @param cardNo
	 *            证件号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 获取出生日期
	 *
	 * @return BIRTHDAY - 出生日期
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 设置出生日期
	 *
	 * @param birthday
	 *            出生日期
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取微信号
	 *
	 * @return WEIXIN - 微信号
	 */
	public String getWeixin() {
		return weixin;
	}

	/**
	 * 设置微信号
	 *
	 * @param weixin
	 *            微信号
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	/**
	 * 获取QQ号
	 *
	 * @return QQ - QQ号
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * 设置QQ号
	 *
	 * @param qq
	 *            QQ号
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 获取邮箱
	 *
	 * @return EMAIL - 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 *
	 * @param email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取手机
	 *
	 * @return MOBILE - 手机
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 *
	 * @param mobile
	 *            手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取住址
	 *
	 * @return ADDRESS - 住址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置住址
	 *
	 * @param address
	 *            住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取注册时间
	 *
	 * @return REG_TIME - 注册时间
	 */
	public Date getRegTime() {
		return regTime;
	}

	/**
	 * 设置注册时间
	 *
	 * @param regTime
	 *            注册时间
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	/**
	 * 获取用户状态（1：有效，2：失效）
	 *
	 * @return STATUS - 用户状态（1：有效，2：失效）
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置用户状态（1：有效，2：失效）
	 *
	 * @param status
	 *            用户状态（1：有效，2：失效）
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}