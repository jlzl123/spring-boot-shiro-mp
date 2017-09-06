package cn.lsh.admin.mapper;

import java.util.Set;

import cn.lsh.admin.entity.RolePermission;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	public Set<String> findPermissions(long roleId);
	
}