package com.tiger.service.mapper;

import com.tiger.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> , IdListMapper<Category, Long> {
}
