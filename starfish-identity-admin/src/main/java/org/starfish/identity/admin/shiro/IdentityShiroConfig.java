package org.starfish.identity.admin.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.starfish.identity.admin.utils.ConstantUtils;

@Configuration
public class IdentityShiroConfig {
    @Autowired
    private ConstantUtils constantUtils;

    /**
     * 密码匹配器
     *
     * @return
     */
    @Bean
    public RetryLimitCredentialsMatcher credentialsMatcher(CacheManager cacheManager) {
        RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher(cacheManager);
        credentialsMatcher.setHashAlgorithmName(constantUtils.getAlgorithmName());
        credentialsMatcher.setHashIterations(constantUtils.getIterations());
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public IdentityShiroRealm shiroRealm(RetryLimitCredentialsMatcher credentialsMatcher) {
        IdentityShiroRealm shiroRealm = new IdentityShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    /**
     * 缓存
     */
    @Bean
    public EhCacheManager cacheManager(){
        
    }
}
