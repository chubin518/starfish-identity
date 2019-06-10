package org.starfish.identity.admin.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.starfish.identity.admin.utils.ConstantPropertyUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroSecurityConfig {

    @Autowired
    private ConstantPropertyUtils propertyUtils;

    /**
     * EhCache 配置
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 凭证匹配器
     *
     * @param cacheManager
     * @return
     */
    @Bean
    public RetryLimitCredentialsMatcher retryLimitCredentialsMatcher(CacheManager cacheManager) {
        RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher(cacheManager);
        credentialsMatcher.setHashIterations(propertyUtils.getIterations());
        credentialsMatcher.setHashAlgorithmName(propertyUtils.getAlgorithmName());
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 自定义 Realm
     *
     * @param credentialsMatcher
     * @return
     */
    @Bean
    public Realm realm(CredentialsMatcher credentialsMatcher) {
        IdentityAuthorizeRealm shiroRealm = new IdentityAuthorizeRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    /**
     * 安全管理器
     *
     * @param realms
     * @param cacheManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(List<Realm> realms, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(realms);
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    /**
     * 匹配过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/oauth/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/oauth/403");
        Map<String, String> filters = new LinkedHashMap<>();
        filters.put("/oauth/logout", "logout");
        // anon:所有url都都可以匿名访问
        filters.put("/oauth/login", "anon");
        filters.put("/static/**", "anon");
        filters.put("/favicon.ico", "anon");
        filters.put("/druid/**", "anon");
        //  authc:所有url都必须认证通过才可以访问
        filters.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
