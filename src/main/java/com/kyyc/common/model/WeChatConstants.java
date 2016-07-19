package com.kyyc.common.model;

import java.util.ResourceBundle;

/**
 * 系统常量
 *
 * @author MaQinZh 2015年12月4日上午11:03:03
 * @since version 1.0.0
 */
public class WeChatConstants {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("constants");

    /**
     * 微信oauth2接口获取用户临时参数-code
     */
    public static final String WECHAT_PARAM_CODE = "code";

	/**
	 * 微信公众号缓存用户对象KEY
	 */
	public static final String WECHAT_MP_COOKIE_NAME = "wechat_mp_name";

	/**
	 * 微信企业号缓存用户对象KEY
	 */
	public static final String WECHAT_QY_COOKIE_NAME = "wechat_qy_name";
	

	/**
	 * 公众号对应的账户ID
	 */
	public static final String MP_APP_ID = BUNDLE.getString("mp_app_id");
	public static final String MP_APP_SECRET = BUNDLE.getString("mp_app_secret");
	
	/**
	 * 公众号对应的全局的TOKEN和AES_KEY，其他应用默认也是用该值
	 */
	public static final String MP_TOKEN = BUNDLE.getString("mp_token");
	public static final String MP_AES_KEY = BUNDLE.getString("mp_aes_key");
	
	/**
	 * 公众号推送通知，预约成功！
	 */
	public static final String MP_ORDER_SUCCCESS_MSG = BUNDLE.getString("mp_order_succcess_msg");
	

	/**
	 * 企业号对应的账户ID
	 */
	public static final String CP_CORP_ID = BUNDLE.getString("cp_corp_id");
	public static final String CP_CORP_SECRET = BUNDLE.getString("cp_corp_secret");
	
	/**
	 * 企业号对应的全局的TOKEN和AES_KEY，其他应用默认也是用该值
	 */
	public static final String CP_TOKEN = BUNDLE.getString("cp_token");
	public static final String CP_AES_KEY = BUNDLE.getString("cp_aes_key");
	
	/**
	 * 企业号课程管理应用
	 */
	public static final String CP_COURSE_AGENT_ID = "1";
	
}
