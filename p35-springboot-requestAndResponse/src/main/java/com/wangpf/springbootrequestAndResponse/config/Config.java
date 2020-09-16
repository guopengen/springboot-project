package com.wangpf.springbootrequestAndResponse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2019/12/30 17:34
 */

//@Configuration
//@ImportResource(locations= {"classpath:spring-mvc.xml"})
    @Component
public class Config {
    String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
