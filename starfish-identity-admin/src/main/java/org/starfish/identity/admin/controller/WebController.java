package org.starfish.identity.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.starfish.identity.admin.jwt.JwtUtils;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;
import org.starfish.identity.utils.ResponseBean;

@RestController
public class WebController {
  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WebController.class);

  @Autowired
  private IdentityUserService userService;

  @PostMapping("/login")
  public ResponseBean login(@RequestParam("username") String username, @RequestParam("password") String password) {
    IdentityUser userBean = userService.findUserByAccount(username);
    if (userBean.getPassword().equals(password)) {
      return new ResponseBean(200, "Login success", JwtUtils.create(username, password));
    } else {
      throw new UnauthorizedException();
    }
  }

  @GetMapping("/article")
  public ResponseBean article() {
    Subject subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()) {
      return new ResponseBean(200, "You are already logged in", null);
    } else {
      return new ResponseBean(200, "You are guest", null);
    }
  }

  @GetMapping("/require_auth")
  @RequiresAuthentication
  public ResponseBean requireAuth() {
    return new ResponseBean(200, "You are authenticated", null);
  }

  @GetMapping("/require_role")
  @RequiresRoles("admin")
  public ResponseBean requireRole() {
    return new ResponseBean(200, "You are visiting require_role", null);
  }

  @GetMapping("/require_permission")
  @RequiresPermissions(logical = Logical.AND, value = { "view", "edit" })
  public ResponseBean requirePermission() {
    return new ResponseBean(200, "You are visiting permission require edit,view", null);
  }

  @RequestMapping(path = "/401")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseBean unauthorized() {
    return new ResponseBean(401, "Unauthorized", null);
  }

}