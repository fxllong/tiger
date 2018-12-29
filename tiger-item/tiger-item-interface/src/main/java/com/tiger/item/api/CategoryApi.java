package com.tiger.item.api;

import com.tiger.item.pojo.Category;
import com.tiger.item.pojo.LitemallCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @date 2018/9/22
 */
@RequestMapping("category")
public interface CategoryApi {

//    @GetMapping("list/ids")
//    List<LitemallCategory> queryCategoryByIds(@RequestParam(value = "ids") List<Long> ids);

    @GetMapping("list/l1")
    List<LitemallCategory> queryCategoryByL1();


}
