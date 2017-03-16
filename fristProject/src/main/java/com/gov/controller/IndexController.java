package com.gov.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gov.common.service.UserRightViewService;
import com.gov.model.Guest;
import com.gov.model.User;
import com.gov.model.UserRightView;
import com.gov.service.GuestService;
import com.gov.service.UserService;
import com.gov.util.CompareTime;

@Controller
public class IndexController {
	@Resource
	private UserService userService;
	@Resource
	private UserRightViewService userRightViewService;
	@Resource
	private GuestService guestService;
	@Resource
	private CompareTime ct;
	@RequestMapping(value="ajax/myinfo.do")
	@ResponseBody
	public ModelAndView myinfo(){
		ModelAndView mav = new ModelAndView("nest/myinfo");
		BaseController bc = new BaseController();
		User user = bc.getCurrentUser();
		List<UserRightView> urv = userRightViewService.selectByUsername(user.getUserName());
		System.out.println(urv);
		mav.addObject("user", user);
		mav.addObject("right", urv);
		return mav;
	}
	@RequestMapping(value="ajax/guestOrder.do")
	@ResponseBody
	public ModelAndView guestOrder(Integer guestroomid,String guestcometime){
		ModelAndView mav = new ModelAndView("nest/guestOrder");
		List<Guest> list = guestService.selectByRoomidAndCometime(guestroomid,guestcometime);
		Guest guest = list.get(0);
		String cometime = guest.getGuestcometime();
		long come = ct.getTimeMillisecond(cometime);
		mav.addObject("order","XS"+come+"L00"+guest.getGuestid());
		mav.addObject("guest", guest);
		return mav;
	}
}
