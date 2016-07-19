package com.kyyc.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyyc.common.dao.CourseMapper;
import com.kyyc.common.model.Course;
import com.kyyc.core.service.BaseService;

/**
 * 
 * 课程管理业务类
 * 
 * @author MaQinZh
 *
 */
@Service
public class CourseService extends BaseService<Course> {

	@Resource
	private CourseMapper courseMapper;

}
