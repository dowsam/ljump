package cn.com.solex.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.solex.dao.JdbcDao;

@Service
@Transactional
public class BaseJdbcService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected JdbcDao jdbcDao;

	public BaseJdbcService() {
		super();
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
	@Transactional(readOnly = true)
	public <T> List<T> find(final String sql, Class<T> clazz,
			Map<String, Object> parameters) {
		return this.jdbcDao.find(sql, clazz, parameters);
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
	@Transactional(readOnly = true)
	public <T> List<T> find(final String sql, Class<T> clazz, Object... objects) {
		return this.jdbcDao.find(sql, clazz, objects);
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
	@Transactional(readOnly = true)
	public <T> T findForObject(final String sql, Class<T> clazz,
			Map<String, Object> parameters) {
		return this.jdbcDao.findForObject(sql, clazz, parameters);
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
	@Transactional(readOnly = true)
	public <T> T findForObject(final String sql, Class<T> clazz,
			Object... parameters) {
		return this.jdbcDao.findForObject(sql, clazz, parameters);
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
	@Transactional(readOnly = true)
	public int queryForInt(String sql, Map<String, ?> args) {
		return this.jdbcDao.queryForInt(sql, args);
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
	@Transactional(readOnly = true)
	public int queryForInt(String sql, Object... args) {
		return this.jdbcDao.queryForInt(sql, args);
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
	@Transactional(readOnly = true)
	public long findForLong(final String sql, Map<String, Object> parameters) {
		return this.jdbcDao.findForLong(sql, parameters);
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
	@Transactional(readOnly = true)
	public long findForLong(final String sql, Object... parameters) {
		return this.jdbcDao.findForLong(sql, parameters);
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
	@Transactional(readOnly = true)
	public Map<String, Object> findForMap(final String sql,
			Map<String, Object> parameters) {
		return this.jdbcDao.findForMap(sql, parameters);
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
	@Transactional(readOnly = true)
	public Map<String, Object> findForMap(final String sql,
			Object... parameters) {
		return this.jdbcDao.findForMap(sql, parameters);
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
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findForListMap(final String sql,
			Map<String, Object> parameters) {
		return this.jdbcDao.findForListMap(sql, parameters);
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
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findForListMap(final String sql,
			Object... parameters) {
		return this.jdbcDao.findForListMap(sql, parameters);
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
		return this.jdbcDao.executeForObject(sql, bean);
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
		return this.jdbcDao.executeForObject(sql, args);
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
		return this.jdbcDao.executeForObject(sql, args);
	}

	/**
	 * 批量处理操作 例如：update t_actor set first_name = :firstName, last_name =
	 * :lastName where id = :id 参数用冒号
	 */
	public int[] batchUpdate(final String sql, List<Object[]> batch) {
		return this.jdbcDao.batchUpdate(sql, batch);
	}

	/**
	 * 批量处理操作 例如：update t_actor set first_name = :firstName, last_name =
	 * :lastName where id = :id 参数用冒号
	 */
	public int[] batchUpdate(final String sql, List<Object[]> batch,
			int[] argTypes) {
		return this.jdbcDao.batchUpdate(sql, batch, argTypes);
	}

	/**
	 * 批量处理操作 例如：update t_actor set first_name = :firstName, last_name =
	 * :lastName where id = :id 参数用冒号
	 */
	public int[] batchUpdate(final String sql, Map<String, ?>[] batchValues) {
		return this.jdbcDao.batchUpdate(sql, batchValues);
	}
}
