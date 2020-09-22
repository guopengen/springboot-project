package com.wangpf.springbootrequestAndResponse.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/9/21 18:24
 */
@Slf4j
@Service
public class CacheService {

    //@Cacheable(value = "name")
    public String getCacheResult(String name){
        log.info("###CacheService name"+name);
        return "hello"+name;
    }
}
