package cn.lsh.admin.service;

import java.util.List;

import cn.lsh.admin.entity.Role;
import cn.lsh.admin.entity.User;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
public interface UserService extends IService<User> {
	
	public User findByName(String username);
	
	public List<Role> findRolePermissions(long uid);
}
