package cn.com.solex.ljump.schedule;

import java.util.concurrent.BlockingQueue;

import cn.com.solex.ljump.entity.SendMsg;
import cn.com.solex.queue.QueuesHolder;
import cn.com.solex.schedule.AbstractAppTask;
import cn.com.solex.utils.NonceUtils;

/**
 * 模拟数据,每1秒钟插入往队列中插入数据
 * 
 * @author Administrator
 * 
 */
public class SendMsgJob extends AbstractAppTask {

	private BlockingQueue<SendMsg> msgQueue;

	@Override
	public void doTask() {
		if (msgQueue == null) {
			msgQueue = QueuesHolder.getQueue("sendMsg");
		}
		SendMsg msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		msgQueue.offer(msg);
	}
}
