package org.starfish.identity.admin.shirojwt;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Configuration
public class JwtAuthorizingConfig {
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

    @Bean
    public Realm realm() {
        return new JwtAuthorizingRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(List<Realm> realms, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealms(realms);
        // 关闭自带session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/jwt/jwtlogin");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/jwt/403");
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> filterRules = new LinkedHashMap<>();
        filterRules.put("/jwt/logout", "logout");
        // anon:所有url都都可以匿名访问
        filterRules.put("/jwt/jwtlogin", "anon");
        filterRules.put("/static/**", "anon");
        filterRules.put("/favicon.ico", "anon");
        filterRules.put("/druid/**", "anon");
        filterRules.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRules);
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
