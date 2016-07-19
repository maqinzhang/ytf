package com.kyyc.common.service;

import java.util.Date;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyyc.common.dao.UserInfoMapper;
import com.kyyc.common.model.UserInfo;
import com.kyyc.common.model.WeChatConstants;
import com.kyyc.core.service.BaseService;

/**
 * 
 * 会员注册管理业务类
 * 
 * @author MaQinZh
 *
 */
@Service
public class UserInfoService extends BaseService<UserInfo> {

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * 从微信端更新关注用户
	 */
	public void transferFromWexin() throws Exception {

		WxMpService wxMpService = new WxMpServiceImpl();

		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(WeChatConstants.MP_APP_ID); // 设置微信公众号的appid
		config.setSecret(WeChatConstants.MP_APP_SECRET); // 设置微信公众号的app
															// corpSecret
		config.setToken(WeChatConstants.MP_TOKEN); // 设置微信公众号的token
		config.setAesKey(WeChatConstants.MP_AES_KEY); // 设置微信公众号的EncodingAESKey
		wxMpService.setWxMpConfigStorage(config);

		WxMpUserList wxMpUserList = wxMpService.userList(null);

		/**
		 * 迭代修改从微信同步过来的关注用户
		 */
		for (String openId : wxMpUserList.getOpenIds()) {
			/**
			 * 获取用户
			 */
			WxMpUser wxMpUser = wxMpService.userInfo(openId, null);

			System.out.println(wxMpUser.toString());

			UserInfo userInfo = userInfoMapper.selectByPrimaryKey(openId);
			if (ObjectUtils.isEmpty(userInfo)) {
				/**
				 * 新增
				 */
				userInfo = new UserInfo();
				userInfo.setUserId(openId);
				userInfo.setNickName(wxMpUser.getRemark());
				userInfo.setSex(String.valueOf(wxMpUser.getSexId()));
				userInfo.setRegTime(new Date(wxMpUser.getSubscribeTime() * 1000));
				userInfo.setStatus(wxMpUser.isSubscribe() ? "1" : "0");
				userInfoMapper.insertSelective(userInfo);
			} else {
				/**
				 * 修改
				 */
				userInfo.setUserId(openId);
				userInfo.setNickName(wxMpUser.getRemark());
				userInfo.setSex(String.valueOf(wxMpUser.getSexId()));
				userInfo.setRegTime(new Date(wxMpUser.getSubscribeTime() * 1000));
				userInfo.setStatus(wxMpUser.isSubscribe() ? "1" : "0");
				userInfoMapper.updateByPrimaryKeySelective(userInfo);
			}
		}
	}

}
