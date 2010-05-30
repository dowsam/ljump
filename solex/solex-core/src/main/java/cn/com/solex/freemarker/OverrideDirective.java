package cn.com.solex.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


/**
 * 复写父模块的block指今
 * @author xuenong_li
 *
 */
public class OverrideDirective implements TemplateDirectiveModel {

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = DirectiveUtils.getRequiredParam(params, "name");
		String overrideVariableName = DirectiveUtils.getOverrideVariableName(name);
		
		if(env.getVariable(overrideVariableName) == null) {
			env.setVariable(overrideVariableName, new TemplateDirectiveBodyModel(body));
		}
	}

	public static class TemplateDirectiveBodyModel implements TemplateModel {
		public TemplateDirectiveBody body;

		public TemplateDirectiveBodyModel(TemplateDirectiveBody body) {
			this.body = body;
		}
	}

	public String getName() {
		return "override";
	}

}
