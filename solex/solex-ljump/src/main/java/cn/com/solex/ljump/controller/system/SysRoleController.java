package cn.com.solex.ljump.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseRestController;
import cn.com.solex.ljump.entity.system.SysRole;

/**
 * 权限管理
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/system/sysRole")
public class SysRoleController extends BaseRestController<SysRole, String> {

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, SysRole model) {
		return new ModelAndView("/system/sysRole/index");
	}

}
