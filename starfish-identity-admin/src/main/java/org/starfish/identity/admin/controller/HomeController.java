package org.starfish.identity.admin.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IdentityUserService userService;
    @GetMapping("")
    @RequiresAuthentication
    public String index() {
        return "home";
    }

    @GetMapping("test")
    public IdentityUser test(){
      return userService.findUserByAccount("admin123");
    }
}
