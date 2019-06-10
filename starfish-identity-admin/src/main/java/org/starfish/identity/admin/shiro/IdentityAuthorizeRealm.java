package org.starfish.identity.admin.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.starfish.identity.admin.utils.ConstantPropertyUtils;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;

import java.time.LocalDateTime;

public class IdentityAuthorizeRealm extends AuthorizingRealm {
    @Autowired
    private IdentityUserService userService;

    @Autowired
    private ConstantPropertyUtils constantPropertyUtils;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String account = authenticationToken.getPrincipal().toString();
        IdentityUser identityUser = userService.findUserByAccount(account);
        if (identityUser == null || identityUser.getId() <= 0) {
            throw new UnknownAccountException(String.format("用户名：%s 不存在", account));
        }

        if (identityUser.getIsLocked() == 1 && LocalDateTime.now().isBefore(identityUser.getLockTime().plusMinutes(constantPropertyUtils.getLockMinutes()))) {
            throw new LockedAccountException(String.format("用户名：%s 锁定%s分钟", account, constantPropertyUtils.getLockMinutes()));
        }

        return new SimpleAuthenticationInfo(identityUser,
                identityUser.getPassword(),
                ByteSource.Util.bytes(identityUser.getSalt()),
                getName());
    }
}
