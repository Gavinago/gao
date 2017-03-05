package com.gov.util.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gov.model.Guest;
import com.gov.service.GuestService;
import com.gov.util.CompareTime;
import com.gov.util.exception.TimeException;
import com.gov.util.service.RoomSate;
@Service
public class RoomSateImpl implements RoomSate {
	@Resource
	private GuestService guestService;
	@Resource
	public CompareTime compareTime;
	@Override
	public Guest checkRoomState (Integer roomid,String comeTime, String leaveTime) {
		long come = compareTime.getTimeMillisecond(comeTime);
		long leave = compareTime.getTimeMillisecond(leaveTime);
		Guest guest1 = null;
		if(come<leave){
		List<Guest> list = guestService.selectByRoomId(roomid);
		for (Guest guest : list) {
			if(guest.getGuestcometime()!=null&&guest.getGuestcometime()!=""){
				if(guest.getGuestleavetime()!=null&&guest.getGuestleavetime()!=""){
					
					long guestcome = compareTime.getTimeMillisecond(guest.getGuestcometime());
					long guestleave = compareTime.getTimeMillisecond(guest.getGuestleavetime());
					if(come<guestcome&&leave<guestcome){
						
					}else if(come>=guestleave){
						
					}else{
						guest1 = guest;
						return guest;
					};
				}
			}
			
		}
		}else{
			throw new TimeException();
		}
		return guest1;
	}

}
