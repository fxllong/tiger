package com.tiger.user.mapper;

import com.tiger.item.pojo.LitemallCategory;
import com.tiger.user.pojo.LitemallUser;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<LitemallUser> , IdListMapper<LitemallUser, Long> {
}
