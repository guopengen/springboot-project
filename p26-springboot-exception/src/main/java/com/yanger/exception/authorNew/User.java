package com.yanger.exception.authorNew;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.Serializable;

/**
 * @author wangpengfei101022
 * @Description: TODO
 * @date 2019/12/25 10:47
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    /** 编号 */
    private int id;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private int age;

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
