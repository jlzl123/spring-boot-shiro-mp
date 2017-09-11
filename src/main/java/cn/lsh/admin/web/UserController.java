package cn.lsh.admin.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.util.RequestUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import cn.lsh.admin.entity.User;
import cn.lsh.admin.service.UserService;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
@Slf4j
@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequiresPermissions("admin:user:view")//注解验证用户权限，值为用户需要的权限
	@RequestMapping("/index")
	public String index(){
		return "admin/user/list";
	}
	
	@RequiresPermissions("admin:user:view")
	@RequestMapping("/list")
	@ResponseBody                 //采用bootstrap table的自动分页
	public Page<User> list(){//@RequestParam("offset")int offset,@RequestParam("limit")int limit
		Page<User> page= new Page<User>();
		page.setRecords(userService.findUsers());
//		for(User user:page.getRecords()){
//			System.out.println(user.getId());
//		}
//		return userService.selectPage(new Page<User>(offset, limit));
		return page;
	}
	
	@RequestMapping(value="/changeState",method=RequestMethod.POST)
	@ResponseBody
	public String changeState(long id,int state){
		User user=new User();
		user.setId(id);
		user.setState(state);
		boolean flag=userService.updateById(user);
		log.info("修改用户 {} 状态--{}",id,flag);
		return "success";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(User user){
		log.info("修改用户信息:{}",user.toString());
		if(user.getPassword().equals("000000")){
			user.setPassword(null);
		}
		boolean isSucc=userService.updateById(user);
		if(isSucc){
			return "redirect:index";
		}
		return "error";
	}
	
	@RequestMapping("/deleteOne")
	@ResponseBody
	public int deleteOne(long id){
		log.info("删除用户：{}",id);
		boolean isSucc=userService.deleteById(id);
		return isSucc?1:0;
	}
	
	@RequestMapping("/add")
	public String add(User user){
		log.info("添加用户：{}",user);
		boolean isSucc=user.insert();
		return isSucc?"redirect:index":"error";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, List<User>> test(){
		List<User> users=userService.findUsers();
		for(User user:users){
			System.out.println(user.getId());
		}
		Map<String, List<User>> map=new HashMap<String, List<User>>();
		map.put("records", users);
		return map;
	}
}
