package com.tiger.user.service;

import com.tiger.item.pojo.LitemallCategory;
import com.tiger.user.mapper.UserMapper;
import com.tiger.user.pojo.LitemallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;

/**
 * @author fanxinglong
 * @create 2018-12-29 22:39
 **/

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public LitemallUser queryByOid(String openId) {
        Example example = new Example(LitemallUser.class);
        Example.Criteria criteria = example.createCriteria();
        example.or().andEqualTo("weixinOpenid",openId)
                .andEqualTo("deleted",false);
        return userMapper.selectOneByExample(example);
    }

    public void add(LitemallUser user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
    }

    public int updateById(LitemallUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
