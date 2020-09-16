package com.wangpf.springbootrequestAndResponse.aspect;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/6/25 10:49
 */
public class RequestLimitException extends Exception{
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
