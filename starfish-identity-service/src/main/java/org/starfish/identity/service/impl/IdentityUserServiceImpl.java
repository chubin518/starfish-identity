package org.starfish.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starfish.identity.dao.IdentityUserDao;
import org.starfish.identity.entity.IdentityUser;
import org.starfish.identity.service.IdentityUserService;
import tk.mybatis.mapper.weekend.Weekend;

@Service
public class IdentityUserServiceImpl extends BaseServiceImpl<IdentityUser> implements IdentityUserService {
    @Autowired
    private IdentityUserDao userDao;

    @Override
    public IdentityUser findUserByAccount(String account) {
        Weekend<IdentityUser> weekend = new Weekend<>(IdentityUser.class);
        weekend.weekendCriteria()
                .andEqualTo(IdentityUser::getAccount, account)
                .andEqualTo(IdentityUser::getIsDeleted, 0);
        return userDao.selectOneByExample(weekend);
    }

    @Override
    public int updateByAccount(IdentityUser identityUser, String account) {
        Weekend<IdentityUser> weekend = new Weekend<>(IdentityUser.class);
        weekend.weekendCriteria().andEqualTo(IdentityUser::getAccount, account);
        return userDao.updateByExampleSelective(identityUser, account);
    }
}
