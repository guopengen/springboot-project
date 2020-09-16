package com.wangpf.springbootrequestAndResponse.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangpf.springbootrequestAndResponse.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @Author wangpengfei101022
 * @Dat
 * e 2020/6/26 18:49
 */
@Aspect
@Component
@Order(1)
public class ControllerLogInterceptor {
    //创建Pointcut表示式，表示所有controller请求
    @Pointcut("execution(* com..*.controller..*(..))")
    private void controllerAspect() {
        System.out.println("===============");
    }// 请求method前打印内容

    @AfterReturning(value = "controllerAspect()",returning="returnValue")
    public Object around(JoinPoint pjp,Object returnValue) throws Throwable {
        //通过uuid关联请求参数和返回参数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //methodBefore(pjp, uuid);
        try {
            /*System.out.println("---------------"+returnValue);
            if (returnValue instanceof User) {
                User resultVO = (User) returnValue;
                String message = resultVO.getName();
                resultVO.setName("通过AOP把值修改了 " + message);
            }
            System.out.println("修改完毕-->返回方法为:" + returnValue);*/

            //proceed = pjp.proceed();
            System.out.println("方法返回值为：" + returnValue);
            Object o = JSON.toJSON(returnValue);
            JSONObject jsonObj = new JSONObject();
            if (o instanceof JSONObject) {
                jsonObj = (JSONObject) o;
            }


            Map<String, Object> data =new HashMap<>();
            //循环转换
            Iterator it =jsonObj.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                data.put(entry.getKey(), entry.getValue());
            }
            data.put("name","ssss");
            System.out.println(data);
            return  data;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void methodBefore(JoinPoint joinPoint, String uuid) {
        try {
        // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
        Object[] objs = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (int i = 0; i < objs.length; i++) {
        if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
        paramMap.put(argNames[i], objs[i]);
        }
        }
        if (paramMap.size() > 0) {
            System.out.println("方法："+joinPoint.getSignature()+"参数："+JSONObject.toJSONString(paramMap));
        }
        } catch (Exception e) {

        }
    }

}
