package cn.lsh.common.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.lsh.common.shiro.ShiroRealm;

@Configuration
public class ShiroConfiguration {

//	Shiro生命周期处理器
	@Bean(name="lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}

	//自定义密码比较器
//	@Bean(name="hashedCredentialsMatcher")
//	public HashedCredentialsMatcher hashedCredentialsMatcher(){
//		HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
//		credentialsMatcher.setHashAlgorithmName("MD5");
//		credentialsMatcher.setHashIterations(2);
//		credentialsMatcher.setStoredCredentialsHexEncoded(true);
//		return credentialsMatcher;
//	}
	
	//配置自定义的权限登录器
	@Bean(name="shiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")//使用depends-on属性指定的Bean要先初始化完毕后才初始化当前Bean
	public ShiroRealm shiroRealm(){
		ShiroRealm shiroRealm= new ShiroRealm();
//		shiroRealm.setCredentialsMatcher(credentialsMatcher);加入自定义的密码比较器
		return shiroRealm;
	}
	
	//配置缓存管理器
	@Bean(name="ehCacheManager")
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager(){
		return new EhCacheManager();
	}
	
	//配置核心安全事务管理器
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager(){
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setCacheManager(ehCacheManager());
	   return securityManager;
	}
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager
			securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//配置登录的url和登录成功的url
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面  
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		//配置访问权限，必需为有序的LinkedHashMap
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//配置过滤器，过滤链定义，从上向下顺序执行，只要匹配中了，就不匹配了,一般将 /**放在最为下边
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/user/**", "authc,roles[user]");//表示需要认证才可以访问
		filterChainDefinitionMap.put("/shop/**", "authc,roles[shop]");
		filterChainDefinitionMap.put("/admin/**", "authc,roles[admin]");
		filterChainDefinitionMap.put("/", "authc");
		filterChainDefinitionMap.put("/**", "anon");//表示可以匿名访问
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	//DefaultAdvisorAutoProxyCreator实现自动创建Proxy
	@Bean
	@ConditionalOnMissingBean//在容器加载它作用的bean时，检查容器中是否存在目标类型（ConditionalOnMissingBean注解的value值）的bean了，如果存在这跳过原始bean的BeanDefinition加载动作。
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultSecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	//ShiroDialect，为了在thymeleaf里使用shiro的标签的bean 
	@Bean(name="shiroDialect")
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}
}
