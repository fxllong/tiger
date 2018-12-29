package com.tiger.index.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.cms.pojo.LitemallAd;
import com.tiger.common.utils.ResponseUtil;
import com.tiger.index.service.WxIndexService;
import com.tiger.item.pojo.LitemallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanxinglong
 * @create 2018-12-29 17:39
 **/
@RestController
@RequestMapping("wx/index")
public class WxIndexcontroller {

    @Autowired
    private WxIndexService wxIndexService;

    /**
     * 首页数据
     *
     * @return 首页数据
     */
    @GetMapping("/index")
    public Object index() {

        Map<String, Object> data = new HashMap<>();
        //查询banner数据
//        List<LitemallAd> banner = adService.queryIndex();
//        data.put("banner", banner);
        //查询类别数据
        data = wxIndexService.index();


//        //查询最新商品数据
//        List<LitemallGoods> newGoods = goodsService.queryByNew(0, SystemConfig.getNewLimit());
//        data.put("newGoodsList", newGoods);
//        //查询最热门数据
//        List<LitemallGoods> hotGoods = goodsService.queryByHot(0, SystemConfig.getHotLimit());
//        data.put("hotGoodsList", hotGoods);
//        //查询品牌数据
//        List<LitemallBrand> brandList = brandService.queryVO(0, SystemConfig.getBrandLimit());
//        data.put("brandList", brandList);
//        //查询专题数据
//        List<LitemallTopic> topicList = topicService.queryList(0, SystemConfig.getTopicLimit());
//        data.put("topicList", topicList);
//
//        List<Map> categoryList = new ArrayList<>();
//        List<LitemallCategory> catL1List = categoryService.queryL1WithoutRecommend(0, SystemConfig.getCatlogListLimit());
//        for (LitemallCategory catL1 : catL1List) {
//            List<LitemallCategory> catL2List = categoryService.queryByPid(catL1.getId());
//            List<Integer> l2List = new ArrayList<>();
//            for (LitemallCategory catL2 : catL2List) {
//                l2List.add(catL2.getId());
//            }
//
//            List<LitemallGoods> categoryGoods = null;
//            if (l2List.size() == 0) {
//                categoryGoods = new ArrayList<>();
//            } else {
//                categoryGoods = goodsService.queryByCategory(l2List, 0, SystemConfig.getCatlogMoreLimit());
//            }
//
//            Map<String, Object> catGoods = new HashMap<String, Object>();
//            catGoods.put("id", catL1.getId()); //一级分类ID
//            catGoods.put("name", catL1.getName()); //一级分类名称
//            catGoods.put("goodsList", categoryGoods); //二级分类下所对应的所有商品
//            categoryList.add(catGoods);
//       }
//        data.put("floorGoodsList", categoryList);

        //缓存数据
        return ResponseUtil.ok(data);
    }
}
