package cn.lsh.admin.web;


import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.baomidou.mybatisplus.plugins.Page;

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
}
