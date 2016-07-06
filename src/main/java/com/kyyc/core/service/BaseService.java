package com.kyyc.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyyc.core.dao.BaseMapper;
import com.kyyc.core.model.BaseModel;

/**
 * <p>
 * 抽象service层基类 提供一些简便方法
 * <p/>
 * <p>
 * 泛型 ： M 表示实体类型
 * <p/>
 */
@Service
public abstract class BaseService<M extends BaseModel> {

	@Autowired
	protected BaseMapper<M> baseMapper;

	/**
	 * 保存一个实体，null的属性也会保存，不会使用数据库默认值
	 * 
	 * @param m
	 * @return 返回保存的数量
	 */
	public int save(M m) {
		return baseMapper.insert(m);
	}

	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 *
	 * @param m
	 *            实体
	 * @return 返回保存的数量
	 */
	public int saveNotNull(M m) {
		return baseMapper.insertSelective(m);
	}

	/**
	 * 根据主键更新实体全部字段，null值会被更新
	 *
	 * @param m
	 *            实体
	 * @return 返回更新的数量
	 */
	public int update(M m) {
		return baseMapper.updateByPrimaryKey(m);
	}

	/**
	 * 根据主键更新属性不为null的值
	 *
	 * @param m
	 *            实体
	 * @return 返回更新的数量
	 */
	public int updateNotNull(M m) {
		return baseMapper.updateByPrimaryKeySelective(m);
	}

	/**
	 * 根据Condition条件更新实体record包含的全部属性，null值会被更新
	 * 
	 * @param m
	 * @param condition
	 * @return
	 */
	public int update(M m, Object condition) {
		return baseMapper.updateByCondition(m, condition);
	}

	/**
	 * 根据Condition条件更新实体m包含的不是null的属性值
	 * 
	 * @param m
	 * @param condition
	 * @return
	 */
	public int updateNotNull(M m, Object condition) {
		return baseMapper.updateByConditionSelective(m, condition);
	}

	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 *
	 * @param id
	 *            主键
	 * @return 返回删除的数量
	 */
	public int deleteById(Object id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号(慎用！)
	 *
	 * @param id
	 *            主键
	 * @return 返回删除的数量
	 */
	public int delete(M m) {
		return baseMapper.delete(m);
	}

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号(慎用！)
	 *
	 * @param id
	 *            主键
	 * @return 返回删除的数量
	 */
	public int deleteByCondition(Object condition) {
		return baseMapper.deleteByCondition(condition);
	}

	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	public M selectById(Object id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 *
	 * @param m
	 * @return 返回id对应的实体
	 */
	public M selectOne(M m) {
		return baseMapper.selectOne(m);
	}

	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * 
	 * @param m
	 * @return 返回实体集合
	 */
	public List<M> select(M m) {
		return baseMapper.select(m);
	}

	/**
	 * 根据Condition条件进行查询
	 * 
	 * @param condition
	 * @return 返回实体集合
	 */
	public List<M> selectByCondition(Object condition) {
		return baseMapper.selectByCondition(condition);
	}

	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 *
	 * @return 实体总数
	 */
	public int count(M m) {
		return baseMapper.selectCount(m);
	}

	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 *
	 * @return 实体总数
	 */
	public int countByCondition(Object condition) {
		return baseMapper.selectCountByCondition(condition);
	}
}
