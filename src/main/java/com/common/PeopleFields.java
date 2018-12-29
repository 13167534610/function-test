package com.common;

/**
 * description:people类中属性的枚举
 *
 * @author wangqiang
 */
public enum PeopleFields {
    id("编号"),name("名称"),age("年龄"),bir("生日"),money("资产");
    private String value;
    PeopleFields(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
