package cn.com.solex.ljump.schedule;

import org.junit.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.solex.schedule.TaskSchedulerService;
import cn.com.solex.test.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml",
		"/schedule/applicationContext-quartz-timer-cluster.xml" })
public class TaskSchedulerServiceTest extends SpringTxTestCase {

	@Autowired
	private TaskSchedulerService taskSchedulerService;

	@Test
	public void taskSchedulerServiceTs() throws InterruptedException {
		System.out.println(this.taskSchedulerService);
		Thread.sleep(10000);
	}

	@Test
	public void testClient() throws InterruptedException, SchedulerException {
		// this.taskSchedulerService.scheduleImmediate(DemoTask.class,
		// "DemoTask1", "测试1", 100, 36000);
		// this.taskSchedulerService.schedule(DemoTask.class, "DemoTask2",
		// "测试2",
		// "0 0/10 9-17 ? * MON-FRI");

		// if (!this.taskSchedulerService.isShutdown()) {
		// this.taskSchedulerService.start();
		// }
		//
		// List<JobExecutionContext> jobs = this.taskSchedulerService
		// .getTheScheduler().getCurrentlyExecutingJobs();
		// System.out.println(jobs.size());
		//
		// for (JobExecutionContext jobExecutionContext : jobs) {
		// System.out.println(ToStringBuilder
		// .reflectionToString(jobExecutionContext));
		// }
		// this.taskSchedulerService.scheduleImmediate(DemoTaskTest.class,
		// "DemoTaskTest222", "测试2222", 10, 3000);
		// this.taskSchedulerService.scheduleDialy(DemoTaskTest.class,
		// "scheduleDialy", "每日定时执行", 22, 10);
		// this.taskSchedulerService.scheduleWeekly(DemoTaskTest.class,
		// "scheduleWeekly", "每周定时执行", 1, 22, 30);
		// this.taskSchedulerService.scheduleHourly(DemoTaskTest.class,
		// "scheduleHourly_no", "以小时为单位定时执行", 1);
		Thread.sleep(10000);
	}
}
