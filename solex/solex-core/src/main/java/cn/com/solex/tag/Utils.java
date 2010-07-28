package cn.com.solex.tag;

/**
 * 标签辅助工具
 * 
 * @author Administrator
 * 
 */
public class Utils {
	public static String BLOCK = "__jsp_override__";

	static String getOverrideVariableName(String name) {
		return BLOCK + name;
	}
}
