package cn.com.solex.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 提供Jdbc Dao层
 * 
 * @author Administrator
 * 
 */
@Repository
public class JdbcDao {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected SimpleJdbcTemplate simpleJdbcTemplate;
	protected SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
	}

	/**
	 * 根据sql语句，返回对象集合
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select * from tb where id=:id)
	 * @param clazz类型
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象集合
	 */
	public <T> List<T> find(final String sql, Class<T> clazz,
			Map<String, Object> parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			Assert.notNull(clazz, "集合中对象类型不能为空!");
			if (parameters != null) {
				return simpleJdbcTemplate.query(sql, resultBeanMapper(clazz),
						parameters);
			} else {
				return simpleJdbcTemplate.query(sql, resultBeanMapper(clazz));
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回对象集合
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select * from tb where id=:id)
	 * @param clazz类型
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象集合
	 */
	public <T> List<T> find(final String sql, Class<T> clazz, Object... objects) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			Assert.notNull(clazz, "集合中对象类型不能为空!");
			return simpleJdbcTemplate.query(sql, resultBeanMapper(clazz),
					objects);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回对象
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select * from tb where id=:id)
	 * @param clazz类型
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public <T> T findForObject(final String sql, Class<T> clazz,
			Map<String, Object> parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			Assert.notNull(clazz, "集合中对象类型不能为空!");
			if (parameters != null) {
				return simpleJdbcTemplate.queryForObject(sql,
						resultBeanMapper(clazz), parameters);
			} else {
				return simpleJdbcTemplate.queryForObject(sql,
						resultBeanMapper(clazz));
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回对象
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select * from tb where id=:id)
	 * @param clazz类型
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public <T> T findForObject(final String sql, Class<T> clazz,
			Object... parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			Assert.notNull(clazz, "集合中对象类型不能为空!");
			return simpleJdbcTemplate.queryForObject(sql,
					resultBeanMapper(clazz), parameters);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回数值型返回结果
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public int queryForInt(String sql, Map<String, ?> args) {
		return simpleJdbcTemplate.queryForInt(sql, args);
	}

	/**
	 * 根据sql语句，返回数值型返回结果
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public int queryForInt(String sql, Object... args) {
		return simpleJdbcTemplate.queryForInt(sql, args);
	}

	/**
	 * 根据sql语句，返回数值型返回结果
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public long findForLong(final String sql, Map<String, Object> parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			if (parameters != null) {
				return simpleJdbcTemplate.queryForLong(sql, parameters);
			} else {
				return simpleJdbcTemplate.queryForLong(sql);
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 根据sql语句，返回数值型返回结果
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public long findForLong(final String sql, Object... parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			return simpleJdbcTemplate.queryForLong(sql, parameters);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 根据sql语句，返回Map对象,对于某些项目来说，没有准备Bean对象，则可以使用Map代替Key为字段名,value为值
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public Map<String, Object> findForMap(final String sql,
			Map<String, Object> parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			if (parameters != null) {
				return simpleJdbcTemplate.queryForMap(sql, parameters);
			} else {
				return simpleJdbcTemplate.queryForMap(sql);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回Map对象,对于某些项目来说，没有准备Bean对象，则可以使用Map代替Key为字段名,value为值
	 * 
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public Map<String, Object> findForMap(final String sql,
			Object... parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			return simpleJdbcTemplate.queryForMap(sql, parameters);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回Map对象集合
	 * 
	 * @see findForMap
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public List<Map<String, Object>> findForListMap(final String sql,
			Map<String, Object> parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			if (parameters != null) {
				return simpleJdbcTemplate.queryForList(sql, parameters);
			} else {
				return simpleJdbcTemplate.queryForList(sql);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据sql语句，返回Map对象集合
	 * 
	 * @see findForMap
	 * @param sql语句
	 *            (参数用冒号加参数名，例如select count(*) from tb where id=:id)
	 * @param parameters参数集合
	 *            (key为参数名，value为参数值)
	 * @return bean对象
	 */
	public List<Map<String, Object>> findForListMap(final String sql,
			Object... parameters) {
		try {
			Assert.hasText(sql, "sql语句不正确!");
			return simpleJdbcTemplate.queryForList(sql, parameters);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 执行insert，update，delete等操作<br>
	 * 例如insert into users (name,login_name,password)
	 * values(:name,:loginName,:password)<br>
	 * 参数用冒号,参数为bean的属性名
	 * 
	 * @param sql
	 * @param bean
	 */
	public int executeForObject(final String sql, Object bean) {
		Assert.hasText(sql, "sql语句不正确!");
		return simpleJdbcTemplate.update(sql, paramBeanMapper(bean));
	}

	/**
	 * 执行insert，update，delete等操作<br>
	 * 例如insert into users (name,login_name,password)
	 * values(:name,:loginName,:password)<br>
	 * 参数用冒号,参数为bean的属性名
	 * 
	 * @param sql
	 * @param bean
	 */
	public int executeForObject(final String sql, Map<String, Object> args) {
		Assert.hasText(sql, "sql语句不正确!");
		return simpleJdbcTemplate.update(sql, args);
	}

	/**
	 * 执行insert，update，delete等操作<br>
	 * 例如insert into users (name,login_name,password)
	 * values(:name,:loginName,:password)<br>
	 * 参数用冒号,参数为bean的属性名
	 * 
	 * @param sql
	 * @param bean
	 */
	public int executeForObject(final String sql, Object... args) {
		Assert.hasText(sql, "sql语句不正确!");
		return simpleJdbcTemplate.update(sql, args);
	}

	/**
	 * 批量处理操作 例如：update t_actor set first_name = :firstName, last_name =
	 * :lastName where id = :id 参数用冒号
	 */
	public int[] batchUpdate(final String sql, List<Object[]> batch) {
		int[] updateCounts = simpleJdbcTemplate.batchUpdate(sql, batch);
		return updateCounts;
	}

	/**
	 * 批量处理操作 例如：update t_actor set first_name = :firstName, last_name =
	 * :lastName where id = :id 参数用冒号
	 */
	public int[] batchUpdate(final String sql, List<Object[]> batch,
			int[] argTypes) {
		int[] updateCounts = simpleJdbcTemplate.batchUpdate(sql, batch,
				argTypes);
		return updateCounts;
	}

	/**
	 * 批量处理操作 例如：update t_actor set first_name = :firstName, last_name =
	 * :lastName where id = :id 参数用冒号
	 */
	public int[] batchUpdate(final String sql, Map<String, ?>[] batchValues) {
		int[] updateCounts = simpleJdbcTemplate.batchUpdate(sql, batchValues);
		return updateCounts;
	}

	protected <T> RowMapper<T> resultBeanMapper(Class<T> entity) {
		return new BeanPropertyRowMapper<T>(entity);
	}

	protected BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		return new BeanPropertySqlParameterSource(object);
	}

}
