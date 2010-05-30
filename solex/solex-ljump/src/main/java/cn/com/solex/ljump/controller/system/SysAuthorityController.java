package cn.com.solex.ljump.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseRestController;
import cn.com.solex.ljump.entity.system.SysAuthority;



/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/system/sysAuthority")
public class SysAuthorityController extends
		BaseRestController<SysAuthority, String> {

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, SysAuthority model) {
		return new ModelAndView("/system/sysAuthority/index");
	}

}
