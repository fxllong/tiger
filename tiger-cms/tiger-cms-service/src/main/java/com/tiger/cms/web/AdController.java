package com.tiger.cms.web;

import com.tiger.cms.pojo.LitemallAd;
import com.tiger.cms.service.LitemallAdService;
import com.tiger.common.utils.ResponseUtil;
import com.tiger.common.validator.Order;
import com.tiger.common.validator.Sort;
import com.tiger.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/ad")
@Validated
public class AdController {

    @Autowired
    private LitemallAdService adService;

    @GetMapping("/list")
    public Object list(String name, String content,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        PageResult<LitemallAd> pageResult =  adService.queryAll(name, content, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageResult.getTotal());
        data.put("items", pageResult.getItems());
        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallAd ad) {
        String name = ad.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String content = ad.getName();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    /**
     * 增加分类
     * @param category
     * @return
     */
    @PostMapping
    public Object addCategory(@RequestBody LitemallAd category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        adService.add(category);
        return ResponseUtil.ok(category);
    }

    /**
     * 查询单个分类
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Object read(@NotNull @PathVariable("id") Integer id) {
        LitemallAd category = adService.findById(id);
        return ResponseUtil.ok(category);
    }

    /**
     * 修改单个分类
     * @param category
     * @return
     */
    @PutMapping
    public Object update(@RequestBody LitemallAd category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        if (adService.updateById(category) == 0) {
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
        adService.deleteById(id);
        return ResponseUtil.ok();
    }

}
