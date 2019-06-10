package org.starfish.identity.admin.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.starfish.identity.admin.utils.ConstantPropertyUtils;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private IdentityUserService userService;

    @Autowired
    private ConstantPropertyUtils constantPropertyUtils;

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        IdentityUser identityUser = (IdentityUser) info.getPrincipals().getPrimaryPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(identityUser.getAccount());
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(identityUser.getAccount(), retryCount);
        }
        // 是否锁定过期
        boolean isExpire = identityUser.getIsLocked() == 1 && LocalDateTime.now().isAfter(identityUser.getLockTime().plusMinutes(constantPropertyUtils.getLockMinutes()));
        if (isExpire) {
            if (identityUser.getIsLocked() == 1) {
                identityUser.setIsLocked((byte) 0);
                identityUser.setLockTime(LocalDateTime.of(1900, 1, 1, 1, 1));
                userService.updateByPrimaryKey(identityUser);
            }
            passwordRetryCache.remove(identityUser.getAccount());
        } else {
            if (retryCount.incrementAndGet() > constantPropertyUtils.getLockMinutes()) {
                if (identityUser.getIsLocked() == 0) {
                    identityUser.setIsLocked((byte) 1);
                    identityUser.setLockTime(LocalDateTime.now());
                    userService.updateByPrimaryKey(identityUser);
                }
                throw new LockedAccountException();
            }
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordRetryCache.remove(identityUser.getAccount());
        }
        return matches;
    }
}
