package net.guanzhuo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jdk.nashorn.internal.parser.JSONParser;

@Controller
public class LoginController {

	@RequestMapping(value="index.do",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}
