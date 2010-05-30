package cn.com.solex.ljump.controller.system;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseRestController;
import cn.com.solex.ljump.entity.OperatorUser;
import cn.com.solex.ljump.entity.system.SysUser;
import cn.com.solex.utils.SpringSecurityUtils;

/**
 * 用户管理
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/system/sysUser")
public class SysUserController extends
		BaseRestController<SysUser, java.lang.String> {

	private final String LIST_ACTION = "redirect:/system/sysUser";

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, SysUser model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/system/sysUser/index");
		modelAndView.addObject("users", this.baseService.getAll(SysUser.class));
		return modelAndView;
	}

	@Override
	public ModelAndView edit(@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = baseService.get(SysUser.class, id);
		return new ModelAndView("/system/sysUser/edit", "user", user);
	}

	@Override
	public ModelAndView delete(@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response) {
		SysUser sysUser = baseService.get(SysUser.class, id);
		baseService.delete(sysUser);
		OperatorUser operatorUser = SpringSecurityUtils.getCurrentUser();
		User user = new User(operatorUser.getUsername(), operatorUser
				.getPassword(), operatorUser.isEnabled(), operatorUser
				.isAccountNonExpired(), operatorUser.isCredentialsNonExpired(),
				operatorUser.isAccountNonLocked(),
				new ArrayList<GrantedAuthority>());
		SpringSecurityUtils.saveUserDetailsToContext(user, request);
		return new ModelAndView(LIST_ACTION);
	}

	@Override
	public ModelAndView show(@PathVariable String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SysUser user = baseService.get(SysUser.class, id);
		return new ModelAndView("/system/sysUser/show", "user", user);
	}

}
