package com.wangpf.springbootrequestAndResponse.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/6/25 10:26
 */
@Component
@Order
@Aspect
public class LimitAspect {
    private Map limitMap = new HashMap();


    private static final Logger log = LoggerFactory.getLogger(LimitAspect.class);

    @Pointcut("@annotation(limitKey)")
    public void limit(LimitKey limitKey) {
    }

    @Around("limit(limitKey)")
    public Object aroundLog(ProceedingJoinPoint joinpoint, LimitKey limitKey) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
        int frequency = limitKey.frequency();
        String methodName = limitKey.methodName();
        String paramKey = limitKey.paramKey();
        String url = limitKey.url();
        //入参
        Class returnType = methodSignature.getReturnType();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinpoint.getArgs();
        Object obj = null ;

        for(int i = 0 ; i < parameterNames.length;i++) {
            if(parameterNames[i].equals(paramKey)) {
                obj = args[i];
                break;
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(url).append("/").append(methodName);
        //设置sb的一个过期时间，用redis，拿出来判断是否存在，存在就继续，否则从limitMap中删除
        if(limitMap.get(sb.toString()) == null ) {
            limitMap.put(sb.toString(),frequency-1);
        } else {
            int surplusCount = (int) limitMap.get(sb.toString());
            if(surplusCount > 0){
                limitMap.put(sb.toString(), --surplusCount);
            } else {
                Map<String, Object> mp = new HashMap<String, Object>();
                mp.put("msg", 50003);//接口超过请求次数
                return returnType.newInstance();
            }
        }
        System.err.println("剩余次数："+limitMap.get(sb.toString())+" 自定义key:"+sb.toString());
        return joinpoint.proceed();
    }
}
