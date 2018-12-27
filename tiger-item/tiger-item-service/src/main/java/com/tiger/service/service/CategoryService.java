package com.tiger.service.service;

import com.tiger.common.enums.ExceptionEnum;
import com.tiger.common.exception.TigerException;
import com.tiger.item.pojo.Category;
import com.tiger.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author
 * @create 2018-12-22 23:29
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryByPid(long pid) {
        Category c = new Category();
        c.setParentId(pid);
        List<Category> list = categoryMapper.select(c);
        if(CollectionUtils.isEmpty(list)){
            throw new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    public List<Category> queryCategoryByIds(List<Long> ids) {
        List<Category> list = categoryMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(list)){
            throw new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;

    }

    public List<Category> queryAllByCid3(Long id) {
        Category c3 = categoryMapper.selectByPrimaryKey(id);
        Category c2 = categoryMapper.selectByPrimaryKey(c3.getParentId());
        Category c1 = categoryMapper.selectByPrimaryKey(c2.getParentId());
        List<Category> list = Arrays.asList(c1, c2, c3);
        if (CollectionUtils.isEmpty(list)) {
            throw new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
