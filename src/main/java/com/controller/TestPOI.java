package com.controller;

import com.common.PeopleFields;
import com.entity.People;
import com.function.poi.POIUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangqiang on 2017/8/28.
 */
@Controller
public class TestPOI {
    @RequestMapping("/testPOI.htm")
    public void testPOI(HttpServletResponse response,String title) throws IOException {
        List<People> peoples=new ArrayList<People>();
        peoples.add(new People(1,"LF",23,new Date(),11D));
        peoples.add(new People(2,"WYH",22,new Date(),12.00));
        peoples.add(new People(3,"WQ",25,new Date(),13.4));
        People people = new People();
        people.setId(4);
        people.setName("HSSF");
        peoples.add(people);
        HashMap<String, String> map = new HashMap<String, String>();
        PeopleFields[] fields = PeopleFields.values();
        for (PeopleFields field : fields) {
            map.put(field.name(),field.getValue());
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        POIUtil.setMap(map);
        POIUtil.exportExcel(workbook,peoples,title,People.class,response);
    }
}
