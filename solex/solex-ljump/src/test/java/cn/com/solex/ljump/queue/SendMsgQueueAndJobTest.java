package cn.com.solex.ljump.queue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.solex.ljump.schedule.SendMsgJob;
import cn.com.solex.schedule.TaskSchedulerService;
import cn.com.solex.test.SpringTxTestCase;
import cn.com.solex.utils.TimeUtils;

@ContextConfiguration(locations = { "/applicationContext-test.xml",
		"/schedule/applicationContext-quartz-timer-cluster.xml",
		"/queue/applicationContext-queue.xml" })
public class SendMsgQueueAndJobTest extends SpringTxTestCase {

	@Autowired
	private TaskSchedulerService taskSchedulerService;

	@Test
	public void start() {
		this.taskSchedulerService.scheduleSecondly(SendMsgJob.class,
				"SendMsgJob", "定时生产短信内容", 1);
		TimeUtils.sleep(240000);
	}

}
