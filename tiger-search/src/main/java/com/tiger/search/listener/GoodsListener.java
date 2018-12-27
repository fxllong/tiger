package com.tiger.search.listener;

import org.springframework.stereotype.Component;

/**
 * @author bystander
 * @date 2018/9/28
 */
@Component
public class GoodsListener {

//    @Autowired
//    private SearchService searchService;
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "ly.search.insert.queue", durable = "true"),
//            exchange = @Exchange(name = "ly.item.exchange",
//                    type = ExchangeTypes.TOPIC,
//                    ignoreDeclarationExceptions = "true"),
//            key = {"item.insert", "item.update"}
//            ))
//    public void listenInsert(Long id) {
//        //监听新增或更新
//        if (id != null) {
//            searchService.insertOrUpdate(id);
//        }
//
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "ly.search.insert.queue", durable = "true"),
//            exchange = @Exchange(name = "ly.item.exchange",
//                    type = ExchangeTypes.TOPIC,
//                    ignoreDeclarationExceptions = "true"),
//            key = "item.delete"
//    ))
//    public void listenDelete(Long id) {
//        //监听删除
//        if (id != null) {
//            searchService.delete(id);
//        }
//    }
}
