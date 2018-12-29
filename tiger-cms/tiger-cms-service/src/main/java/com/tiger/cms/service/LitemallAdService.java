package com.tiger.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.cms.mapper.LitemallAdMapper;
import com.tiger.cms.pojo.LitemallAd;
import com.tiger.common.enums.ExceptionEnum;
import com.tiger.common.exception.TigerException;
import com.tiger.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fanxinglong
 * @create 2018-12-29 14:25
 **/
@Service
public class LitemallAdService {

    @Autowired
    private LitemallAdMapper litemallAdMapper;

    public PageResult<LitemallAd> queryAll(String id, String name, Integer page, Integer limit, String sort, String order) {
        //分页
        PageHelper.startPage(page, limit);

        Example example = new Example(LitemallAd.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andEqualTo("id",Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name","%" + name + "%");
        }
        criteria.andEqualTo("deleted",false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        List<LitemallAd> categoryList = litemallAdMapper.selectByExample(example);
        PageInfo<LitemallAd> pageInfo = new PageInfo<LitemallAd>(categoryList);
        return new PageResult<LitemallAd>(pageInfo.getTotal(), categoryList);
    }

    public void add(LitemallAd category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        litemallAdMapper.insertSelective(category);
    }

    public LitemallAd findById(Integer id) {
        return litemallAdMapper.selectByPrimaryKey(id);
    }

    public int updateById(LitemallAd category) {
        //TODO 此处命名不合适
        LitemallAd category1 = new LitemallAd();
        return litemallAdMapper.updateByPrimaryKeySelective(category1);
    }

    public void deleteById(Integer id) {
        if (id == null) {
            throw new TigerException(ExceptionEnum.INVALID_PARAM);
        }
        LitemallAd category = new LitemallAd();
        category.setId(id);
        category.setDeleted(true);
        int count = litemallAdMapper.updateByPrimaryKeySelective(category);
        if (count == 0) {
            throw new TigerException(ExceptionEnum.DELETE_GOODS_ERROR);
        }
    }



}
