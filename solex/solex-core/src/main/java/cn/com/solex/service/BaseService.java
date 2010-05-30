package cn.com.solex.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.solex.dao.BaseDao;
import cn.com.solex.dao.Page;

/**
 * 对外提供基础服务类
 * 
 * @author xuenong_li
 * 
 */
@Service
@Transactional
public class BaseService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected BaseDao baseDao;

	/**
	 * 保存新增或修改的对象.
	 */
	public <T> void save(final T entity) {
		baseDao.save(entity);
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属性的transient对象.
	 */
	public <T> void delete(final T entity) {
		baseDao.delete(entity);
	}

	/**
	 * 按id删除对象.
	 */
	public <T, PK extends Serializable> void delete(final Class<T> entityClass,
			final PK id) {
		baseDao.delete(entityClass, id);
	}

	/**
	 * 按id获取对象.
	 */
	@Transactional(readOnly = true)
	public <T, PK extends Serializable> T get(final Class<T> entityClass,
			final PK id) {
		return baseDao.get(entityClass, id);
	}

	/**
	 * 获取全部对象.
	 */
	@Transactional(readOnly = true)
	public <T> List<T> getAll(Class<T> entityClass) {
		return baseDao.getAll(entityClass);
	}

	/**
	 * 获取全部对象,支持排序.
	 */
	@Transactional(readOnly = true)
	public <T> List<T> getAll(Class<T> entityClass, String orderBy,
			boolean isAsc) {
		return baseDao.getAll(entityClass, orderBy, isAsc);
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	@Transactional(readOnly = true)
	public <T> List<T> findBy(final Class<T> entityClass,
			final String propertyName, final Object value) {
		return baseDao.findBy(entityClass, propertyName, value);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	@Transactional(readOnly = true)
	public <T> T findUniqueBy(final Class<T> entityClass,
			final String propertyName, final Object value) {
		return baseDao.findUniqueBy(entityClass, propertyName, value);
	}

	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public <T, PK extends Serializable> List<T> findByIds(Class<T> entityClass,
			List<PK> ids) {
		return baseDao.findByIds(entityClass, ids);
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@Transactional(readOnly = true)
	public <X> List<X> find(final String hql, final Object... values) {
		return baseDao.find(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	@Transactional(readOnly = true)
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return baseDao.find(hql, values);
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) baseDao.findUnique(hql, values);
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) baseDao.findUnique(hql, values);
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return baseDao.batchExecute(hql, values);
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return baseDao.batchExecute(hql, values);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		return baseDao.createQuery(queryString, values);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString,
			final Map<String, ?> values) {
		return baseDao.createQuery(queryString, values);
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	@Transactional(readOnly = true)
	public <T> List<T> find(final Class<T> entityClass,
			final Criterion... criterions) {
		return baseDao.find(entityClass, criterions);
	}

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	@Transactional(readOnly = true)
	public <T> T findUnique(final Class<T> entityClass,
			final Criterion... criterions) {
		return baseDao.findUnique(entityClass, criterions);
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public <T> Criteria createCriteria(final Class<T> entityClass,
			final Criterion... criterions) {
		return baseDao.createCriteria(entityClass, criterions);
	}

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize
	 * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public <T> void initEntity(T entity) {
		baseDao.initEntity(entity);
	}

	/**
	 * @see #initEntity(Object)
	 */
	public <T> void initEntity(List<T> entityList) {
		baseDao.initEntity(entityList);
	}

	/**
	 * 为Query添加distinct transformer.
	 */
	public Query distinct(Query query) {
		return baseDao.distinct(query);
	}

	/**
	 * 为Criteria添加distinct transformer.
	 */
	public Criteria distinct(Criteria criteria) {
		return baseDao.distinct(criteria);
	}

	/**
	 * 使用 HQL 预加载lazy init 用DISTINCE_ROOT_ENTITY排除重复数据.
	 * 
	 * @param <T>
	 * @param queryString
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> List<T> findHqlDistinct(String queryString, Object... values) {
		Query query = createQuery(queryString, values);
		return distinct(query).list();
	}

	/**
	 * 使用Criteria 预加载lazy init 用DISTINCE_ROOT_ENTITY排除重复数据. join 方式
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> findCriteriaJoinDistinct(Class<T> entityClass,
			String propertyName) {
		return findCriteriaDistinct(entityClass, propertyName,
				org.hibernate.FetchMode.JOIN);
	}

	/**
	 * 使用Criteria 预加载lazy init 用DISTINCE_ROOT_ENTITY排除重复数据.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param fetchMode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> List<T> findCriteriaDistinct(Class<T> entityClass,
			String propertyName, org.hibernate.FetchMode fetchMode) {
		Criteria criteria = createCriteria(entityClass).setFetchMode(
				propertyName, fetchMode);
		return distinct(criteria).list();
	}

	/**
	 * 取得对象的主键名.
	 */
	@Transactional(readOnly = true)
	public <T> String getIdName(final Class<T> entityClass) {
		return baseDao.getIdName(entityClass);
	}

	// -- 分页查询函数 --//
	/**
	 * 分页获取全部对象.
	 */
	@Transactional(readOnly = true)
	public <T> Page<T> getAll(final Class<T> entityClass, final Page<T> page) {
		return baseDao.getAll(entityClass, page);
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@Transactional(readOnly = true)
	public <T> Page<T> findPage(final Page<T> page, final String hql,
			final Object... values) {
		return baseDao.findPage(page, hql, values);
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@Transactional(readOnly = true)
	public <T> Page<T> findPage(final Page<T> page, final String hql,
			final Map<String, ?> values) {
		return baseDao.findPage(page, hql, values);
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	@Transactional(readOnly = true)
	public <T> Page<T> findPage(final Class<T> entityClass, final Page<T> page,
			final Criterion... criterions) {
		return baseDao.findPage(entityClass, page, criterions);
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	@Transactional(readOnly = true)
	public <T> boolean isPropertyUnique(final Class<T> entityClass,
			final String propertyName, final Object newValue,
			final Object oldValue) {
		return baseDao.isPropertyUnique(entityClass, propertyName, newValue,
				oldValue);
	}

}
