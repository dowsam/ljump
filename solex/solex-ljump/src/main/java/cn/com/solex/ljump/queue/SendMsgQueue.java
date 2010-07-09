package cn.com.solex.ljump.queue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import cn.com.solex.concurrent.AsyncToken;
import cn.com.solex.concurrent.AsyncTokenFactory;
import cn.com.solex.concurrent.AsyncTokenUtils;
import cn.com.solex.concurrent.DefaultAsyncTokenFactory;
import cn.com.solex.concurrent.IResponder;
import cn.com.solex.ljump.entity.SendMsg;
import cn.com.solex.queue.PeriodConsumer;
import cn.com.solex.queue.QueuesHolder;
import cn.com.solex.utils.NonceUtils;

/**
 * 短信列 表,异步
 * 
 * @author Administrator
 * 
 */
public class SendMsgQueue extends PeriodConsumer {

	private BlockingQueue<SendMsg> msgQueue;
	private AsyncTokenFactory asyncTokenFactory = new DefaultAsyncTokenFactory();

	@Override
	protected void processMessageList(final List<?> messageList) {
		if (messageList != null && !messageList.isEmpty()) {
			if (msgQueue == null) {
				msgQueue = QueuesHolder.getQueue("sendMsg");
			}
			// List<SendMsg> tempList = new ArrayList<SendMsg>(
			// (List<SendMsg>) messageList);
			// List<SendMsg> msgs = (List<SendMsg>)
			// DataUtils.randomSome(tempList);
			// System.out.println("--短信发送成功的列表---");
			// for (SendMsg sendMsg : msgs) {
			// System.out.println(sendMsg.toString());
			// messageList.remove(sendMsg);
			// }
			// System.out.println("--end--");
			// System.out.println();
			// System.out.println("--短信发送失败的列表,并且继续插入队列当中,继续发送短信--");
			// for (Object msg : messageList) {
			// SendMsg g = (SendMsg) msg;
			// System.out.println(g.toString());
			// this.msgQueue.offer(g);
			// }
			// System.out.println("--end--");
			// System.out.println();
			final AsyncToken<SendMsg> token = new AsyncToken<SendMsg>();
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						SendMsg msg = (SendMsg) messageList.get(0);
						token.setComplete(msg); // 通知Responder
												// token执行完
					} catch (Exception e) {
						token.setFault(e); // 通知Responder token发生错误
					}
				}
			});
			thread.start();

			// token可以继续传递给外部,以便外面感兴趣的listener监听这个异步方法的执行结果
			token.addResponder(new IResponder() {
				public void onFault(Exception fault) {
					System.out.println("email send fail,cause:" + fault);
					// 此处可以直接引用address,subject,content,如,我们可以再次发送一次
					// sendAsyncEmail(address, subject, content);
				}

				public void onResult(Object result) {
					System.out.println(result instanceof SendMsg);
					System.out.println("email send success,result:" + result);
				}
			});
		}
	}

	@Override
	protected void clean() {

	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		SendMsgQueue q = new SendMsgQueue();
		q.setQueueName("sendMsg");
		q.start();
		List<SendMsg> list = new ArrayList<SendMsg>();
		SendMsg msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		msg = new SendMsg();
		msg.setSeq(NonceUtils.randomInt());
		msg.setSrcNum("15985850548");
		msg.setDstNums(new String[] { "110", "120", "119" });
		msg.setNeedRpt(true);
		msg.setMsgStr("日本鬼子来了.....");
		list.add(msg);

		q.processMessageList(list);
		q.stop();
	}

}
