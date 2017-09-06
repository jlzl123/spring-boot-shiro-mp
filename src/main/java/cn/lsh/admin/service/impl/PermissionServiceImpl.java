package cn.lsh.admin.service.impl;

import cn.lsh.admin.entity.Permission;
import cn.lsh.admin.mapper.PermissionMapper;
import cn.lsh.admin.service.PermissionService;

import com.baomidou.mybatisplus.plugins.Page;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Page<Permission> selectPermPage(Page<Permission> page) {
		// TODO Auto-generated method stub
		page.setRecords(permissionMapper.selectPermList(page));
		return page;
	}
	
}
