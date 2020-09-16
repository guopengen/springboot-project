package com.wangpf.springbootrequestAndResponse.aspect;

import java.lang.annotation.*;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/6/25 10:25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitKey {
    //方法名称
    String methodName() default "";

    //访问次数
    int frequency() default 2;

    //业务KEY
    String paramKey() default "productType";

    //请求地址
    String url() default "";

    //过期时间
    long timeout() default 1000;
}
