package com.tiger.item.mapper;

import com.tiger.item.pojo.LitemallCategory;
import com.tiger.item.pojo.LitemallGoods;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface GoodsMapper extends Mapper<LitemallGoods>, IdListMapper<LitemallGoods, Long> {
}
