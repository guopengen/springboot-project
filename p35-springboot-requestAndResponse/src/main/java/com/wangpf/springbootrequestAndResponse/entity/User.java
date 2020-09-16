package com.wangpf.springbootrequestAndResponse.entity;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2019/12/30 15:24
 */
public class User {
    private String name;
    private int age;
    private Teacher teacher;
    List<Course> courseList;
    Map<String,User> childrenUserMap;
    List<String> testList;

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }

    public User() {
    }

    public User(String name, int age, Teacher teacher, List<Course> courseList, Map<String, User> childrenUserMap) {
        this.name = name;
        this.age = age;
        this.teacher = teacher;
        this.courseList = courseList;
        this.childrenUserMap = childrenUserMap;
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Map<String, User> getChildrenUserMap() {
        return childrenUserMap;
    }

    public void setChildrenUserMap(Map<String, User> childrenUserMap) {
        this.childrenUserMap = childrenUserMap;
    }
}
