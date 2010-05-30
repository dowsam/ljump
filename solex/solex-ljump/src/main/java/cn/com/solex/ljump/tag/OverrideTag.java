package cn.com.solex.ljump.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Override标签
 * 
 * @author Administrator
 * 
 */
public class OverrideTag extends BodyTagSupport {
	private static final long serialVersionUID = 1921886178731121161L;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		return isOverrided() ? SKIP_BODY : EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		if (isOverrided()) {
			return EVAL_PAGE;
		}
		BodyContent b = getBodyContent();
		// System.out.println("Override.content:"+b.getString());
		String varName = Utils.getOverrideVariableName(name);

		// TODO 或者可以通过参数指定scope: 如page or request
		pageContext.getRequest().setAttribute(varName, b.getString());
		return EVAL_PAGE;
	}

	private boolean isOverrided() {
		String varName = Utils.getOverrideVariableName(name);
		return pageContext.getRequest().getAttribute(varName) != null;
	}

}
