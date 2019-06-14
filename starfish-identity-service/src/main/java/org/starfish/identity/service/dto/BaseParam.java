package org.starfish.identity.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseParam {
  private Integer tenantId;
  private Integer id;
  private Integer userId;
}