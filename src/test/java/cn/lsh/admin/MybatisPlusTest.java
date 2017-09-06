package cn.lsh.admin;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.lsh.Application;
import cn.lsh.admin.entity.Permission;
import cn.lsh.admin.entity.User;
import cn.lsh.admin.service.PermissionService;
import cn.lsh.admin.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class MybatisPlusTest {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		Permission permission=permissionService.selectById(1);
		System.out.println(permission.getName());
	}	
	
	@Test
	public void testPage(){
		Page<User> page=new Page<User>(0, 5);
		Wrapper<User> wrapper=new EntityWrapper<User>();
		wrapper.eq("sex", 1);
		page=userService.selectPage(page, wrapper);
		List<User> users=page.getRecords();
		for(User user:users){
			System.out.println(user.getId());
		}
	}
}
