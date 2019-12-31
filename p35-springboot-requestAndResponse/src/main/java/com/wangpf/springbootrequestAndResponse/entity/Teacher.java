package com.wangpf.springbootrequestAndResponse.entity;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2019/12/30 20:19
 */
public class Teacher {
    private String teacherName;

    public Teacher() {
    }

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
