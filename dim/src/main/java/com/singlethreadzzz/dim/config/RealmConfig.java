package com.singlethreadzzz.dim.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import com.singlethreadzzz.dim.realm.AdminRealm;

@Configuration
public class RealmConfig {
	
	@Autowired
	private AdminRealm adminRealm;
	
	//设置自定义加密方式
    @Bean
    public HashedCredentialsMatcher adminHashedCredentialsMatcher() {
        HashedCredentialsMatcher adminHashedCredentialsMatcher = new HashedCredentialsMatcher();
        adminHashedCredentialsMatcher.setHashAlgorithmName("SHA-256");//散列算法:MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512等，此处应与新增用户的加密方式一致。
        adminHashedCredentialsMatcher.setHashIterations(2);//散列的次数，默认1次， 设置两次相当于 md5(md5(""))，此处应与新增用户的加密方式一致。
        return adminHashedCredentialsMatcher;
    }
	
    //将自己的验证方式加入realm
    @Bean
    public ArrayList<Realm> adminRealms(HashedCredentialsMatcher adminHashedCredentialsMatcher) {	
    	this.adminRealm.setCredentialsMatcher(adminHashedCredentialsMatcher);
    	ArrayList<Realm> adminRealms = new ArrayList<Realm>();
    	adminRealms.add(adminRealm);
        return adminRealms;
    }
    /*
     * 将自定义的登录认证realm加入adminModularRealmAuthenticator
     * 
     * FirstSuccessfulStrategy：只要有一个Realm 验证成功即可，只返回第一个Realm 身份验证成功的认证信息，其他的忽略
　　      * AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，将返回所有Realm身份验证成功的认证信息
　　      * AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了
     */
    @Bean
    public ModularRealmAuthenticator adminModularRealmAuthenticator(ArrayList<Realm> adminRealms) {
    	ModularRealmAuthenticator adminModularRealmAuthenticator = new ModularRealmAuthenticator();
    	adminModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
    	adminModularRealmAuthenticator.setRealms(adminRealms);
		return adminModularRealmAuthenticator;
    }
    
    /*
     * 将自定义的权限认证realm加入adminModularRealmAuthorizer
     * 
     */
    @Bean
    public ModularRealmAuthorizer adminModularRealmAuthorizer(ArrayList<Realm> adminRealms) {
    	ModularRealmAuthorizer adminModularRealmAuthorizer = new ModularRealmAuthorizer();
    	adminModularRealmAuthorizer.setRealms(adminRealms);
		return adminModularRealmAuthorizer;
    }
    
    //将自定义的Authenticator加入SecurityManager
    @Bean
    public SecurityManager adminSecurityManager(ModularRealmAuthenticator adminModularRealmAuthenticator, ModularRealmAuthorizer adminModularRealmAuthorizer) {
    	DefaultWebSecurityManager adminSecurityManager = new DefaultWebSecurityManager();
    	adminSecurityManager.setAuthenticator(adminModularRealmAuthenticator);
    	adminSecurityManager.setAuthorizer(adminModularRealmAuthorizer);
        return adminSecurityManager;
    }
    
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean adminShiroFilterFactoryBean(SecurityManager adminSecurityManager) {
        ShiroFilterFactoryBean adminShiroFilterFactoryBean = new ShiroFilterFactoryBean();
        adminShiroFilterFactoryBean.setSecurityManager(adminSecurityManager);
        LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();

        //登出
        map.put("/logout","logout");
        
        //无需认证的地址   
        map.put("/login","anon");
        map.put("/static/css","anon");
        map.put("/static/js","anon");
        map.put("/static/image","anon");
        
        //需要权限的地址
//        map.put("/addUser","roles[admin]");
        
        //默认拦截全部地址
        map.put("/**","authc");
        
        //登录
        adminShiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        adminShiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        adminShiroFilterFactoryBean.setUnauthorizedUrl("/login");
        adminShiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return adminShiroFilterFactoryBean;
    }
    
    //配置Shiro Spring AOP权限注解支持 有此配置可以在java类中使用注解判断权限
    @Bean
    public AuthorizationAttributeSourceAdvisor adminAuthorizationAttributeSourceAdvisor(SecurityManager adminShiroFilterFactoryBean) {
        AuthorizationAttributeSourceAdvisor adminAuthorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        adminAuthorizationAttributeSourceAdvisor.setSecurityManager(adminShiroFilterFactoryBean);
        return adminAuthorizationAttributeSourceAdvisor;
    }

}
