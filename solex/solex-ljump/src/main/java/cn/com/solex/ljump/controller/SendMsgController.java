package cn.com.solex.ljump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.solex.controller.BaseController;
import cn.com.solex.ljump.entity.SendMsg;
import cn.com.solex.queue.QueuesHolder;

@Controller
@RequestMapping("/sendmsg")
public class SendMsgController extends BaseController {

	private BlockingQueue<SendMsg> sendMsg;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/sendmsg/index");
		if (sendMsg == null) {
			sendMsg = QueuesHolder.getQueue("sendMsg");
		}
		List<SendMsg> data = new ArrayList<SendMsg>(sendMsg);
		modelAndView.addObject("data", data);
		return modelAndView;
	}
}
