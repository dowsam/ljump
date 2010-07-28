package cn.com.solex.memcached;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.com.solex.logger.MockLog4jAppender;
import cn.com.solex.test.SpringContextTestCase;

@ContextConfiguration(locations = { "/applicationContext-extension-test-memcached.xml" })
public class MemcachedTest extends SpringContextTestCase {
	@Autowired
	private SpyMemcachedClient spyClient;

	@Before
	public void setUp() {
		MockLog4jAppender appender = new MockLog4jAppender();
		appender.addToLogger(SpyMemcachedClient.class);
	}

	@Test
	public void normal() {
		String key = "consumer:1";
		String value = "admin";

		spyClient.set(key, 60 * 60 * 1, value);
		String result = spyClient.get(key);
		assertEquals(value, result);

		spyClient.delete(key);
		result = spyClient.get(key);
		assertNull(result);
	}

	@Test
	public void bulk() {
		String key1 = "consumer:1";
		String value1 = "admin";

		String key2 = "consumer:2";
		String value2 = "calvin";

		String key3 = "invalidKey";

		spyClient.set(key1, 60 * 60 * 1, value1);
		spyClient.set(key2, 60 * 60 * 1, value2);

		Map<String, String> result = spyClient.getBulk(key1, key2);
		assertEquals(value1, result.get(key1));
		assertEquals(value2, result.get(key2));
		assertNull(result.get(key3));
	}

}
