package com.testClass.po;

/**
 * Created by lenovo on 2018/4/29.
 */

public class User {
    private  String name;
    private  String phone;
    private  Integer age;

    public User(String name, String phone, Integer age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
