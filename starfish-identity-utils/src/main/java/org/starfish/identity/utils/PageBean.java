package org.starfish.identity.utils;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页对象
 */
@Getter
@Setter
public class PageBean<E> implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer pageNum;
  private Integer pageSize;
  private Integer pageTotal;
  private Long recordTotal;
  private List<E> records;

  public static <E> PageBean<E> build(Integer pageNum, Integer pageSize, Long recordTotal, List<E> records) {
    PageBean<E> response = new PageBean<>();
    response.setPageNum(pageNum);
    response.setPageSize(pageSize);
    response.setRecordTotal(recordTotal);
    response.setPageTotal((int) Math.ceil(recordTotal / pageSize));
    response.setRecords(records);
    return response;
  }

  public static <E> PageBean<E> restPage(List<E> list) {
    PageBean<E> result = new PageBean<E>();
    PageInfo<E> pageInfo = new PageInfo<E>(list);
    result.setPageTotal(pageInfo.getPages());
    result.setPageNum(pageInfo.getPageNum());
    result.setPageSize(pageInfo.getPageSize());
    result.setRecordTotal(pageInfo.getTotal());
    result.setRecords(pageInfo.getList());
    return result;
  }
}