package com.tiger.item.service;

import com.github.pagehelper.PageHelper;
import com.tiger.item.mapper.GoodsMapper;
import com.tiger.item.pojo.LitemallCategory;
import com.tiger.item.pojo.LitemallGoods;
import com.tiger.item.pojo.LitemallGoodsAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author fanxinglong
 * @create 2018-12-29 17:47
 **/
public class GoodService {


    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 获取新品上市
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByNew(int offset, int limit) {
        Example example = new Example(LitemallCategory.class);
        Example.Criteria criteria = example.createCriteria();
        example.selectProperties("id", "name","brief","picUrl","isHot","isNew","counterPrice","retailPrice");
        example.or().andEqualTo("isNew",true).
                andEqualTo("isOnSale",true).andEqualTo("deleted",true);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return goodsMapper.selectByExample(example);
    }
}
