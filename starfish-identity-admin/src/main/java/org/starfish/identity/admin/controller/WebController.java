package org.starfish.identity.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
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
import org.starfish.identity.admin.shirojwt.JwtAuthenticationToken;
import org.starfish.identity.admin.utils.ConstantPropertyUtils;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;
import org.starfish.identity.utils.ResponseBean;

@RestController
@RequestMapping("/jwt")
public class WebController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WebController.class);

    @Autowired
    private IdentityUserService userService;
    @Autowired
    private ConstantPropertyUtils propertyUtils;

    @PostMapping("/login")
    public ResponseBean<String> login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        IdentityUser userBean = userService.findUserByAccount(username);
        SimpleHash simpleHash = new SimpleHash(propertyUtils.getAlgorithmName(), password, userBean.getSalt(),
                propertyUtils.getIterations());
        String newPassword = simpleHash.toString();
        if (null != userBean && userBean.getPassword().equals(newPassword)) {
            return ResponseBean.success(JwtUtils.create(username, userBean.getPassword()));
        } else {
            throw new UnauthorizedException();
        }
    }

    @PostMapping("/jwtlogin")
    public ResponseBean<String> jwtLogin(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        IdentityUser userBean = userService.findUserByAccount(username);
        SimpleHash simpleHash = new SimpleHash(propertyUtils.getAlgorithmName(), password, userBean.getSalt(),
                propertyUtils.getIterations());
        String jwt = JwtUtils.create(username, simpleHash.toString());
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(jwtAuthenticationToken);
            boolean isSuc = subject.isAuthenticated();
            return ResponseBean.success(jwt);
        } catch (Exception ex) {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("article")
    public ResponseBean<String> article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResponseBean.success("You are already logged in");
        } else {
            return ResponseBean.success("You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean<String> requireAuth() {
        return ResponseBean.success("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return ResponseBean.success("You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = { "view", "edit" })
    public ResponseBean requirePermission() {
        return ResponseBean.success("You are visiting permission require edit,view");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return ResponseBean.unAuthorized("Unauthorized");
    }

}