package cn.lsh.admin.mapper;

import java.util.List;

import cn.lsh.admin.entity.User;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
public interface UserMapper extends BaseMapper<User> {

	public User findByName(String username);
	
	public List<User> selectUserList();
}