package com.common;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String,String> getFieldsMap(){
        Map<String, String> map = new HashMap<String, String>();
        PeopleFields[] values = PeopleFields.values();
        return map;
    }
}
