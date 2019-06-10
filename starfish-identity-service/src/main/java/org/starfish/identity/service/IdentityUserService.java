package org.starfish.identity.service;

import org.starfish.identity.entity.IdentityUser;

public interface IdentityUserService  extends BaseService<IdentityUser>{
    IdentityUser findUserByAccount(String account);
    int updateByAccount(IdentityUser identityUser,String account);
}
