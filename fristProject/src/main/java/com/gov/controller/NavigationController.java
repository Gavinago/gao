package com.gov.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gov.model.RoomType;
import com.gov.model.Step;
import com.gov.service.RoomTypeService;
import com.gov.service.StepService;
import com.gov.util.CacheUtils;

import ch.qos.logback.classic.spi.StackTraceElementProxy;

@Controller
public class NavigationController {
	@Resource
	private StepService stepService; 
	@Resource
	private RoomTypeService roomTypeService;
	/**
	 * 自动携带站点配置信息属性注入的ModelAndView创建
	 * @return
	 */
	@RequestMapping(value="/navigation/dynamenu.do",method= RequestMethod.GET)
	public  ModelAndView createModelAndView(HttpServletRequest request){
		String localpath = request.getContextPath();
		ModelAndView mav = new ModelAndView("/nest/dynamenu");
		String nav = (String) CacheUtils.get("navigation");
		if(null==nav){
		StringBuilder sb = new StringBuilder();
		List<Step> list = stepService.selectAllParent(0);
		if(list!=null||list.size()>0){
		sb.append("<ul class='nav navbar-nav' >");
		for (Step step : list) {
			sb.append("<li class=\"dropdown\"id='li"+step.getStepid()+"'>");
			sb.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">");
			sb.append(step.getStepname());
			sb.append("<span class=\"caret\"></span></a>");
			sb.append("<ul class=\"dropdown-menu\">");
			List<Step> childs = stepService.selectAllStept(step.getStepid());
			if(childs!=null||childs.size()>0){
				int i =0;
			for (Step child : childs) {
				//有子菜单
				//生成下拉菜单html
				sb.append("<li id='li"+child.getStepurl()+"'><a href=\""+localpath+"");
				sb.append(child.getStepurl());
				sb.append("\">");
				sb.append(child.getStepname());
				sb.append("</a></li>");
				++i;
				if(i<childs.size()){
					sb.append("<li class='divider'></li>");
				}
			}
			sb.append("</ul>");
			}else{
				//无子菜单
				sb.append("<li><a href=\""+localpath+"");
				sb.append(step.getStepurl());
				sb.append("\">");
				sb.append(step.getStepname());
				sb.append("</a></li>");
			}
		}
		sb.append("</ul>");
		CacheUtils.put("navigation", sb.toString());
		mav.addObject("dynamenu", sb.toString());
		}
		}else{
			mav.addObject("dynamenu", nav);
		}
		return mav;
	}
	/**
	 * 左侧导航栏动态注入
	 * @return
	 */
	@RequestMapping(value="/leftNavigation/dynamenu.do")
	public ModelAndView leftNavigation(HttpServletRequest request){
		String localpath = request.getContextPath();
		ModelAndView mav = new ModelAndView("/nest/leftDynamenu");
		//查看缓存中是否存在
		String nav = (String) CacheUtils.get("leftNavigation");
		StringBuilder sb = new StringBuilder();
		if(null==nav){
		int i=0;
		int j=10;
		List<Step> list = stepService.selectAllParent(1);
		if(null!=list||list.size()>0){
			sb.append("<div class='panel-group' id='accordion2'>");
			for (Step step : list) {
				j=j+10;
				sb.append(" <div class='panel panel-default'><div class='panel-heading' data-toggle='collapse'data-parent='#accordion2' href='#"+j+"'><a class='accordion-toggle'>"+step.getStepname()+"</a></div>");
				List<Step> childs = stepService.selectAllStept(step.getStepid());
				if(null!=childs||childs.size()>0){
					i++;
					if(i==1){
						sb.append("<div id='"+j+"' class='panel-collapse in'style='height: auto;'>");
					}else{
						sb.append("<div id='"+j+"' class='panel-collapse collapse'style='height: 0px;'>");
					}
					sb.append("<div class='panel-body'>");
					sb.append("<ul class='nav nav-pills nav-stacked'>");
					for (Step child : childs) {
						sb.append("<li><a class='list-group-item ' href='javascript:void(0)' onclick=\"javascript:changeroom('"+localpath+child.getStepurl()+"?clazz="+child.getClazz()+"',this)\">"+child.getStepname()+"</a></li>");
					}
					sb.append("</ul>");
					sb.append("</div>");
					sb.append("</div>");
				}
				sb.append("</div>");
			}
			sb.append("</div>");
		}
		CacheUtils.put("leftNavigation", sb.toString());
		}else{
			sb.append(CacheUtils.get("leftNavigation"));
		}
		mav.addObject("leftNavigation", sb.toString());
		List<Step> step = (List<Step>) CacheUtils.get("step");
		if(null == step||step.size()==0){
			step =stepService.selectClassByState(1);
			CacheUtils.put("step", step);
		}
		mav.addObject("step", CacheUtils.get("step"));
		return mav;
	}
}
