package com.yanger.exception.authorOld;

import com.yanger.exception.authorOld.AuthException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanger
 * @description 测试异常处理
 * @date 2019/10/17
 */
@RestController
@RequestMapping("ex")
public class ExceptionApiController {

    @GetMapping("hello1")
    public String hello(@RequestParam int i){
        int res = 1000/i;
        return "hello1, 1000/" + i + " = " + res;
    }

    //http://localhost:8080/auth/?i=2
    @GetMapping("auth")
    public String auth(@RequestParam int i) throws AuthException {
        if(i > 100) {
            throw new AuthException("权限不足");
        }
        return "auth ok";
    }

    @GetMapping("error")
    public String error(@RequestParam int i) throws AuthException {
        int[] is = new int[]{1,2,3,4,5};
        return "num is " + is[i];
    }

}
