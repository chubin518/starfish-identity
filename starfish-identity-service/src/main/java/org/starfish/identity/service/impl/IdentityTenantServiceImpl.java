package org.starfish.identity.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;
import org.starfish.identity.entity.IdentityTenant;
import org.starfish.identity.service.IdentityTenantService;

import tk.mybatis.mapper.weekend.Weekend;

@Service
public class IdentityTenantServiceImpl extends BaseServiceImpl<IdentityTenant> implements IdentityTenantService {
  @Override
  public int delete(List<Integer> ids) {
    Weekend<IdentityTenant> weekend = new Weekend<>(IdentityTenant.class);
    weekend.weekendCriteria().andIn(IdentityTenant::getId, ids).orEqualTo(IdentityTenant::getIsDeleted, 0);
    return deleteByExample(weekend);
  }

  @Override
  public int save(IdentityTenant tenant) {
    int result = 0;
    if (tenant.getId() >= 1) {
      result = updateByPrimaryKeySelective(tenant);
    } else {
      result = insert(tenant);
    }
    return result >= 1 ? tenant.getId() : 0;
  }

  @Override
  public List<IdentityTenant> list(String name, int status, int isdeleted, int pageIndex, int pageSize) {
    Weekend<IdentityTenant> weekend = new Weekend<>(IdentityTenant.class);
    weekend.weekendCriteria().andLike(IdentityTenant::getName, name).andEqualTo(IdentityTenant::getStatus, status)
        .andEqualTo(IdentityTenant::getIsDeleted, isdeleted);
    PageHelper.startPage(pageIndex, pageSize);
    return selectByExample(weekend);
  }
}