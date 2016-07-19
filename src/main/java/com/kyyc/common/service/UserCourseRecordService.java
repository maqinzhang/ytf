package com.kyyc.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyyc.common.dao.UserCourseRecordMapper;
import com.kyyc.common.model.UserCourseRecord;
import com.kyyc.core.service.BaseService;

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
}
