package com.function.poi;

import com.common.PeopleFields;
import com.entity.People;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

/**
 * 本工具类是一个通用的Excel导出工具类
 * 模板：一级标题（title0）  二级标题（title1）  数据表（dataRow）
 * 使用方法：二级标题汉化调用setMap<String,String>()方法
 *          调用exportExcel()方法设计导出Excel
 * @author blue sun
 */
public class POIUtil {
    private static final short FONTSIZE_0=18;
    private static final short FONTSIZE_1=14;
    private static final short FONTSIZE_2=10;
    private static final int COLUMN_START=0;
    private static final int COLUMN_TITLE0=0;
    private static final int COLUMN_WIDTH=12;
    private static final int ROW_START=0;
    private static final int ROW_END=1;
    private static final int ROWNUM_TITLE0=0;
    private static final int ROWNUM_TITLE1=2;
    private static final int FIELD_SUB_START=0;
    private static final int FIELD_SUB_END=1;
    private static final int DATAROW_ADD=3;

    private static Map<String,String> map = null;

    /**
     * 导出方法
     * @param workbook：工作簿，仅仅支持HSSFWorkBook
     * @param objs：倒数对象集合
     * @param title：文件名，一级标题
     * @param clazz：对象类型
     * @param response：响应对象
     */
    public static void exportExcel(Workbook workbook, List objs, String title, Class clazz, HttpServletResponse response, String filePath){
        OutputStream output = null;
        try {
            if (StringUtils.isEmpty(title))
                title = "newFile";
            Sheet sheet = workbook.createSheet(title);//建立新的sheet对象（excel的表单）
            Field[] fields = clazz.getDeclaredFields();//获取实体属性名
            //合并区域
            CellRangeAddress address = new CellRangeAddress(ROW_START, ROW_END, COLUMN_START, fields.length-1);
            sheet.addMergedRegion(address);//设置合并区域生效
            sheet.setDefaultColumnWidth(COLUMN_WIDTH);//设置默认列宽
            //创建一级标题
            Row title0 = sheet.createRow(ROWNUM_TITLE0);//开始行
            Cell cell = title0.createCell(COLUMN_TITLE0);//开始列
            cell.setCellStyle(getCellStyle(workbook,FONTSIZE_0));//设置样式
            cell.setCellValue(title);//设置一级标题名
            //创建二级标题
            Row title1 = sheet.createRow(ROWNUM_TITLE1);//从哪一行开始
            if (map == null) {//不需要标题汉化
                for (int i = 0; i < fields.length; i++) {
                    Cell colCell = title1.createCell(i);//循环创建列
                    colCell.setCellStyle(getCellStyle(workbook,FONTSIZE_1));//设置样式
                    colCell.setCellValue(fields[i].getName());//设置值
                }
            }else {//需要标题汉化
                for (int i = 0; i < fields.length; i++) {
                    Cell colCell = title1.createCell(i);
                    colCell.setCellStyle(getCellStyle(workbook,FONTSIZE_1));
                    colCell.setCellValue(getValue(fields[i].getName()));
                }
            }
            if (null == objs || objs.isEmpty()){//没有需要导出的数据
              throw new RuntimeException("No datas what you want to export");
            }
            //创建数据行
            for (int i = 0; i < objs.size(); i++) {
                Row dataRow = sheet.createRow(i + DATAROW_ADD);
                for (int j = 0; j < fields.length; j++) {
                    Cell dataCell = dataRow.createCell(j);
                    //获取属性get方法名
                    String getMethodName = "get" +
                            fields[j].getName().substring(FIELD_SUB_START, FIELD_SUB_END).toUpperCase()
                            + fields[j].getName().substring(FIELD_SUB_END);
                    //返回方法对象 //参数一:方法的名字   //参数二:方法参数的类型（无参）
                    Method getMethod = clazz.getDeclaredMethod(getMethodName, new Class[]{});
                    //执行方法  参数一:执行那个对象中的方法    参数二:该方法的参数
                    Object value = getMethod.invoke(objs.get(i), new Object[]{});
                    //设置单元格显示格式控件
                    judgeAndSetValue(workbook, value, dataCell);
                }
            }
            if (response != null){
                output = response.getOutputStream();
                response.reset();
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(title+".xls","UTF-8"));
                response.setContentType("application/msexcel");
            }else {
                output = new FileOutputStream(filePath + title + ".xls");
            }
            workbook.write(output);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置样式
     * @param workbook：工作簿
     * @param fontSize：字号
     * font.setFontName("");：设置字体类型，如：宋体
     */
    private static CellStyle getCellStyle(Workbook workbook,short fontSize){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中对齐
        Font font = workbook.createFont();
        if (fontSize == FONTSIZE_0 || fontSize == FONTSIZE_1)
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置粗体显示
        font.setFontHeightInPoints(fontSize);//设置字体大小
        cellStyle.setFont(font);//设置字体样式生效
        cellStyle.setWrapText(true);//设置自动换行
        return cellStyle;
    }

    /**
     * description：根据值得类型设置单元格格式并给单元格设值
     * @param workbook：工作簿
     * @param value：通过get方法获取的值
     * @param dataCell：数据单元格
     */
    private static void judgeAndSetValue(Workbook workbook, Object value, Cell dataCell){
        DataFormat df = workbook.createDataFormat();//数据格式对象
        if (value instanceof Integer){//整数类型
            CellStyle cellStyle = getCellStyle(workbook, FONTSIZE_2);
            dataCell.setCellStyle(cellStyle);
            dataCell.setCellValue((Integer)value);
        }
        if (value instanceof Long){//长整数类型
            CellStyle cellStyle = getCellStyle(workbook, FONTSIZE_2);
            dataCell.setCellStyle(cellStyle);
            dataCell.setCellValue((Long)value);
        }
        if (value instanceof Double){//双精度小数类型
            CellStyle cellStyle = getCellStyle(workbook, FONTSIZE_2);
            cellStyle.setDataFormat(df.getFormat("#,#0.00"));
            dataCell.setCellStyle(cellStyle);
            dataCell.setCellValue((Double)value);
        }
        if (value instanceof Float){//单精度小数类型
            CellStyle cellStyle = getCellStyle(workbook, FONTSIZE_2);
            cellStyle.setDataFormat(df.getFormat("#,#0.0"));
            dataCell.setCellStyle(cellStyle);
            dataCell.setCellValue((Float)value);
        }
        if (value instanceof Date){//日期类型
            CellStyle cellStyle = getCellStyle(workbook, FONTSIZE_2);
            cellStyle.setDataFormat(df.getFormat("yyyy/MM/dd"));
            dataCell.setCellStyle(cellStyle);
            dataCell.setCellValue((Date)value);
        }
        if(value instanceof String){//字符串类型
            CellStyle cellStyle = getCellStyle(workbook, FONTSIZE_2);
            dataCell.setCellStyle(cellStyle);
            dataCell.setCellValue((String)value);
        }
    }

    /**
     * 标题汉化,可以使用枚举来维护map，如下 PeopleFields是一个枚举
     * PeopleFields[] fields = PeopleFields.values();
     * for (PeopleFields field : fields) {
     *   map.put(field.name(),field.getValue());
     * }
     * @param map1：key为实体类属性名，value为对应的汉语意思
     */
    public static void setMap(Map<String, String> map1){
        map=map1;
    }

    /**
     * 获取汉化二级标题
     * @param key：实体类的属性名
     */
    private static String getValue(String key){
        return map.get(key);
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
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
        POIUtil.exportExcel(workbook,peoples,"非浏览器测试",People.class, null, "D:/");
    }
}