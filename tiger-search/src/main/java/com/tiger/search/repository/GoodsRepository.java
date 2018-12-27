package com.tiger.search.repository;

import com.tiger.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author bystander
 * @date 2018/9/22
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
