package cn.lsh.admin.mapper;

import java.util.List;

import cn.lsh.admin.entity.Role;
import cn.lsh.admin.entity.UserRole;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

	public List<Role> findRoleListByUserId(long uid);
	
}