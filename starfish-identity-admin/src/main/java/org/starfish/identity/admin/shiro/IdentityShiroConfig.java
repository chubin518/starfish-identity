package org.starfish.identity.admin.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
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

/**
 *
 */

public class IdentityShiroConfig {
    @Autowired
    private ConstantPropertyUtils constantPropertyUtils;


    /**
     * 设置过滤规则
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/oauth/login");
        filterFactoryBean.setSuccessUrl("/");
        filterFactoryBean.setUnauthorizedUrl("/oauth/unauth");

        // 注意此处使用的是LinkedHashMap，是有顺序的，shiro会按从上到下的顺序匹配验证，匹配了就不再继续验证
        // 所以上面的url要苛刻，宽松的url要放在下面，尤其是"/**"要放到最下面，如果放前面的话其后的验证规则就没作用了。
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/oauth/login", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/ouath/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");

        return filterFactoryBean;
    }


    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public IdentityAuthorizeRealm shiroRealm(RetryLimitCredentialsMatcher credentialsMatcher) {
        IdentityAuthorizeRealm shiroRealm = new IdentityAuthorizeRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }



    /**
     * 安全管理器
     * @param realms
     * @param cacheManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(List<Realm> realms, CacheManager cacheManager) {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealms(realms);
        return securityManager;
    }


    /**
     * 密码匹配器
     *
     * @return
     */
    @Bean
    public RetryLimitCredentialsMatcher credentialsMatcher(CacheManager cacheManager) {
        RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher(cacheManager);
        credentialsMatcher.setHashAlgorithmName(constantPropertyUtils.getAlgorithmName());
        credentialsMatcher.setHashIterations(constantPropertyUtils.getIterations());
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 缓存
     */
    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return cacheManager;
    }
    /**
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
