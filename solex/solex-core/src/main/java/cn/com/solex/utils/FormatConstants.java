package cn.com.solex.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * 提供日期常量
 * @author xuenong_li
 *
 */
public abstract class FormatConstants {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd",
			java.util.Locale.CHINA);
	public static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss",
			java.util.Locale.CHINA);
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
			java.util.Locale.CHINA);
	public static final DateFormat DATE_TIME_FORMAT_IMAGE = new SimpleDateFormat("yyyyMMddHHmmss",
			java.util.Locale.CHINA);
}
