package com.tiger.service.web;

import com.tiger.common.vo.PageResult;
import com.tiger.item.pojo.Brand;
import com.tiger.item.pojo.Category;
import com.tiger.service.service.BrandService;
import com.tiger.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @create 2018-12-22 23:31
 **/
@RestController
@RequestMapping("brand")
public class BrandController {

    /**
     * 分页条件过滤列表查询
     */
    @Autowired
    private BrandService brandService;

    @GetMapping("list")
    public ResponseEntity<PageResult<Brand>> queryCategoryByPid(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return  ResponseEntity.ok(brandService.queryBrandListByKey(page,rows,sortBy,desc,key));
    }

    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.addBrand(brand, cids);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
