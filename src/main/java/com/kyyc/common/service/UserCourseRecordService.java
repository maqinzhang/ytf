package com.kyyc.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageHelper;
import com.kyyc.common.dao.UserCourseRecordMapper;
import com.kyyc.common.model.UserCourseRecord;
import com.kyyc.common.model.WeChatConstants;
import com.kyyc.core.model.Constants;
import com.kyyc.core.service.BaseService;
import com.kyyc.mp.service.WeChatMpService;

/**
 * 
 * 用户课程报考记录业务类
 * 
 * @author MaQinZh
 *
 */
@Service
public class UserCourseRecordService extends BaseService<UserCourseRecord> {

	@Resource
	private UserCourseRecordMapper userCourseRecordMapper;

	@Resource
	private WeChatMpService weChatMpService;

	/**
	 * 判断基础课程预约数量
	 */
	public int countBaseCourse(String userId, String code) {
		return userCourseRecordMapper.countBaseCourse(userId, code);
	}

	/**
	 * 取消预约记录、将排队的置为预约成功、推送预约成功信息
	 */
	public void deleteRecord(int id) {

		UserCourseRecord userCourseRecord = userCourseRecordMapper.selectByPrimaryKey(id);

		/**
		 * 为空，返回
		 */
		if (ObjectUtils.isEmpty(userCourseRecord)) {
			throw new NullPointerException("当前预约记录不存在，请传入正确的参数值！");
		}

		/**
		 * 取消预约记录
		 */
		userCourseRecordMapper.deleteByPrimaryKey(id);

		/**
		 * 删除非候补的时候，需要将后续候补置为预约成功
		 */
		if ("0".equals(userCourseRecord.getIsStandby())) {
			/**
			 * 获取第一条排队的记录
			 */
			UserCourseRecord record = new UserCourseRecord();
			record.setCourseId(userCourseRecord.getCourseId());
			record.setIsStandby("1");

			PageHelper.startPage(Integer.parseInt(Constants.DEFAULT_PAGE_NO),
					Integer.parseInt(Constants.DEFAULT_PAGE_NO), false);
			record = userCourseRecordMapper.selectOne(record);

			/**
			 * 非空时，更新为预约成功
			 */
			if (!ObjectUtils.isEmpty(record)) {
				record.setIsStandby("0");
				userCourseRecordMapper.updateByPrimaryKey(record);

				/**
				 * 推送消息，通知预约成功
				 */
				weChatMpService.sendMsgToUser(record.getUserId(), WeChatConstants.MP_ORDER_SUCCCESS_MSG);
			}
		}
	}
}
