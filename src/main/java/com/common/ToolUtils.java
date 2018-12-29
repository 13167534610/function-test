package com.common;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class ToolUtils {
	
	public static final String RETURNCODE = "000";
	
    private final String ol = "{";
    private final String or = "}";
    private final String il = "<";
    private final String ir = ">";

    public HashMap string2HashMap(String str) {
        //前四位为分割符
        String outl = "";
        String outr = "";
        String inl = "";
        String inr = "";
        if (null == str || "".equals(str)) {
            return null;
        } else {
            outl = str.substring(0, 1);
            inl = str.substring(1, 2);
            inr = str.substring(2, 3);
            outr = str.substring(3, 4);
            str = str.substring(4, str.length());
        }
        Pattern pattern = Pattern.compile("[" + outl + outr + "]+");
        String[] strs = pattern.split(str);
        HashMap back = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            if ("".equals(strs[i])) continue;
            String[] strs1 = strs[i].split(inr);
            if (strs1.length == 2) {
                back.put(strs1[0].replaceAll(inl, "").trim().toUpperCase(), strs1[1].replaceAll(inl, "").trim());
            }
        }
        return back;
    }

    public String hashMap2String(HashMap hm) {
        String key;
        StringBuffer back = new StringBuffer();
        Iterator it = (hm.keySet()).iterator();
        while (it.hasNext()) {
            //强制String转换，存入变量key
            key = (String) it.next();
            //首先输出键，之后调用hm.get()方法，取每个键的值
            back.append(ol + il + key.toLowerCase() + ir + il + hm.get(key) + ir + or);
        }
        return back.toString();
    }

    public List listHashMap2ListString(List rs) {
        List back = new ArrayList();
        for (int i = 0; i < rs.size(); i++) {
            HashMap hm = (HashMap) rs.get(i);
            back.add(hashMap2String(hm));
        }
        return back;
    }

    public String getHashMapValue(HashMap hm, String name) {
        name = name.toUpperCase();
        if (hm.get(name) == null) {
            return "";
        } else {
            return ((Object) hm.get(name)).toString();
        }
    }

    
    public static String MD5encrypt(String s) {
        // 用作十六进制的数组.
        byte hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");// 使用MD5加密
            byte[] strTemp = s.getBytes("utf-8");// 把传入的字符串转换成字节数组
            mdTemp.update(strTemp);//
            byte[] md = mdTemp.digest();
            int j = md.length;
            byte str[] = new byte[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);// 返回加密后的字符串.
        } catch (Exception e) {
            return null;
        }
    }

    public String getRandom(int longR) {
        StringBuffer back = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < longR; i++) {
            back.append(random.nextInt(10) + "");
        }
        return back.toString();
    }

    /**
     * 去掉字符串中的空字符
     *
     * @param ary 要操作的字符串
     * @return 如出错则返回""字符串.
     */
    public String getString(String ary) {
        String ls_str;
        try {
            ls_str = ary.trim();
        } catch (Exception ex) {
            return "";
        }
        return ls_str;
    }

    public String getSysTime(String formatparam) {
        Date date = new Date();
        String format = formatparam;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(date);
        return result;
    }

    public void hashMapToVo(Object obj, HashMap hm) {
        if (obj == null) {
            return;
            //throw new Exception("对象为空");
        }
        Class clazz = obj.getClass();

        /*if (clazz.getSimpleName().equals("Object")) {
            //return;
        }*/

        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return;
            //throw new Exception("当前对象中没有任何属性值");
        }
        String val = "";
        for (int i = 0; i < fields.length; i++) {
            if ("java.lang.String".equals(fields[i].getType().getCanonicalName())) {
                fields[i].setAccessible(true);
                if (null == hm.get(fields[i].getName().toUpperCase())) {
                    val = "";
                } else {
                    val = (String) hm.get(fields[i].getName().toUpperCase().trim());
                }
                try {
                    fields[i].set(obj, val.trim());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //Object value = fields[i].get(obj);
            }
        }
    }

    public String getMethodName() {
        String name = new Exception().getStackTrace()[1].getMethodName();// 获得调用者的方法名
        return name.substring(2, name.length());
    }

    public String separator(String key, String value) {
        if (null == key) key = "";
        if (null == value) value = "";
        return ol + il + key + ir + il + value + ir + or;
    }

    public String getSeparatorHead() {
        return ol + il + ir + or;
    }

    public String judgeReturnCode(String str) {
        if (str.indexOf("returncode") > 0) {
            HashMap hm = string2HashMap(str);
            if ("000".equals(hm.get("returncode"))) {
                return "success";
            } else {
                return "error";
            }
        } else {
            return "success";
        }
    }

    public static Object createObject(String className) {
        Class clazz = null;
        Object o = null;
        try {
            clazz = Class.forName(className);
            o = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }


    public static final String getKey() {
        return "123456789";
    }

    public static void main(String[] argv) {
        /*ToolUtils tool = new ToolUtils();
       System.out.println(tool.MD5encrypt("73100147000002028065D7C5CF0FA4B72013-06-14 16:33:48bxwjob@163.com12345654@sxm#2"));*/
    	String string = "12399298731018301000235294511012131423546y76578127.0.0.1" + null;
    	String md5encrypt = ToolUtils.MD5encrypt(string);
    	System.out.println(md5encrypt);
        List list = null;
        if (!CollectionUtils.isEmpty(list)||list.size()>1){
            System.out.println(true);
        }
    }
}