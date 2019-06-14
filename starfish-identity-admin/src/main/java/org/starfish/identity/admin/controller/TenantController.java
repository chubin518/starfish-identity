package org.starfish.identity.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.starfish.identity.entity.IdentityTenant;
import org.starfish.identity.service.IdentityTenantService;
import org.starfish.identity.utils.PageBean;
import org.starfish.identity.utils.ResponseBean;

@RestController
@RequestMapping("/tenant")
public class TenantController {
  @Autowired
  private IdentityTenantService tenantService;

  @PostMapping("save")
  public ResponseBean<Integer> save(@RequestBody IdentityTenant tenant) {
    int result = tenantService.save(tenant);
    return ResponseBean.success(result);
  }

  @GetMapping("detail")
  public ResponseBean<IdentityTenant> detail(@RequestParam Integer id) {
    IdentityTenant tenant = tenantService.selectByPrimaryKey(id);
    return ResponseBean.apply(tenant);
  }

  @GetMapping("list")
  public ResponseBean<PageBean<IdentityTenant>> list(@RequestParam("name") String name,
      @RequestParam("status") int status, @RequestParam("isdeleted") int isdeleted,
      @RequestParam(value = "pageindex", defaultValue = "1") int pageIndex,
      @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
    List<IdentityTenant> lstTenent = tenantService.list(name, status, isdeleted, pageIndex, pageSize);
    return ResponseBean.apply(PageBean.restPage(lstTenent));
  }
}