package cn.kfqjtdqb.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {


    public  static Date convert(String source) {
        //1.定义我们要转成的日期类型（格式是：yyyy-MM-dd HH:mm:ss）,
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            //2.进行转化，转化成功直接返回
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //3.如果转化失败返回null
        return null;
    }


    //将Date型日期转化成指定格式的字符串形式。例如：yyyy-MM-dd HH:mm:ss
    public  static  String changeTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);

    }

}
