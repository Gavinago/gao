package net.guanzhuo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.guanzhuo.model.TUser;
import net.guanzhuo.model.TUserTables;
import net.guanzhuo.service.TUserTablesService;

@Controller
public class TUserTablesController {
	@Resource
	private TUserTablesService TUserTablesService;
	@RequestMapping(value="TUserTables.do")
	public ModelAndView selectByPrimaryKey(Integer userid){
		ModelAndView  mav = new ModelAndView();
		List<TUserTables>list = TUserTablesService.selectByPrimaryKey(userid);
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="updateusertable.do",method=RequestMethod.POST)
	@ResponseBody
	public String updateusertable(Integer userid,String str){
		int j =0;
		List<TUserTables>list = TUserTablesService.selectByPrimaryKey(userid);
		System.out.println("list.size()---"+list.size());
		if(list.size()>0){
			//插入前先判断数据库中是否存在存在则删除
			TUserTablesService.deleteUser(userid);
		}else{
			
		}
		String[] ss = str.split("[,]");
		TUserTables tuser  = new TUserTables();
		tuser.setUserid(userid);
		for(int i=0;i<ss.length;i++){
			tuser.setTableid(Integer.valueOf(ss[i]));
			System.out.println("插入----"+tuser);
			j++;
			//写入数据库
			TUserTablesService.insertUser(tuser);
		}
		return String.valueOf(j);
	}
	@RequestMapping(value="updataUserTime.do",produces = "text/html;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updataUserTime(Integer userid){
		List<TUserTables>list = TUserTablesService.selectByPrimaryKey(userid);
		if(list.size()>0){
			String timeparam =null;
			TUserTablesService.updataUserTime(userid, timeparam);
			return "同步时间重置成功!";
		}else{
			return "该用户还没有同步时间!";
		}
	}
}
