package com.tiger.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.common.enums.ExceptionEnum;
import com.tiger.common.exception.TigerException;
import com.tiger.common.vo.PageResult;
import com.tiger.item.mapper.CategoryMapper;
import com.tiger.item.pojo.LitemallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author
 * @create 2018-12-22 23:29
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public PageResult<LitemallCategory> queryAllCategory(String id, String name, Integer page, Integer limit, String sort, String order) {
        //分页
        PageHelper.startPage(page, limit);

        Example example = new Example(LitemallCategory.class);
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

        List<LitemallCategory> categoryList = categoryMapper.selectByExample(example);
        PageInfo<LitemallCategory> pageInfo = new PageInfo<LitemallCategory>(categoryList);
        return new PageResult<LitemallCategory>(pageInfo.getTotal(), categoryList);
    }

    public void add(LitemallCategory category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
    }

    public LitemallCategory findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public int updateById(LitemallCategory category) {
        //TODO 此处命名不合适
        LitemallCategory category1 = new LitemallCategory();
        category1.setId(category.getId());
        category1.setName(category.getName());
        category1.setKeywords(category.getKeywords());
        category1.setDesc(category.getDesc());
        category1.setPid(category.getPid());
        category1.setPicUrl(category.getPicUrl());
        category1.setIconUrl(category.getIconUrl());
        category1.setLevel(category.getLevel());
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category1);
    }

    public void deleteById(Integer id) {
        if (id == null) {
            throw new TigerException(ExceptionEnum.INVALID_PARAM);
        }
        LitemallCategory category = new LitemallCategory();
        category.setId(id);
        category.setDeleted(true);
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count == 0) {
            throw new TigerException(ExceptionEnum.DELETE_GOODS_ERROR);
        }
    }

    public List<LitemallCategory> queryChannel() {
        Example example = new Example(LitemallCategory.class);
        Example.Criteria criteria = example.createCriteria();
        example.or().andEqualTo("level","L1")
                .andEqualTo("deleted",false);
        return categoryMapper.selectByExample(example);
    }


//    public List<Category> queryCategoryByPid(long pid) {
//        Category c = new Category();
//        c.setParentId(pid);
//        List<Category> list = categoryMapper.select(c);
//        if(CollectionUtils.isEmpty(list)){
//            throw new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
//        }
//        return list;
//    }

//    public List<Category> queryCategoryByIds(List<Long> ids) {
//        List<Category> list = categoryMapper.selectByIdList(ids);
//        if(CollectionUtils.isEmpty(list)){
//            throw new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
//        }
//        return list;
//
//    }
//
//    public List<Category> queryAllByCid3(Long id) {
//        Category c3 = categoryMapper.selectByPrimaryKey(id);
//        Category c2 = categoryMapper.selectByPrimaryKey(c3.getParentId());
//        Category c1 = categoryMapper.selectByPrimaryKey(c2.getParentId());
//        List<Category> list = Arrays.asList(c1, c2, c3);
//        if (CollectionUtils.isEmpty(list)) {
//            throw new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
//        }
//        return list;
//    }
}
