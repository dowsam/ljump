package cn.com.solex.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
/**
 * spring security密码工具类
 * @author xuenong_li
 *
 */
public abstract class SpringSecurityPasswordUtils {

	public static Md5PasswordEncoder md5PasswordEncoder = null;
	static {
		md5PasswordEncoder = new Md5PasswordEncoder();
	}

	public static String getMd5PasswordEncoder(String password, String salt) {
		return md5PasswordEncoder.encodePassword(password, salt);
	}
	public static void main(String[] args) {
		System.out.println(getMd5PasswordEncoder("admin", "admin"));
	}

}
