package cn.com.solex.ljump.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.com.solex.ljump.entity.system.Qrtz_Triggers_Job_Details;

/**
 * 任务调试
 * 
 * @author Administrator
 * 
 */
@Repository
public class QuartzDao {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected SimpleJdbcTemplate simpleJdbcTemplate;
	protected SimpleJdbcInsert simpleJdbcInsert;

	public static String QUERY_QUARTZ_SQL = "SELECT t1.*,(SELECT t2.DESCRIPTION FROM QRTZ_JOB_DETAILS t2 WHERE t2.JOB_NAME=t1.JOB_NAME AND t2.JOB_GROUP=t1.JOB_GROUP) AS JOB_DETAILS_DESCRIPTION,(SELECT t3.JOB_CLASS_NAME FROM QRTZ_JOB_DETAILS t3 WHERE t3.JOB_NAME=t1.JOB_NAME AND t3.JOB_GROUP=t1.JOB_GROUP) AS JOB_DETAILS_JOB_CLASS_NAME FROM QRTZ_TRIGGERS t1 ORDER BY t1.START_TIME";
	public static final Map<String, String> status = new HashMap<String, String>();
	static {
		status.put("ACQUIRED", "运行中");
		status.put("PAUSED", "暂停中");
		status.put("WAITING", "等待中");
	}

	@Autowired
	public void setDataSource(
			@Qualifier(value = "quartzDataSource") DataSource quartzDataSource) {
		simpleJdbcTemplate = new SimpleJdbcTemplate(quartzDataSource);
		simpleJdbcInsert = new SimpleJdbcInsert(quartzDataSource);
	}

	/**
	 * 所有任务列表
	 * 
	 * @return
	 */
	public List<Qrtz_Triggers_Job_Details> getQrtzTriggers() {
		List<Qrtz_Triggers_Job_Details> results = simpleJdbcTemplate.query(
				QUERY_QUARTZ_SQL,
				resultBeanMapper(Qrtz_Triggers_Job_Details.class));
		return results;
	}

	protected <T> RowMapper<T> resultBeanMapper(Class<T> entity) {
		return new BeanPropertyRowMapper<T>(entity);
	}
}
