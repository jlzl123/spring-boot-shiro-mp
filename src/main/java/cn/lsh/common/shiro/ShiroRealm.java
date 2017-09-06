package cn.lsh.common.shiro;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lsh.admin.entity.Role;
import cn.lsh.admin.entity.User;
import cn.lsh.admin.service.UserService;

@Slf4j
public class ShiroRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	//权限认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		log.info("##################执行Shiro权限认证##################");
		User user= (User) principal.getPrimaryPrincipal();
		//用户的角色集合
		if(user!=null){
			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
			for(Role role : user.getRoleList()){
				info.addRole(role.getName());//添加用户角色
				info.addStringPermissions(role.getPerNameSet());//添加角色对应的权限
			}
			return info;
		}
		return null;
	}

	//登录认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
		log.info("验证当前Subject时获取到token为：{}",ReflectionToStringBuilder.toString(token,ToStringStyle.MULTI_LINE_STYLE));
		User hasUser=userService.findByName(token.getUsername());
		if(hasUser!=null){
			List<Role> roles=userService.findRolePermissions(hasUser.getId());
			hasUser.setRoleList(roles);
			return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(), getName());
		}
		return null;
	}

}
