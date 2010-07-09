package cn.com.solex.ljump.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseRestController;
import cn.com.solex.ljump.entity.system.Qrtz_Triggers_Job_Details;
import cn.com.solex.ljump.service.QuartzService;

@Controller
@RequestMapping("/system/quartz")
public class QuartzController extends
		BaseRestController<Qrtz_Triggers_Job_Details, String> {

	private final String LIST_ACTION = "redirect:/system/quartz";

	@Autowired
	private QuartzService quartzService;

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, Qrtz_Triggers_Job_Details model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/system/quartz/index");
		modelAndView.addObject("data", this.quartzService.getQrtzTriggers());
		return modelAndView;
	}

}
