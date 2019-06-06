package org.starfish.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.starfish.identity.dao.BaseDao;
import org.starfish.identity.service.BaseService;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    protected BaseDao<T> currentDao;

    @Override
    public BaseDao<T> getCurrentDao() {
        return currentDao;
    }


    @Override
    public int deleteByPrimaryKey(Object key) {
        return currentDao.deleteByPrimaryKey(key);
    }

    @Override
    public int delete(T record) {
        return currentDao.delete(record);
    }

    @Override
    public int insert(T record) {
        return currentDao.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return currentDao.insertSelective(record);
    }

    @Override
    public boolean existsWithPrimaryKey(Object key) {
        return currentDao.existsWithPrimaryKey(key);
    }

    @Override
    public List<T> selectAll() {
        return currentDao.selectAll();
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        return currentDao.selectByPrimaryKey(key);
    }

    @Override
    public int selectCount(T record) {
        return currentDao.selectCount(record);
    }

    @Override
    public List<T> select(T record) {
        return currentDao.select(record);
    }

    @Override
    public T selectOne(T record) {
        return currentDao.selectOne(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return currentDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return currentDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByExample(Object example) {
        return currentDao.deleteByExample(example);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return currentDao.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Object example) {
        return currentDao.selectCountByExample(example);
    }

    @Override
    public T selectOneByExample(Object example) {
        return currentDao.selectOneByExample(example);
    }

    @Override
    public int updateByExample(T record, Object example) {
        return currentDao.updateByExample(record, example);
    }

    @Override
    public int updateByExampleSelective(T record, Object example) {
        return currentDao.updateByExampleSelective(record, example);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return currentDao.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return currentDao.selectByRowBounds(record, rowBounds);
    }

    @Override
    public int insertList(List<? extends T> recordList) {
        return currentDao.insertList(recordList);
    }

    @Override
    public int insertUseGeneratedKeys(T record) {
        return currentDao.insertUseGeneratedKeys(record);
    }

    @Override
    public PageInfo<T> pageByExample(Integer page, Integer size, Object example) {
        PageHelper.startPage(page, size);
        List<T> list = this.selectByExample(example);
        return new PageInfo<T>(list);
    }
}
