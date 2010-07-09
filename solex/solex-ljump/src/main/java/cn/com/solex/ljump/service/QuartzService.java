package cn.com.solex.ljump.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.solex.ljump.dao.QuartzDao;
import cn.com.solex.ljump.entity.system.Qrtz_Triggers_Job_Details;

/**
 * 计划任务调度 事务层
 * 
 * @author l.xue.nong
 * 
 */
@Service
@Transactional(value = "quartzTransactionManager")
public class QuartzService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private QuartzDao quartzDao;

	@Transactional(readOnly = true)
	public List<Qrtz_Triggers_Job_Details> getQrtzTriggers() {
		return this.quartzDao.getQrtzTriggers();
	}
}
