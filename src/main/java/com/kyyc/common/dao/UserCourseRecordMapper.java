package com.kyyc.common.dao;

import org.apache.ibatis.annotations.Param;

import com.kyyc.common.model.UserCourseRecord;
import com.kyyc.core.dao.BaseMapper;

/**
 * 用户预约课程记录持久层
 *
 * @author MaQinZh 2016年7月6日下午4:17:08
 */
public interface UserCourseRecordMapper extends BaseMapper<UserCourseRecord> {

	/**
	 * 判断基础课程预约数量
	 */
	public int countBaseCourse(@Param("userId") String userId, @Param("code") String code);

	/**
	 * 判断WOD课程预约数量
	 */
	public int countWODCourse(@Param("userId") String userId, @Param("name") String name);
}