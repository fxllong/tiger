package com.tiger.item.mapper;

import com.tiger.item.pojo.LitemallCategory;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<LitemallCategory> , IdListMapper<LitemallCategory, Long> {
}
