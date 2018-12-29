package com.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangqiang on 2017/8/28.
 */
public class People implements Serializable{
    private Integer id;
    private String name;
    private Integer age;
    @JSONField(format = "yyyy/MM/dd")
    private Date bir;
    private Double money;

    public People() {
    }

    public People(Integer id, String name, Integer age, Date bir, Double money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bir = bir;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
