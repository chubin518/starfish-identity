package org.starfish.identity.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.starfish.identity.entity.IdentityDept;
import org.starfish.identity.service.IdentityDeptService;
import org.starfish.identity.service.dto.IdentityDeptParam;

import tk.mybatis.mapper.weekend.Weekend;

@Service
public class IdentityDeptServiceImpl extends BaseServiceImpl<IdentityDept> implements IdentityDeptService {

  @Override
  public int save(IdentityDeptParam source) {
    IdentityDept target = new IdentityDept();
    BeanUtils.copyProperties(source, target);
    int result = 0;
    if (source.getId() >= 1) {
      target.setUpdateTime(LocalDateTime.now());
      target.setUpdateUser(source.getUserId());
      result = updateByPrimaryKeySelective(target);
    } else {
      target.setCreateTime(LocalDateTime.now());
      target.setCreateUser(source.getUserId());
      result = insert(target);
    }
    return result >= 1 ? target.getId() : 0;
  }

  @Override
  public int delete(List<Integer> ids) {
    Weekend<IdentityDept> weekend = new Weekend<>(IdentityDept.class);
    weekend.weekendCriteria().andIn(IdentityDept::getId, ids);
    return deleteByExample(weekend);
  }

  @Override
  public List<IdentityDept> list(IdentityDeptParam deptParam, int pageIndex, int pageSize) {
    Weekend<IdentityDept> weekend = new Weekend<>(IdentityDept.class);
    weekend.weekendCriteria().andEqualTo(IdentityDept::getTenantId, deptParam.getTenantId())
        .andEqualTo(IdentityDept::getParentId, deptParam.getParentId())
        .andLike(IdentityDept::getDeptName, deptParam.getDeptName())
        .andEqualTo(IdentityDept::getIsDeleted, deptParam.getIsDeleted())
        .andEqualTo(IdentityDept::getId, deptParam.getId());
    PageHelper.startPage(pageIndex, pageSize);
    return selectByExample(weekend);
  }

}