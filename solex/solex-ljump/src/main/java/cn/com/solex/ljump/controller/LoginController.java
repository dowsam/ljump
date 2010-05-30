package cn.com.solex.ljump.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.solex.controller.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@RequestMapping
	public String login(HttpServletRequest request, Model model,
			@RequestParam(value = "error", required = false) String error) {
		if ("1".equals(error)) {
			saveError(request, "用户名密码错误,请重试.");
		} else if ("5".equals(error)) {
			saveError(request, "验证码错误,请重试.");
		} else if ("3".equals(error)) {
			saveError(request, "此帐号已从别处登录.");
		}
		return "/login";
	}
}
