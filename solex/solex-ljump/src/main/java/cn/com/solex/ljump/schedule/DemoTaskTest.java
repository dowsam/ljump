package cn.com.solex.ljump.schedule;

import cn.com.solex.schedule.AbstractAppTask;

public class DemoTaskTest extends AbstractAppTask {

	@Override
	public void doTask() {
		System.out.println(this.getTaskName() + ":获取将被执行的时间:"
				+ this.getNextFireTime() + ":获取上一次被执行的时间:"
				+ this.getPreviousFireTime());
	}

}
