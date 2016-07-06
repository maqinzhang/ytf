package com.kyyc.core.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * MyBatis 性能拦截器，用于输出每条 SQL 语句及其执行时间
 */
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }),
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class ExecuteSqlInterceptor implements Interceptor {

	/** 是否启用该拦截器功能，默认是true启用 **/
	private boolean enable = true;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		long start = 0L;
		if (enable) {

			start = System.currentTimeMillis();

			MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
			Object parameterObject = null;
			if (invocation.getArgs().length > 1) {
				parameterObject = invocation.getArgs()[1];
			}

			String statementId = mappedStatement.getId();
			BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
			Configuration configuration = mappedStatement.getConfiguration();
			String sql = getSql(boundSql, parameterObject, configuration);

			System.out.println();
			System.out.println("=============================================================");
			System.out.println("业务开始时间：" + DATE_FORMAT.format(new Date()));
			System.out.println("执行SqlId：" + statementId);
			System.out.println("执行的SQL语句：" + sql);
		}

		Object result = invocation.proceed();

		if (enable) {
			long end = System.currentTimeMillis();
			long timing = end - start;

			System.out.println("执行花费时间：" + timing + " ms");
			System.out.println("=============================================================");
			System.out.println();
		}
		return result;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
	}

	private String getSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		if (parameterMappings != null) {
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else {
						MetaObject metaObject = configuration.newMetaObject(parameterObject);
						value = metaObject.getValue(propertyName);
					}
					sql = replacePlaceholder(sql, value);
				}
			}
		}
		return sql;
	}

	private String replacePlaceholder(String sql, Object propertyValue) {
		String result;
		if (propertyValue != null) {
			if (propertyValue instanceof String) {
				result = "'" + propertyValue + "'";
			} else if (propertyValue instanceof Date) {
				result = "'" + DATE_FORMAT.format(propertyValue) + "'";
			} else {
				result = propertyValue.toString();
			}
		} else {
			result = "null";
		}
		return sql.replaceFirst("\\?", result);
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
