package org.starfish.identity.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.starfish.identity.dao.BaseDao;
import java.util.List;

/**
 * @param <T>
 */
public interface BaseService<T> {
    BaseDao<T> getCurrentDao();

    int deleteByPrimaryKey(Object key);

    int delete(T record);

    int insert(T record);

    int insertSelective(T record);

    boolean existsWithPrimaryKey(Object key);

    List<T> selectAll();

    T selectByPrimaryKey(Object key);

    int selectCount(T record);

    List<T> select(T record);

    T selectOne(T record);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    int deleteByExample(Object example);

    List<T> selectByExample(Object example);

    int selectCountByExample(Object example);

    T selectOneByExample(Object example);

    int updateByExample(T record, Object example);

    int updateByExampleSelective(T record, Object example);

    List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds);

    List<T> selectByRowBounds(T record, RowBounds rowBounds);

    int insertList(List<? extends T> recordList);

    int insertUseGeneratedKeys(T record);

    PageInfo<T> pageByExample(Integer page, Integer size, Object example);
}
