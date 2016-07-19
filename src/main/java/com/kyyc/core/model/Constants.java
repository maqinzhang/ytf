package com.kyyc.core.model;

import java.util.ResourceBundle;

/**
 * 系统常量
 *
 * @author MaQinZh 2015年12月4日上午11:03:03
 * @since version 1.0.0
 */
public class Constants {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("constants");

	/**
	 * 文件服务器地址
	 */
	public static final String FILE_SERVER_URL = BUNDLE.getString("fileserver.url");

	/**
	 * 12位时间格式 yyMMDDHHmmss 应用端时间戳使用
	 */
	public static final String DATETIME_12_A = "yyMMddHHmmss";

	/**
	 * 15位时间格式 yyMMddHHmmssSSS 应用端时间戳使用
	 */
	public static final String DATETIME_15_A = "yyMMddHHmmssSSS";

	/**
	 * 17位时间格式 yyyyMMddHHmmssSSS
	 */
	public static final String DATETIME_17 = "yyyyMMddHHmmssSSS";

	/**
	 * 14位时间格式 yyyyMMddHHmmss
	 */
	public static final String DATETIME_14 = "yyyyMMddHHmmss";

	/**
	 * 14位时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATETIME_14_COMMON = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 10位时间格式 日期部分 yyyy-MM-dd
	 */
	public static final String DATETIME_10 = "yyyy-MM-dd";

	/**
	 * 10位时间格式 日期部分 yyyy/MM/dd
	 */
	public static final String DATETIME_10_SHORT = "yyyy/MM/dd";

	/**
	 * 8位时间格式 日期部分 yyyyMMdd
	 */
	public static final String DATETIME_8 = "yyyyMMdd";

	/**
	 * 6位时间格式 日期部分 yyyymm
	 */
	public static final String DATE_6 = "yyyyMM";

	/**
	 * 6位日期格式 yyyy年MM月
	 */
	public static final String DATE_6_CN = "yyyy年MM月";

	/**
	 * 6位短日期格式 yymmdd
	 */
	public static final String DATE_SHORT_6 = "yyMMdd";

	/**
	 * 8位日期格式 yyyy年MM月dd日
	 */
	public static final String DATETIME_8_CN = "yyyy年MM月dd日";

	/**
	 * 6位时间格式 时间部分 HHmmss
	 */
	public static final String DATETIME_6 = "HHmmss";

	/**
	 * 5位时间格式 时间部分 HH:mm
	 */
	public static final String DATETIME_5 = "HH:mm";

	/**
	 * 默认分页页码
	 */
	public static final String DEFAULT_PAGE_NO = "1";

	/**
	 * 默认分页页大小
	 */
	public static final String DEFAULT_PAGE_SIZE = "10";

}
