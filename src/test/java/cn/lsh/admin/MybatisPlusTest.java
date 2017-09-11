package cn.lsh.admin;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.lsh.Application;
import cn.lsh.admin.entity.Permission;
import cn.lsh.admin.entity.Role;
import cn.lsh.admin.entity.User;
import cn.lsh.admin.mapper.RolePermissionMapper;
import cn.lsh.admin.service.PermissionService;
import cn.lsh.admin.service.UserService;
import cn.lsh.admin.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class,MockServletContext.class})
public class MybatisPlusTest {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	private MockMvc mockMvc;
	
	@Before
	public void before(){
		mockMvc=MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	
//	@Test
	public void test(){
		Permission permission=permissionService.selectById(1);
		System.out.println(permission.getName());
	}	
	
//	@Test
	public void testPage(){
		//第一个参数为当前页码，第二个参数该页数据条数
		Page<User> page=new Page<User>(0,5);
		Wrapper<User> wrapper=new EntityWrapper<User>();
		wrapper.eq("sex", 1);
		page=userService.selectPage(page, wrapper);
		List<User> users=page.getRecords();
		for(User user:users){
			System.out.println(user.getId());
		}
		System.out.println(userService.selectPage(new Page<User>()).getSize());
	}
	
//	@Test
	public void testUerService(){
		List<Role> roles=userService.findRolePermissions(837135380675952641L);
		for(Role role:roles){
			System.out.println(role.toString());
		}
	}
	
//	@Test
	public void testRolePermissionMapper(){
		Set<String> permNameSet=rolePermissionMapper.findPermissions(1);
		Iterator<String> iterator=permNameSet.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	
	@Test
	public void testDataChange() throws Exception{
		RequestBuilder request=MockMvcRequestBuilders.post("/admin/user/test");
		ResultActions result=mockMvc.perform(request);
		Map<String, Object> map=result.andReturn().getModelAndView().getModel();
		List<User> users=(List<User>) map.get("records");
		for(User user:users){
			System.out.println(user.getId());
		}
	}
}
