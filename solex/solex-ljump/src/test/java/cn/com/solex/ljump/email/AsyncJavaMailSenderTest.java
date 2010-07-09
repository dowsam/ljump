package cn.com.solex.ljump.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.solex.concurrent.AsyncToken;
import cn.com.solex.concurrent.IResponder;
import cn.com.solex.email.AsyncJavaMailSender;
import cn.com.solex.test.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml",
		"/email/applicationContext-email.xml" })
public class AsyncJavaMailSenderTest extends SpringTxTestCase {
	@Autowired
	private AsyncJavaMailSender asyncJavaMailSender;

	@Test
	public void send() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			AsyncToken<Object> token = this.asyncJavaMailSender.send(
					"xuenong520@163.com", "l.xue.nong@gmail.com", "异步测试" + i);

			token.addResponder(new IResponder() {
				@Override
				public <T> void onResult(T result) {
					System.out.println("onResult();邮件发送成功!");
				}

				@Override
				public void onFault(Exception fault) {
					System.out.println("onFault():" + fault);
				}
			});
		}

		Thread.sleep(120000);

	}
}
