package com.gov.controller;



import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gov.model.Guest;
import com.gov.model.Room;
import com.gov.service.GuestService;
import com.gov.service.RoomService;
import com.gov.util.CacheUtils;
import com.gov.util.CompareTime;
import com.gov.util.service.RoomSate;

@Controller
public class GuestController {
	@Resource
	private RoomService roomService;
	@Resource
	private GuestService guestService;
	@Resource
	private RoomSate roomSate;
	@Resource
	public CompareTime compareTime;
	
	@RequestMapping(value="/back/room/roomguest.do",method=RequestMethod.GET)
	public ModelAndView roomguest(String roomsnum,Integer roomid,Double roomcash){
		ModelAndView mav = new ModelAndView();
		List<Room> list = roomService.selectByPrimaryKey(roomid);
		mav.addObject("roomcash", roomcash);
		switch(list.get(0).getRoomstate()){
			case 0 :
				mav.setViewName("/back/roomguest");
				break;
			case 1 ://空闲
				mav.setViewName("/back/roomguest");
				break;
			case 2 ://已入住
				Guest guest = guestService.selectHotelGuestByRoomid(roomid);
				if(null!=guest){
					mav.setViewName("/back/roomExitGuest");
					mav.addObject("guest",guest);
				}else{
					mav.setViewName("/back/roomguest");
				}
				break;
			case 3 ://该时间段有预约
				mav.setViewName("/back/noRoomGuest");
				break;
			default:
				break;
		
		}
		mav.addObject("roomsnum",roomsnum);
		mav.addObject("roomid",roomid);
		mav.addObject("roomstate",list.get(0).getRoomstate());
		mav.addObject("footer", CacheUtils.get("footer"));
		mav.addObject("title", CacheUtils.get("title"));
		return mav;
	}
	
	@RequestMapping(value="/back/room/guestinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public String[] guestinfo(Guest guest){
		String[] strArray = null;
		if(null!=guest.getGuestidcard()&&guest.getGuestidcard()!=""){
			strArray = guestService.insert(guest);
		}else{
			strArray = new String[4];
			strArray[0]="-1";
		}
		return strArray;
	}

}
