package org.starfish.identity.admin.jwt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtShiroConfig {

  @Bean
  public Realm realm() {
    JwtRealm realm = new JwtRealm();
    return realm();
  }

  @Bean
  public DefaultWebSecurityManager securityManager(List<Realm> realms) {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealms(realms);

    // 关闭自带session
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    sessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
    securityManager.setSubjectDAO(subjectDAO);
    return securityManager;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    shiroFilterFactoryBean.setUnauthorizedUrl("/oauth/403");
    Map<String, Filter> filterMap = new HashMap<>();
    filterMap.put("jwt", new JwtFilter());
    shiroFilterFactoryBean.setFilters(filterMap);
    Map<String, String> filterRules = new LinkedHashMap<>();
    filterRules.put("/oauth/logout", "logout");
    // anon:所有url都都可以匿名访问
    filterRules.put("/oauth/login", "anon");
    filterRules.put("/static/**", "anon");
    filterRules.put("/favicon.ico", "anon");
    filterRules.put("/druid/**", "anon");
    filterRules.put("/**", "jwt");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRules);
    return shiroFilterFactoryBean;
  }
}