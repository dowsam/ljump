package cn.com.solex.ljump.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.solex.ljump.entity.system.Qrtz_Triggers_Job_Details;
import cn.com.solex.test.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuartzServiceTest extends SpringTxTestCase {

	@Autowired
	private QuartzService quartzService;

	@Test
	public void getQrtzTriggers() {
		List<Qrtz_Triggers_Job_Details> list = this.quartzService
				.getQrtzTriggers();
		assertEquals(list.size(), 6);
	}

}
