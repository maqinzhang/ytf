package com.kyyc.core.dao;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 持久层基础接口
 * 
 * <pre>
 * </pre>
 *
 * @author MaQinZh 2015年12月2日上午10:05:20
 * @since version 
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, ConditionMapper<T> {

}