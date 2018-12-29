package com.tiger.item.api;

import com.tiger.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author bystander
 * @date 2018/9/22
 */
public interface CmsApi {

    @GetMapping("brand/{id}")
    Brand queryById(@PathVariable("id") Long id);

    @GetMapping("brand/list")
    List<Brand> queryBrandsByIds(@RequestParam(value = "ids", required = false) List<Long> ids);
}
