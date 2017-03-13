package com.gov.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gov.common.auth.ShiroSessionHelper;
import com.gov.common.service.TUserLogService;
import com.gov.dao.GuestMapper;
import com.gov.model.Guest;
import com.gov.model.Room;
import com.gov.model.TUserLog;
import com.gov.service.GuestService;
import com.gov.service.RoomService;
import com.gov.util.CompareTime;
import com.gov.util.exception.ComeTimeWException;
import com.gov.util.service.RoomSate;
@Service
public class GuestServiceImpl implements GuestService {
	@Resource
	private GuestMapper guestMapper;
	@Resource
	private CompareTime compareTime;
	@Resource
	private TUserLogService tUserLogService;
	@Resource
	private RoomService roomService;
	@Resource
	private RoomSate roomSate;
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public PageInfo<Guest> selectByguestidcard(Integer pageNum, Integer pageSize,String guestidcard) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Guest> list = guestMapper.selectByguestidcard(guestidcard);
		//将结果用分页结果包装
		PageInfo<Guest> pagelist = new PageInfo<Guest>(list);
		return pagelist;
	}

	@Override
	public PageInfo<Guest> selectbySearch(Integer pageNum, Integer pageSize, String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Guest> selectbyTime(Integer pageNum, Integer pageSize, String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] insert(Guest guest)throws ComeTimeWException{
		int i=0;
		String[] strArray = new String[4];
		String str2 = compareTime.getCurrentTime();
		double time = compareTime.comparefarTime(guest.getGuestcometime(),str2);
		if(time>=-1){
			/**
			  * 添加room是否已预约判断信息
			  */
			Guest guest1 = roomSate.checkRoomState(guest.getGuestroomid(), guest.getGuestcometime(), guest.getGuestleavetime());
			TUserLog tuserlog = (TUserLog) ShiroSessionHelper.getAttribute("tuserlog");
			if(null==guest1){
				guest.setGueststate(2);
				i = guestMapper.insert(guest);
				strArray[0]=String.valueOf(i);
				roomService.updateRoomStateByRoomid(2, guest.getGuestroomid());
			if(tuserlog!=null){
				tuserlog.setOperation("接待  "+guest.getGuestname()+" : "+guest.getGuestidcard()+" 客户入住");
				tuserlog.setOperationTime(new Date());
				tUserLogService.insert(tuserlog);
			}
			}else{
				if(null==guest1.getGuestidcard()||guest1.getGuestidcard()==""||guest1.getGuestidcard().equals(guest.getGuestidcard())){
					roomService.updateRoomStateByRoomid(2, guest.getGuestroomid());
					if(tuserlog!=null){
						tuserlog.setOperation("接待  "+guest.getGuestname()+" : "+guest.getGuestidcard()+" 客户入住");
						tuserlog.setOperationTime(new Date());
						tUserLogService.insert(tuserlog);
					}
					guest1.setGueststate(2);
					guestMapper.updateGuestInfoByGuestid(guest1);
					strArray[0]="1";
			}else{
				logger.warn("此时间段 {} 房已有人预约",guest.getGuestroomname());
				strArray[1]="此时间段"+guest.getGuestroomname()+"房已有人预约";
			}
			}
		}else{
			logger.warn("{}用户入住失败",guest.getGuestname());
			strArray[2]="入住时间不能在当前时间之前";
		}
		return strArray;
	}

	@Override
	public List<Guest> selectByRoomId(Integer guestroomid) {
		List<Guest> list = guestMapper.selectByRoomId(guestroomid);
		return list;
	}

	@Override
	public PageInfo<Guest> selectByRoomName(String guestroomname,Integer pageNum, Integer pageSize) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Guest> list = guestMapper.selectByRoomName(guestroomname);
		//将结果用分页结果包装
		PageInfo<Guest> pagelist = new PageInfo<Guest>(list);
		return pagelist;
	}

	@Override
	public List<Guest> selectByRoomidAndCometime(Integer guestroomid, String guestcometime) {
		List<Guest> list = guestMapper.selectByRoomidAndCometime(guestroomid,guestcometime);
		return list;
	}

	@Override
	public int updateGuestStateByGuestid(Integer gueststate, Integer guestid) {
		int i = guestMapper.updateGuestStateByGuestid(gueststate,guestid);
		return 0;
	}

	@Override
	public int updateGuestInfoByGuestid(Guest guest) {
		int i = guestMapper.updateGuestInfoByGuestid(guest);
		return i;
	}

	@Override
	public Guest selectHotelGuestByRoomid(Integer guestroomid) {
		Guest guest = guestMapper.selectHotelGuestByRoomid(guestroomid);
		return guest;
	}

	@Override
	public PageInfo<Guest> selectbyRelationshipcomeTime(Integer pageNum, Integer pageSize,String guestcometime,String searchText) {
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Guest> list = guestMapper.selectbyRelationshipcomeTime(guestcometime,searchText);
		//将结果用分页结果包装
		PageInfo<Guest> pagelist = new PageInfo<Guest>(list);
		return pagelist;
	}
	@Override
	public PageInfo<Guest> selectGuestBookRoom(Integer pageNum, Integer pageSize,String searchText,String orderBy){
		//之前加入分页器
		PageHelper.startPage(pageNum, pageSize);
		List<Guest> list = guestMapper.selectGuestBookRoom(searchText, orderBy);
		//将结果用分页结果包装
		PageInfo<Guest> pagelist = new PageInfo<Guest>(list);
		return pagelist;
	}

	@Override
	public List<Guest> selectGuestByGuestid(Integer guestid) {
		List<Guest> list = guestMapper.selectGuestByGuestid(guestid);
		return list;
	}

	@Override
	public int deleteGuestByGuestid(Integer guestid) {
		int i = guestMapper.deleteGuestByGuestid(guestid);
		return i;
	}
	
	
	
	
	
	
	
	

}
