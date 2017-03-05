package net.guanzhuo.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.guanzhuo.model.SynsTables;
import net.guanzhuo.model.TUser;
import net.guanzhuo.model.TUserTables;
import net.guanzhuo.service.SynsTablesService;
import net.guanzhuo.service.TUserService;
import net.guanzhuo.service.TUserTablesService;

@Controller
public class TUserController {
	@Resource
	private TUserService tUserService;
	@Resource
	private SynsTablesService synsTableService ;
	@Resource
	private TUserTablesService tUserTablesService;
	@RequestMapping(value="selectUser.do")
	@ResponseBody
	public List<TUser> selectByPrimaryKey(Integer userid ){
		List<TUser>list = tUserService.selectByPrimaryKey(userid);
		System.out.println(list);
		return list;
	}
	@RequestMapping(value="selectAllUser.do")
	public ModelAndView selectAllUser(){
		ModelAndView mav = new ModelAndView("index");
		List<TUser>list = tUserService.selectAllUser();
		mav.addObject("list", list);
		return mav;
	}
	@RequestMapping(value="updateUser.do",method=RequestMethod.POST)
	public String updataUser(TUser tUser){
		tUserService.updateUser(tUser);
		System.out.println("更改用户------"+tUser);
		return "redirect:selectAllUser.do";
	}
	@RequestMapping(value="addUser.do",method=RequestMethod.GET)
	public ModelAndView addUserreturn(){
		ModelAndView mav = new ModelAndView("adduser");
		return mav;
	}
	@RequestMapping(value="addUser.do",method=RequestMethod.POST)
	public String addUser(TUser tUser){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tUser.setLasttime(sdf.format(new Date()));
		tUserService.addUser(tUser);
		System.out.println("添加用户----"+tUser);
		return "redirect:selectAllUser.do";
	}
	@RequestMapping(value="selectUserform.do" ,method=RequestMethod.GET)
	public ModelAndView selectUserform(Integer userid){
		List<SynsTables> list = synsTableService.selectAll();
		List<TUserTables> usertable = tUserTablesService.selectByUserTable(userid);
		ModelAndView mav = new ModelAndView("userform");
		mav.addObject("listtable", list);
		mav.addObject("usertable", usertable);
		mav.addObject("userid", userid);
		return mav;
				
		
	}
	@RequestMapping(value="deleteUser.do",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(Integer userid){
		if(!(userid==1||userid==101||userid==102||userid==103||userid==1001||userid==2001||userid==610902||userid==6109022)){
			List<TUserTables> list = tUserTablesService.selectByPrimaryKey(userid);
			if(list.size()>0){
				tUserTablesService.deleteUser(userid);
			}
			tUserService.deleteUser(userid);
			return "删除用户 "+userid+" 成功";
		}else{
			
			return "原始用户不能删除 ！";
		}
	}
}
