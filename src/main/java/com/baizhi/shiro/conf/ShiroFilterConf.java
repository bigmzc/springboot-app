package com.baizhi.shiro.conf;

import com.baizhi.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroFilterConf {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiro会对所有资源进行控制,默认不拦截  需要配置
        Map<String, String> map = new HashMap<>();
        //多个过滤器  AnonymousFilter  匿名过滤器   anon
        // FormAuthenticationFilter 认证过滤器 authc
        map.put("/**", "authc");
        map.put("/admin/*", "anon");
        map.put("/index.jsp", "anon");
        map.put("/img/createImg", "anon");
        map.put("/script/*", "anon");
        map.put("/css/*", "anon");
        //多个过滤器组成过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置认证页面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager(Realm realm, CacheManager cacheManager) {
        //web环境下securityManage的实现类为DefaultWebSecurityManager
        SecurityManager securityManager = new DefaultWebSecurityManager();
        ((DefaultWebSecurityManager) securityManager).setRealm(realm);
        ((DefaultWebSecurityManager) securityManager).setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public Realm getRealm(CredentialsMatcher credentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    @Bean
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hm = new HashedCredentialsMatcher();
        hm.setHashAlgorithmName("MD5");
        hm.setHashIterations(1024);
        return hm;
    }

    @Bean
    public CacheManager getCacheManager() {
        CacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
}
