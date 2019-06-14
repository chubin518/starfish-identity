package org.starfish.identity.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentityDeptParam extends BaseParam {
  /**
   * 父主键
   */
  private Integer parentId;

  /**
   * 部门名
   */
  private String deptName;

  /**
   * 部门全称
   */
  private String fullName;

  /**
   * 排序
   */
  private Integer sort;

  /**
   * 备注
   */
  private String remark;

  /**
   * 是否已删除
   */
  private Byte isDeleted;
}