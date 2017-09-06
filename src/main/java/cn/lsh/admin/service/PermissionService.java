package cn.lsh.admin.service;

import cn.lsh.admin.entity.Permission;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
public interface PermissionService extends IService<Permission> {
	
	public Page<Permission> selectPermPage(Page<Permission> page);
}
