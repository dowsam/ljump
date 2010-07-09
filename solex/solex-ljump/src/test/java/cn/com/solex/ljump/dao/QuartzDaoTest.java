package cn.com.solex.ljump.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.solex.ljump.entity.system.Qrtz_Triggers_Job_Details;
import cn.com.solex.test.SpringTxTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class QuartzDaoTest extends SpringTxTestCase {
	@Autowired
	private QuartzDao quartzDao;

	@Test
	public void getQrtzTriggers() {
		List<Qrtz_Triggers_Job_Details> list = this.quartzDao.getQrtzTriggers();
		assertEquals(list.size(), 6);
	}
}
