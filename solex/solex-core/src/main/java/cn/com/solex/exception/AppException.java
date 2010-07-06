package cn.com.solex.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Exception 封装类
 * 
 * @author Administrator
 * 
 */
public abstract class AppException extends Exception {
	private static final long serialVersionUID = -326990210570870857L;
	private Throwable rootcause;

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		this.rootcause = cause;
	}

	public AppException(String message, Throwable cause) {
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
