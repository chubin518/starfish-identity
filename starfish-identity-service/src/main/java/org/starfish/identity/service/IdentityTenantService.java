package org.starfish.identity.service;

import java.util.List;

import org.starfish.identity.entity.IdentityTenant;

public interface IdentityTenantService extends BaseService<IdentityTenant> {

  int delete(List<Integer> ids);

  int save(IdentityTenant tenant);

  List<IdentityTenant> list(String name, int status, int isdeleted, int pageIndex, int pageSize);
}