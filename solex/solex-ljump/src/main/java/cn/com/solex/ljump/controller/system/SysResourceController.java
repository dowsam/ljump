package cn.com.solex.ljump.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseRestController;
import cn.com.solex.ljump.entity.system.SysResource;

/**
 * 资源控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/system/sysResource")
public class SysResourceController extends
		BaseRestController<SysResource, String> {

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, SysResource model) {
		return new ModelAndView("/system/sysResource/index");
	}

}
