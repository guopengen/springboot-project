package com.wangpf.springbootrequestAndResponse.core.controller;

import com.wangpf.springbootrequestAndResponse.core.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/9/21 18:22
 */
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    CacheService cacheService;

    @RequestMapping("/query")
    public String cacheQuery(String userName){
        String cacheResult = cacheService.getCacheResult(userName);
        return cacheResult;
    }
}
