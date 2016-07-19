package com.kyyc.mp.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.util.StringUtil;

import com.kyyc.common.model.WeChatConstants;
import com.kyyc.core.utils.web.CookieUtil;

@Service
public class WeChatMpService {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	/**
	 * 获取微信帐号对应的用户ID
	 */
	public String getWeChatOpenId(HttpServletRequest req, HttpServletResponse resp) {

		String cookieVal = CookieUtil.getCookieValue(req, WeChatConstants.WECHAT_MP_COOKIE_NAME);
		String code = req.getParameter(WeChatConstants.WECHAT_PARAM_CODE);

		WxMpService wxMpService = new WxMpServiceImpl();

		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(WeChatConstants.MP_APP_ID); // 设置微信公众号的appid
		config.setSecret(WeChatConstants.MP_APP_SECRET); // 设置微信公众号的app
															// corpSecret
		config.setToken(WeChatConstants.MP_TOKEN); // 设置微信公众号的token
		config.setAesKey(WeChatConstants.MP_AES_KEY); // 设置微信公众号的EncodingAESKey
		wxMpService.setWxMpConfigStorage(config);

		// 重定向后 获取code
		if (StringUtil.isNotEmpty(code)) {
			try {
				WxMpOAuth2AccessToken oauth2 = wxMpService.oauth2getAccessToken(code);
				String openId = oauth2.getOpenId();
				CookieUtil.setCookie(resp, WeChatConstants.WECHAT_MP_COOKIE_NAME, openId);
				cookieVal = openId;
			} catch (WxErrorException e) {
				LOG.error("公众号获取用户微信端缓存信息出错！", e);
				return null;
			}
		}

		// 若取不到微信用户cookie ，则OAUTH2
		if (StringUtil.isEmpty(cookieVal)) {
			String url = req.getRequestURL().toString();
			url = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, null);
			try {
				resp.sendRedirect(url);
			} catch (IOException e) {
				LOG.error("公众号调用oauth2接口出错！", e);
				return null;
			}
			return null;
		}
		return cookieVal;
	}

	/**
	 * 公众号向关注用户推送消息
	 */
	public void sendMsgToUser(String openId, String content) {

		WxMpService wxMpService = new WxMpServiceImpl();

		WxMpCustomMessage msg = new WxMpCustomMessage();
		msg.setToUser(openId);
		msg.setMsgType("text");
		msg.setContent(content);

		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(WeChatConstants.MP_APP_ID); // 设置微信公众号的appid
		config.setSecret(WeChatConstants.MP_APP_SECRET); // 设置微信公众号的app
															// corpSecret
		config.setToken(WeChatConstants.MP_TOKEN); // 设置微信公众号的token
		config.setAesKey(WeChatConstants.MP_AES_KEY); // 设置微信公众号的EncodingAESKey
		wxMpService.setWxMpConfigStorage(config);

		try {
			wxMpService.customMessageSend(msg);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
}
