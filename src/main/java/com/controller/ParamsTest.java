package com.controller;

import com.entity.People;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * description:测试各种格式参数传递
 * @author wangqiang
 * date 2017/11/04 22:07:50
 */
@Controller
public class ParamsTest {

    //基本数据类型
    @ResponseBody
    @RequestMapping("/testCommonParams.htm")
    public List testCommonParams(String name, Integer age, Double money,
                                 Byte gender, Short married, Long days, Character gender1){
        ArrayList list = new ArrayList();
        list.add(name);
        list.add(age);
        list.add(money);
        list.add(gender);
        list.add(married);
        list.add(days);
        list.add(gender1);
        return list;
    }

    //对象数据类型
    @ResponseBody
    @RequestMapping("/testObject.htm")
    public String testObject(People people){
        System.out.println(people);
        return "YES";
    }

    //数组数据类型
    @ResponseBody
    @RequestMapping("/testArray.htm")
    public String testArray(Long[] names){
        for (Long name : names) {
            System.out.println(name);
        }
        return "YES";
    }


}
