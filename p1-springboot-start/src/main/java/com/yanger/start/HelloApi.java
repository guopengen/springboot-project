package com.yanger.start;

import com.aspect.LimitKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {
    /**
     *@Description
     LogBack读取配置文件的步骤
    （1）尝试classpath下查找文件logback-test.xml
    （2）如果文件不存在，尝试查找logback.xml
    （3）如果两个文件都不存在，LogBack用BasicConfiguration自动对自己进行最小化配置，这样既实现了上面我们不需要添加任何配置就可以输出到控制台日志信息。

    1.src 路径下的文件 在编译后都会放到 WEB-INF/classes 路径下。默认classpath 就是指这里。
    2.用maven构建 项目时，resources 目录就是默认的classpath
     *@Author wangpf
     *@Date 2019/12/26
    **/
    private static Logger logger = LoggerFactory.getLogger(HelloApi.class);
    @LimitKey(methodName="hello", url="hello")
    @GetMapping("hello")
    public String hello(){
        logger.info("==========hello spring boot!==========");
        return "Hello Spring Boot";
    }
}
