package com.tiger.search.client;

import com.tiger.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author bystander
 * @date 2018/9/22
 */
@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
