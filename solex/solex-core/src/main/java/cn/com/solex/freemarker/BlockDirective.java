package cn.com.solex.freemarker;

import java.io.IOException;
import java.util.Map;

import cn.com.solex.freemarker.OverrideDirective.TemplateDirectiveBodyModel;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 提供父模块的block指令
 * 
 * @author xuenong_li
 * 
 */
public class BlockDirective implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = DirectiveUtils.getRequiredParam(params, "name");
		TemplateDirectiveBodyModel overrideBody = getOverrideBody(env, name);
		TemplateDirectiveBody outputBody = overrideBody == null ? body
				: overrideBody.body;
		if (outputBody != null) {
			outputBody.render(env.getOut());
		}
	}

	public String getName() {
		return "block";
	}

	private TemplateDirectiveBodyModel getOverrideBody(Environment env,
			String name) throws TemplateModelException {
		TemplateDirectiveBodyModel value = (TemplateDirectiveBodyModel) env
				.getVariable(DirectiveUtils.getOverrideVariableName(name));
		return value;
	}

}
