package cn.lsh.admin.mapper;

import java.util.List;

import cn.lsh.admin.entity.Permission;

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
public interface PermissionMapper extends BaseMapper<Permission> {
    /*
     * 父类BaseMapper中定义了一系列crud操作，当然初次之外，也可以自己定义，如下，并在xml中配置sql语句
     */
	
	//可以自己定义查询方法
	public List<Permission> selectPermList(Page<Permission> page);
}