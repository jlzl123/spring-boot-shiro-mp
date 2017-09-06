package cn.lsh.admin.web;


import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import cn.lsh.admin.entity.User;
import cn.lsh.admin.service.UserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
@Slf4j
@Controller
public class AuthController {
	
	@RequestMapping(value="/login")
	public String login(User user){
		if(user.getUsername()!=null && user.getPassword()!=null){
			UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword(), "login");
			Subject currentUser=SecurityUtils.getSubject();
			log.info("对用户[{}]进行登录验证...验证开始",user.getUsername());
			try {
				currentUser.login(token);
				if(currentUser.isAuthenticated()){
					log.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
					return "redirect:/";
				}else{
					token.clear();
					return "redirect:/login";
				}
			} catch (UnknownAccountException e) {
				// TODO: handle exception
	
				log.error("对用户[" + user.getUsername() + "]进行登录验证..验证失败-未知用户"+e.getMessage());
			} catch (IncorrectCredentialsException e) {
				// TODO: handle exception
				log.error("对用户[" + user.getUsername() + "]进行登录验证..验证失败-密码不匹配");
			} catch (LockedAccountException e) {
				// TODO: handle exception
				log.error("对用户[" + user.getUsername() + "]进行登录验证..验证失败-用户被锁定");
			} catch (AuthenticationException e) {
				// TODO: handle exception
				log.error(e.getMessage());
			}
		}
		return "login";
	}
	
}
