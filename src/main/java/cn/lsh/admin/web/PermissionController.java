package cn.lsh.admin.web;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.baomidou.mybatisplus.plugins.Page;

import cn.lsh.admin.entity.Permission;
import cn.lsh.admin.service.PermissionService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
@Controller
@RequestMapping("/admin/perm")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	
	@RequiresPermissions("admin:perm:view")
	@RequestMapping("/index")
	public String index(){
		return "admin/perm/list";
	}
	
	@RequiresPermissions("admin:perm:view")
	@RequestMapping("/list")
	@ResponseBody
	public Page<Permission> list(){
		Page<Permission> page=permissionService.selectPage(new Page<Permission>(0,12));
		return page;
	}
	
}
