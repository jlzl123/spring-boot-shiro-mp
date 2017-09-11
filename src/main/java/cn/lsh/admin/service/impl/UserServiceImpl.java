package cn.lsh.admin.service.impl;

import java.util.List;
import java.util.Set;

import cn.lsh.admin.entity.Role;
import cn.lsh.admin.entity.User;
import cn.lsh.admin.mapper.RolePermissionMapper;
import cn.lsh.admin.mapper.UserMapper;
import cn.lsh.admin.mapper.UserRoleMapper;
import cn.lsh.admin.service.UserService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByName(username);
	}

	@Override
	public List<Role> findRolePermissions(long uid) {
		// TODO Auto-generated method stub
        List<Role> roles=userRoleMapper.findRoleListByUserId(uid);
		for(Role role:roles){
			Set<String> everyRolePer=rolePermissionMapper.findPermissions(role.getId());
			role.setPerNameSet(everyRolePer);
		}
		return roles;
	}

	@Override
	public List<User> findUsers() {
		// TODO Auto-generated method stub
		return userMapper.selectUserList();
	}
	
}
