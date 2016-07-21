package com.kyyc.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyyc.common.dao.MealMapper;
import com.kyyc.common.model.Meal;
import com.kyyc.core.service.BaseService;

/**
 * 
 * 餐食管理业务类
 * 
 * @author MaQinZh
 *
 */
@Service
public class MealService extends BaseService<Meal> {

	@Resource
	private MealMapper mealMapper;

}
