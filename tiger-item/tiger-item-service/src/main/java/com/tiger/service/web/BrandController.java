package com.tiger.service.web;

import com.tiger.common.vo.PageResult;
import com.tiger.item.pojo.Brand;
import com.tiger.item.vo.BrandVo;
import com.tiger.service.service.BrandService;
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

    @GetMapping("page")
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

    /**
     * 更新品牌
     *
     * @param brandVo
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(BrandVo brandVo) {
        brandService.updateBrand(brandVo);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除品牌
     *
     * @param bid
     * @return
     */
    @DeleteMapping("bid/{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("bid") Long bid) {
        brandService.deleteBrand(bid);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据分类ID查询品牌
     *
     * @param cid
     * @return
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }

    /**
     * 根据商品品牌ID查询品牌
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Brand> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(brandService.queryBrandByBid(id));
    }

    /**
     * 根据ids查询品牌
     * @param ids
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandsByIds(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(brandService.queryBrandByIds(ids));
    }

}
