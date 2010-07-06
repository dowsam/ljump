package cn.com.solex.schedule;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定义抽象的Task任务
 * 
 * @author l.xue.nong
 * 
 */
public abstract class AbstractAppTask implements Job {
	protected JobExecutionContext jobConetext;

	public AbstractAppTask() {
		super();
	}

	/**
	 * 获取上一次被执行的时间
	 * 
	 * @return Date
	 */
	public Date getPreviousFireTime() {
		return jobConetext.getPreviousFireTime();
	}

	/**
	 * 获取将被执行的时间
	 * 
	 * @return Date
	 */
	public Date getNextFireTime() {
		return jobConetext.getNextFireTime();
	}

	/**
	 * The actual time the trigger fired. For instance the scheduled time may
	 * have been 10:00:00 but the actual fire time may have been 10:00:03 if the
	 * scheduler was too busy.
	 * 
	 * @return Date
	 */
	public Date getFireTime() {
		return jobConetext.getFireTime();
	}

	/**
	 * doTask－－执行任务的抽象方法，在此方法内实现任务代码
	 */
	public abstract void doTask();

	final public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		this.jobConetext = jobExecutionContext;
		this.doTask();
	}

	public String getTaskName() {
		return this.jobConetext.getJobDetail().getName();
	}
}
