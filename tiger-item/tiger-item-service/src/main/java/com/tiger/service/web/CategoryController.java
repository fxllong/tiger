package com.tiger.service.web;

import com.tiger.item.pojo.Category;
import com.tiger.service.service.BrandService;
import com.tiger.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2018-12-22 23:31
 **/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam long pid){

        return  ResponseEntity.ok(categoryService.queryCategoryByPid(pid));
    }

    /**
     * 根据品牌ID查询商品分类
     *
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid") Long bid) {
        return ResponseEntity.ok(brandService.queryCategoryByBid(bid));
    }

    /**
     * 根据商品分类Ids查询分类
     * @param ids
     * @return
     */
    @GetMapping("list/ids")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(categoryService.queryCategoryByIds(ids));
    }

    /**
     * 根据cid3查询三级分类
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id) {
        return ResponseEntity.ok(categoryService.queryAllByCid3(id));
    }

}
