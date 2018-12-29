package com.tiger.index.service;

import com.tiger.cms.pojo.LitemallAd;
import com.tiger.common.utils.ResponseUtil;
import com.tiger.index.client.BrandClient;
import com.tiger.index.client.CategoryClient;
import com.tiger.item.pojo.LitemallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author fanxinglong
 * @create 2018-12-29 17:43
 **/
@Service
public class WxIndexService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;


    public Map<String, Object> index() {

        Map<String, Object> data = new HashMap<>();
        //查询类别数据
        List<LitemallCategory> channel = categoryClient.queryCategoryByL1();
        System.out.println("======="+channel.size());
        data.put("channel", channel);
        return data;
    }

}
