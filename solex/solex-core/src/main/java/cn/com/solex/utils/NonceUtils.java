package cn.com.solex.utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 
 * <p>类描述：生成唯一数</p>        
 * <p>项目名称：solex-core</p>    
 * <p>类名称：NonceUtils</p>      
 * <p>创建人：l.xue.nong@gmail.com</p>
 * <p>创建时间：2010-6-13 下午01:15:54</p>   
 * <p>修改人：l.xue.nong@gmail.com</p>   
 * <p>修改时间：2010-6-13 下午01:15:54</p>  
 * <p>修改备注：</p>    
 * @version
 */
public class NonceUtils {

	//RFC3339 日期标准格式,
	private static final SimpleDateFormat INTERNATE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	//定长格式化所用字符串, 含1,2,4,8位的字符串.
	private static final String[] SPACES = { "0", "00", "0000", "00000000" };

	//同一JVM同一毫秒内请求的计数器.
	private static Date lastTime;
	private static int counter = 0;

	//-- Random function --//
	/**
	 * 使用较低强度的java.util.Random(),生成含所有字母与数字的字符串.
	 * 
	 * @param length 返回字符串长度
	 */
	public static String randomString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * 使用SecureRandom生成Integer.
	 */
	public static int randomInt() {
		return new SecureRandom().nextInt();
	}

	/**
	 * 使用SecureRandom生成Integer,返回长度不大于8的Hex编码结果.
	 */
	public static String randomHexInt() {
		return Integer.toHexString(randomInt());
	}

	/**
	 * 使用SecureRandom生成Long.
	 */
	public static long randomLong() {
		return new SecureRandom().nextLong();
	}

	/**
	 * 使用SecureRandom生成Long, 返回长度不大于16的Hex编码结果.
	 */
	public static String randomHexLong() {
		return Long.toHexString(randomLong());
	}

	/**
	 * 使用SecureRandom生成32字符,每8位带-的UUID,见rfc4122.
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString();
	}

	//-- Timestamp function --//
	/**
	 * 返回Internate标准格式的当前毫秒级时间戳字符串.
	 * 
	 * 标准格式为yyyy-MM-dd'T'HH:mm:ss.SSS'Z', 如2009-10-15T14:24:50.316Z.
	 */
	public static String currentTimestamp() {
		Date now = new Date();
		return INTERNATE_DATE_FORMAT.format(now);
	}

	/**
	 * 返回当前距离1970年的毫秒数.
	 */
	public static long currentMills() {
		return System.currentTimeMillis();
	}

	/**
	 * 返回当前距离1970年的毫秒数, 返回Hex编码的结果.
	 */
	public static String currentHexMills() {
		return Long.toHexString(currentMills());
	}

	//-- Helper function --//
	/**
	 * 返回Hex编码的同一毫秒内的Counter.
	 */
	public static synchronized String getCounter() {
		Date currentTime = new Date();

		if (currentTime.equals(lastTime)) {
			counter++;
		} else {
			lastTime = currentTime;
			counter = 0;
		}
		return Integer.toHexString(counter);
	}

	//-- Helper function --//
	/**
	 * 格式化字符串, 固定字符串长度, 不足长度在前面补0.
	 */
	public static String format(String source, int length) {
		int spaceLength = length - source.length();
		StringBuilder buf = new StringBuilder();

		while (spaceLength >= 8) {
			buf.append(SPACES[3]);
			spaceLength -= 8;
		}

		for (int i = 2; i >= 0; i--) {
			if ((spaceLength & (1 << i)) != 0) {
				buf.append(SPACES[i]);
			}
		}

		buf.append(source);
		return buf.toString();
	}

	public static void main(String args[]) throws IOException {
		System.out.println(randomLong() + currentMills());
	}
}
