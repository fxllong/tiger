package com.tiger.service.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.common.enums.ExceptionEnum;
import com.tiger.common.exception.TigerException;
import com.tiger.common.vo.PageResult;
import com.tiger.item.pojo.Brand;
import com.tiger.item.pojo.Category;
import com.tiger.service.mapper.BrandMapper;
import com.tiger.service.mapper.CategoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @create 2018-12-22 23:29
 **/
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandListByKey(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页
        PageHelper.startPage(page,rows);

        //条件
        Example example = new Example(Brand.class);
        //判断传来的参数是否为空
        if(StringUtils.isNotBlank(key)){
            example.createCriteria().orLike("name","%" + key + "%")
                    .orEqualTo("letter","%"+key.toUpperCase()+"%");
        }
        if(StringUtils.isNotBlank(sortBy)){
            String sortByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(sortByClause);
        }

        List<Brand> list = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw  new TigerException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        PageInfo info = new PageInfo(list);
        PageResult<Brand> ps = new PageResult<Brand>(info.getTotal(),list);

        return ps;

        //返回结果
    }

    public void addBrand(Brand brand, List<Long> ids) {
        int count = brandMapper.insert(brand);
        if(count!=1){
            throw new TigerException(ExceptionEnum.BRAND_CREATE_FAILED);
        }
        //更新品牌分类表
        for (Long cid : ids) {
            count = brandMapper.saveCategoryBrand(cid, brand.getId());

            if (count == 0) {
                throw new TigerException(ExceptionEnum.BRAND_CREATE_FAILED);
            }
        }
    }
}
