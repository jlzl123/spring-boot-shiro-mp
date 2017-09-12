package cn.lsh.admin.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.mysql.fabric.xmlrpc.base.Array;

import cn.lsh.admin.entity.Role;
import cn.lsh.admin.service.RoleService;

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
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequiresPermissions("admin:role:view")
	@RequestMapping("/index")
	public String index(){
		return "admin/role/list";
	}
	
	@RequiresPermissions("admin:role:view")
	@RequestMapping("/list")
	@ResponseBody
	public Page<Role> list(){
		Page<Role> page=roleService.selectPage(new Page<Role>(0,12));
		return page;
	}
	
//	@RequiresPermissions("admin:role:create")
	@RequestMapping("/add")
	public String add(Role role){
		boolean isSucc=role.insert();
		if(isSucc){
			return "redirect:index";
		}
		return "error";
	}
	
//	@RequiresPermissions("admin:role:update")
	@RequestMapping("/update")
	public String update(Role role){
		boolean isSucc=role.updateById();
		if(isSucc){
			return "redirect:index";
		}
		return "error";
	}
	
	@RequestMapping("/deleteList")
	@ResponseBody
	public boolean deleteLsit(int[] roleIds){
		log.info("批量删除角色:{}",roleIds);
		List<Integer> idList=new ArrayList<Integer>();
		for(int id:roleIds){
			idList.add(id);
		}
		boolean isSecc=roleService.deleteBatchIds(idList);
		return isSecc;
	}
}
