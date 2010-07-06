package cn.com.solex.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * RuntimeException 异常封装类
 * 
 * @author l.xue.nong
 * 
 */
public class AppRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -2665560835479595326L;
	private Throwable rootcause;

	public AppRuntimeException(String message) {
		super(message);
	}

	public AppRuntimeException(Throwable cause) {
		this.rootcause = cause;
	}

	public AppRuntimeException(String message, Throwable cause) {
		super(message);
		this.rootcause = cause;
	}

	public String getMessage() {
		if (rootcause == null) {
			return super.getMessage();
		} else {
			return super.getMessage() + "; cause exception is: \n\t"
					+ rootcause.toString();
		}
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream s) {
		if (rootcause == null) {
			super.printStackTrace(s);
		} else {
			s.println(this);
			rootcause.printStackTrace(s);
		}
	}

	public void printStackTrace(PrintWriter s) {
		if (rootcause == null) {
			super.printStackTrace(s);
		} else {
			s.println(this);
			rootcause.printStackTrace(s);
		}
	}

	public Throwable getRootcause() {
		return rootcause;
	}
}
