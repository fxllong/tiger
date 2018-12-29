package com.tiger.search.client;

import com.tiger.common.vo.PageResult;
import com.tiger.item.pojo.Spu;
import com.tiger.search.pojo.Goods;
import com.tiger.search.repository.GoodsRepository;
import com.tiger.search.service.SearchService;
import com.tiger.search.service.SearchService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService2 searchService;

    @Test
    public void testCreateIndex() {
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

//    @Test
//    public void loadData() {
//        int page = 1;
//        int size = 0;
//        int rows = 100;
//        do {
//            PageResult<Spu> result = goodsClient.querySpuByPage(page, rows, true, null);
//            ArrayList<Goods> goodList = new ArrayList<>();
//            List<Spu> spus = result.getItems();
//            size = spus.size();
//            for (Spu spu : spus) {
//                try {
//                    Goods g = searchService.buildGoods(spu);
//                    goodList.add(g);
//
//                } catch (Exception e) {
//                    break;
//                }
//            }
//            this.repository.saveAll(goodList);
//            page++;
//        } while (size == 100);
//    }

    @Test
    public void loadTest(){
        int page = 1;
        int rows = 100;

        //分页查询数据
        PageResult<Spu> result = this.goodsClient.querySpuByPage(page, rows, true, null);
        List<Spu> spus = result.getItems();
        //创建Goods集合
        List<Goods> goodsList = new ArrayList<>();
        //遍历spu
        for (Spu spu : spus){
            try {
                Goods goods = this.searchService.buildGoods(spu);
                goodsList.add(goods);
            } catch (IOException e) {
                break;
            }
        }
        this.repository.saveAll(goodsList);
    }
}
