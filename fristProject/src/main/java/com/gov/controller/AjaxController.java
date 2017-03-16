package com.gov.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.gov.model.Guest;
import com.gov.model.RoomStateView;
import com.gov.service.GuestService;
import com.gov.service.RoomStateViewService;
import com.gov.util.CompareTime;
import com.gov.util.Constant;
import com.gov.util.ParamHelper;
import com.gov.util.ParamPage;





@Controller
public class AjaxController {
	@Resource
	private RoomStateViewService roomStateViewService;
	@Resource
	private GuestService guestService;
	@Resource
	private CompareTime compareTime;
	
	@RequestMapping(value="/ajax/selectAllRoom",method=RequestMethod.GET)
	public ModelAndView allroom(ParamPage param,Integer clazz){
		clazz = Integer.valueOf(String.valueOf(clazz).substring(2));
		ModelAndView mav = new ModelAndView("nest/allroom");
		ParamHelper.NormalizePage(param);
		PageInfo<RoomStateView> pageinfo = roomStateViewService.selectByRoomclass(param.getPageNum(),param.getPageSize(),clazz,param.getSearchText());
//		mav.addObject("RoomStateView", list);
		mav.addObject(Constant.MODEL_KEY_PAGEINFO, pageinfo);
		mav.addObject(Constant.MODEL_KEY_PARAMPAGE, param);
		return mav;
	}
	@RequestMapping(value="/ajax/checkinRoominfo",method=RequestMethod.GET)
	public ModelAndView checkinRoom(ParamPage param,Integer clazz){
		ParamHelper.NormalizePage(param);
		ModelAndView mav = new ModelAndView();
		clazz = Integer.valueOf(String.valueOf(clazz).substring(2));
		switch(clazz){
			case 1 :
				mav.setViewName("nest/allroom");
				mav = this.allroomByclazz(mav,param,clazz);
				break;
			case 2 :
				mav.setViewName("nest/allroom");
				mav = this.allroomByclazz(mav,param,clazz);
				break;
			case 4 :
				mav.setViewName("nest/guestBookRoom");
				String orderBy ="ASC";
				mav = this.GuestBookRoom(mav, param,orderBy);
				break;
		}
		mav.addObject(Constant.MODEL_KEY_PARAMPAGE, param);
		return mav;
	} 
	@RequestMapping(value="/ajax/ReportRoominfo",method=RequestMethod.GET)
	public ModelAndView ReportRoomInfo(ParamPage param,Integer clazz){
		ParamHelper.NormalizePage(param);
		ModelAndView mav = new ModelAndView("nest/checkinRoominfo");
		clazz = Integer.valueOf(String.valueOf(clazz).substring(2));
		switch(clazz){
		case 30 :
			mav = selectbyRelationshipcomeTime(mav,param.getPageNum(),param.getPageSize(),compareTime.BeforeTheTime(clazz),param.getSearchText());
			break;
		case 90 :
			mav = selectbyRelationshipcomeTime(mav,param.getPageNum(),param.getPageSize(),compareTime.BeforeTheTime(clazz),param.getSearchText());
			break;
		case 365 :
			mav = selectbyRelationshipcomeTime(mav,param.getPageNum(),param.getPageSize(),compareTime.BeforeTheTime(clazz),param.getSearchText());
			break;
	}
		mav.addObject(Constant.MODEL_KEY_PARAMPAGE, param);
		return mav;
	}
	@RequestMapping(value="/ajax/ajaxBookRoomOperation",method=RequestMethod.GET)
	public ModelAndView ajaxBookRoomOperation(Integer guestid,String action,ParamPage param){
		ModelAndView mav = new ModelAndView();
		if(guestid.equals(0)){
			mav.setViewName("nest/guestBookRoom");
			mav = this.GuestBookRoom(mav, param,action);
		}else{
			if(action.equals("checkin")){
				mav = this.BookGuestCheckin(mav, param, guestid);
				mav.setViewName("/back/bookGuestcheckinRoominfo");
			}else if(action.equals("exit")){
				this.bookGuestExit(mav, param, guestid);
			}
			
		}
		mav.addObject(Constant.MODEL_KEY_PARAMPAGE, param);
		return mav;
	}
	@RequestMapping(value="/back/BookRoomOperation",method=RequestMethod.GET)
	public ModelAndView BookRoomOperation(Integer guestid,ParamPage param){
		ModelAndView mav = new ModelAndView();
		mav = this.BookGuestCheckin(mav, param, guestid);
		mav.setViewName("/back/bookGuestcheckinRoominfo");
		mav.addObject(Constant.MODEL_KEY_PARAMPAGE, param);
		return mav;
	}
	@RequestMapping(value="/ajax/ajaxExitRoomSelect",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView exitRoomSelect(Integer guestid){
		ModelAndView mav = new ModelAndView("/nest/exitRoomSelect");
		List<Guest> list = guestService.selectGuestByGuestid(guestid,2);
		Guest guest = list.get(0);
		String cometime = guest.getGuestcometime();
		long come = compareTime.getTimeMillisecond(cometime);
		mav.addObject("order","XS"+come+"L00"+guest.getGuestid());
		mav.addObject("guest", guest);
		return mav;
	}
	public ModelAndView selectbyRelationshipcomeTime(ModelAndView mav,Integer pageNum,Integer pagesize,String compareTime,String searchText){
		PageInfo<Guest> pageinfo = guestService.selectbyRelationshipcomeTime(pageNum,pagesize, compareTime,searchText);
		mav.addObject(Constant.MODEL_KEY_PAGEINFO, pageinfo);
		return mav;
	}
	public ModelAndView allroomByclazz(ModelAndView mav,ParamPage param,Integer state){
		PageInfo<RoomStateView> pageinfo = roomStateViewService.selectByRoomState(param.getPageNum(),param.getPageSize(),state,param.getSearchText());
		mav.addObject(Constant.MODEL_KEY_PAGEINFO, pageinfo);
		return mav;
	}
	public ModelAndView GuestBookRoom(ModelAndView mav,ParamPage param,String orderBy){
		PageInfo<Guest> pageinfo = guestService.selectGuestBookRoom(param.getPageNum(),param.getPageSize(), param.getSearchText(), orderBy);
		mav.addObject(Constant.MODEL_KEY_PAGEINFO, pageinfo);
		return mav;
	}
	public ModelAndView BookGuestCheckin(ModelAndView mav,ParamPage param,Integer guestid){
		List<Guest> list = guestService.selectGuestByGuestid(guestid,1);
		if(list.size()>0)
		mav.addObject("guest",list.get(0));
		return mav;
	}
	public ModelAndView bookGuestExit(ModelAndView mav,ParamPage param,Integer guestid){
		guestService.updateGuestStateByGuestid(4, guestid);
		return mav;
	}
	
}
