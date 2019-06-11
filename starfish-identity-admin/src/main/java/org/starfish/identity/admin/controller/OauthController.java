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
import org.starfish.identity.utils.CommonResult;
import org.starfish.identity.utils.IPAddressUtils;

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
    public CommonResult login(@RequestBody IdentityLoginParam loginParam, BindingResult result, HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserName(), loginParam.getPassword());
        String error = "";
        try {
            subject.login(token);
//            IdentityUser identityUser = new IdentityUser();
//            identityUser.setLoginTime(LocalDateTime.now());
//            identityUser.setLoginAddress(IPAddressUtils.getIpAddr(request));
//            userService.updateByAccount(identityUser, loginParam.getUserName());
        } catch (UnknownAccountException ex) {
            return CommonResult.failed("用户名不存在");
        } catch (IncorrectCredentialsException ex) {
            return CommonResult.failed("密码输入错误");
        } catch (LockedAccountException e) {
            return CommonResult.failed(e.getMessage());
        } catch (AuthenticationException e) {
            return CommonResult.failed("其他错误：" + e.getMessage());
        }
        return CommonResult.success("登录成功");
    }

    @PostMapping("logout")
    public CommonResult logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return CommonResult.success("退出登录成功");
    }

    @GetMapping("403")
    public CommonResult unauthorized() {
        return CommonResult.unauthorized("未登录");
    }
}
