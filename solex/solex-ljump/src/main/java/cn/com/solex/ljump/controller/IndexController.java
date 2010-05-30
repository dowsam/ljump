package cn.com.solex.ljump.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseController;
import cn.com.solex.ljump.entity.system.SysResource;
import cn.com.solex.ljump.service.SysResourceService;

/**
 * 首页
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Autowired
	private SysResourceService service;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/index");
		return modelAndView;
	}

	@RequestMapping(value = "/menu")
	public void bulidMenuHtml(HttpServletRequest request,
			HttpServletResponse response) {
		List<SysResource> list = service.findHqlDistinct(
				SysResource.QUERY_BY_RESOURCEPARENT_WHERE_NULL,
				SysResource.MENU_TYPE);
		StringBuffer stringBuffer = new StringBuffer();
		for (SysResource sysResource : list) {
			stringBuffer.append("<div>");
			stringBuffer.append("<span>").append(sysResource.getValueName())
					.append("</span>");
			for (SysResource resource : sysResource.getChildResource()) {
				stringBuffer.append("<a href=\"").append(
						this.getServerPath(request) + resource.getValue())
						.append("\">").append(resource.getValueName()).append(
								"</a>");
			}
			stringBuffer.append("</div>");
		}
		renderHtml(response, stringBuffer.toString());
	}
}
