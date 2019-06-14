package org.starfish.identity.service;

import java.util.List;

import org.starfish.identity.entity.IdentityDept;
import org.starfish.identity.service.dto.IdentityDeptParam;

public interface IdentityDeptService extends BaseService<IdentityDept> {
  int save(IdentityDeptParam deptParam);

  int delete(List<Integer> ids);

  List<IdentityDept> list(IdentityDeptParam deptParam, int pageIndex, int pageSize);
}