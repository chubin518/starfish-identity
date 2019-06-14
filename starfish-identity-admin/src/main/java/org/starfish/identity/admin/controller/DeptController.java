package org.starfish.identity.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.starfish.identity.entity.IdentityDept;
import org.starfish.identity.service.IdentityDeptService;
import org.starfish.identity.service.dto.IdentityDeptParam;
import org.starfish.identity.utils.PageBean;
import org.starfish.identity.utils.ResponseBean;

@RestController
@RequestMapping("/dept")
public class DeptController {
  @Autowired
  private IdentityDeptService deptService;

  @PostMapping("save")
  public ResponseBean<Integer> save(@RequestBody IdentityDeptParam param) {
    int result = deptService.save(param);
    return ResponseBean.success(result);
  }

  @GetMapping("detail/{id}")
  public ResponseBean<IdentityDept> detail(@PathVariable Integer id) {
    IdentityDept data = deptService.selectByPrimaryKey(id);
    return ResponseBean.apply(data);
  }

  @GetMapping("list")
  public ResponseBean<PageBean<IdentityDept>> list(IdentityDeptParam param, int pageIndex, int pageSize) {
    List<IdentityDept> list = deptService.list(param, pageIndex, pageSize);
    return ResponseBean.apply(PageBean.restPage(list));
  }
}