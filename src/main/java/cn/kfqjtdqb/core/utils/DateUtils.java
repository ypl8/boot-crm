package cn.kfqjtdqb.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    //统计两个日期相差的月份数
    public static Integer getDifMonth(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);
        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return month + result;
    }


    public static Date getMonth(Date currentTime, int index) {
        Calendar cal = Calendar.getInstance();//获取一个Calendar对象
        cal.setTime(currentTime);
        cal.add(Calendar.MONTH, index);//获取当前时间的下一个月
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));//获取下一个月的最后一天
        Date preMonth = cal.getTime();//得到下个月的最后一天
        return preMonth;
    }

    public static String  getYearMonth(Date currentTime, int index) {
        Date date = getMonth(currentTime, index);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMM");
        return  simpleDateFormat.format(date);
    }


    public static void main(String[] args) {
        String current = getYearMonth(new Date(), 3);
        System.out.println(current);
    }


}
