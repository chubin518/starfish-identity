package org.starfish.identity.admin.shirojwt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.starfish.identity.admin.jwt.JwtUtils;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;

public class JwtAuthorizingRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizingRealm.class);

    @Autowired
    private IdentityUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtils.getInfoToken(principals.toString());
        LOGGER.info("doGetAuthorizationInfo 获取权限信息 ：" + username);
        // 获取权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("home:view");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwtToken = token.getCredentials().toString();
        String account = JwtUtils.getInfoToken(jwtToken);
        LOGGER.info("doGetAuthenticationInfo 登录认证 ：" + jwtToken);
        if (StringUtils.isEmpty(account)) {
            throw new AuthenticationException("token无效");
        }
        IdentityUser identityUser = userService.findUserByAccount(account);
        if (identityUser == null || identityUser.getId() <= 0) {
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtils.verify(jwtToken, account, identityUser.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(jwtToken, jwtToken, getName());
    }
}
