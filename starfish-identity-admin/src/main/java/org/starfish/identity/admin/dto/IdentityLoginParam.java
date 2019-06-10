package org.starfish.identity.admin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class IdentityLoginParam {
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
