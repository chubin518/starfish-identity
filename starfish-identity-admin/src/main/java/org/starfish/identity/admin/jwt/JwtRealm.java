package org.starfish.identity.admin.jwt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;

public class JwtRealm extends AuthorizingRealm {

  @Autowired
  private IdentityUserService userService;

  /**
   * 必须重写此方法
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtToken;
  }

  /**
   * 授权 验证用户权限时调用此方法
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = JwtUtils.getInfoToken(principals.toString());
    // 获取权限信息
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    return simpleAuthorizationInfo;
  }

  /**
   * 认证 验证用户名密码的正确性，错误抛出异常即可
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String jwtToken = token.getCredentials().toString();
    String account = JwtUtils.getInfoToken(jwtToken);
    if (StringUtils.isEmpty(account)) {
      throw new AuthenticationException("token无效");
    }
    IdentityUser identityUser = userService.findUserByAccount(account);
    if (identityUser == null || identityUser.getId() <= 0) {
      throw new AuthenticationException("用户不存在!");
    }
    if (JwtUtils.verify(jwtToken, account, identityUser.getPassword())) {
      throw new AuthenticationException("用户名或密码错误");
    }
    return new SimpleAuthenticationInfo(token, token, getName());
  }

}