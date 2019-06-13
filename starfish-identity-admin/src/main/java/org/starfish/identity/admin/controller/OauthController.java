package org.starfish.identity.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.starfish.identity.admin.dto.IdentityLoginParam;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;
import org.starfish.identity.utils.IPAddressUtils;
import org.starfish.identity.utils.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    private IdentityUserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public ResponseBean<String> login(@RequestBody IdentityLoginParam loginParam, BindingResult result,
            HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserName(), loginParam.getPassword());
        String error = "";
        try {
            subject.login(token);
            // IdentityUser identityUser = new IdentityUser();
            // identityUser.setLoginTime(LocalDateTime.now());
            // identityUser.setLoginAddress(IPAddressUtils.getIpAddr(request));
            // userService.updateByAccount(identityUser, loginParam.getUserName());
        } catch (UnknownAccountException ex) {
            return ResponseBean.notFound("用户名不存在");
        } catch (IncorrectCredentialsException ex) {
            return ResponseBean.conflict("密码输入错误");
        } catch (LockedAccountException e) {
            return ResponseBean.locked(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBean.unAuthorized("其他错误：" + e.getMessage());
        }
        return ResponseBean.success("登录成功");
    }

    @PostMapping("logout")
    public ResponseBean<String> logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseBean.success("退出登录成功");
    }

    @GetMapping("403")
    public ResponseBean<String> unauthorized() {
        return ResponseBean.unAuthorized("未登录");
    }
}
