package com.kyyc.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyyc.common.dao.BaseCourseMapper;
import com.kyyc.common.model.BaseCourse;
import com.kyyc.core.service.BaseService;

/**
 * 
 * 课程管理业务类
 * 
 * @author MaQinZh
 *
 */
@Service
public class BaseCourseService extends BaseService<BaseCourse> {

	@Resource
	private BaseCourseMapper baseCourseMapper;

}
