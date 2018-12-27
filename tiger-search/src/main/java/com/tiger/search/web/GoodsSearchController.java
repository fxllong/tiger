package com.tiger.search.web;


import com.tiger.search.pojo.Goods;
import com.tiger.search.pojo.SearchRequest;
import com.tiger.search.pojo.SearchResult;
import com.tiger.search.service.SearchService;
import com.tiger.search.service.SearchService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RestController
public class GoodsSearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("page")
    public ResponseEntity<SearchResult<Goods>> search(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(searchService.search(searchRequest));
    }
}
