package com.tiger.item.web;

import com.tiger.item.service.CategoryService;
import com.tiger.common.utils.ResponseUtil;
import com.tiger.common.validator.Order;
import com.tiger.common.validator.Sort;
import com.tiger.common.vo.PageResult;
import com.tiger.item.pojo.LitemallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类管理
 */

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 展示分类列表
     * @param id
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("list")
    public Object queryCategoryByPid(
            String id, String name,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @Sort @RequestParam(defaultValue = "add_time") String sort,
            @Order @RequestParam(defaultValue = "desc") String order
    ){

        PageResult<LitemallCategory> pageResult = categoryService.queryAllCategory(id, name, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageResult.getTotal());
        data.put("items", pageResult.getItems());
        return ResponseUtil.ok(data);
    }

    /**
     * 增加分类
     * @param category
     * @return
     */
    @PostMapping
    public Object addCategory(@RequestBody LitemallCategory category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        categoryService.add(category);
        return ResponseUtil.ok(category);
    }

    /**
     * 查询单个分类
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Object read(@NotNull @PathVariable("id") Integer id) {
        LitemallCategory category = categoryService.findById(id);
        return ResponseUtil.ok(category);
    }

    /**
     * 修改单个分类
     * @param category
     * @return
     */
    @PutMapping
    public Object update(@RequestBody LitemallCategory category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        if (categoryService.updateById(category) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }

    /**
     * 删除单个分类
     * @param
     * @return
     */
    @DeleteMapping("{id}")
    public Object delete(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        categoryService.deleteById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("list/l1")
    public ResponseEntity<List<LitemallCategory>> queryCategoryByL1() {
        // 所有一级分类目录
        List<LitemallCategory> l1CatList = categoryService.queryChannel();
        List<Map<String, Object>> data = new ArrayList<>(l1CatList.size());
//        for (LitemallCategory category : l1CatList) {
//            Map<String, Object> d = new HashMap<>(2);
//            d.put("value", category.getId());
//            d.put("label", category.getName());
//            data.add(d);
//        }
        return ResponseEntity.ok(l1CatList);
    }

    private Object validate(LitemallCategory category) {
        String name = category.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String level = category.getLevel();
        if (StringUtils.isEmpty(level)) {
            return ResponseUtil.badArgument();
        }
        if (!level.equals("L1") && !level.equals("L2")) {
            return ResponseUtil.badArgumentValue();
        }
        Integer pid = category.getPid();
        if (level.equals("L2") && (pid == null)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

   

}
