package com.kyyc.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyyc.common.dao.UserMealRecordMapper;
import com.kyyc.common.model.UserMealRecord;
import com.kyyc.core.service.BaseService;

/**
 * 
 * 用户订餐记录业务类
 * 
 * @author MaQinZh
 *
 */
@Service
public class UserMealRecordService extends BaseService<UserMealRecord> {

	@Resource
	private UserMealRecordMapper userMealRecordMapper;

}
