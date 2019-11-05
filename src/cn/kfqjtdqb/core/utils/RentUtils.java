package cn.kfqjtdqb.core.utils;

import cn.kfqjtdqb.core.bean.PropertyLeasing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentUtils {

    /**
     * 获取对应月份的租金
     * month  对应的工资
     */
    public static Double getMonthRent(int month, String[] monthRent) {  //获取每个月对应的工资
        int year = month / 12;
        return Double.parseDouble(monthRent[year]);
    }


    /**
     * 获取合同的总租金
     *
     * @param propertyLeasing
     * @return
     */
    public static Double getToalRent(PropertyLeasing propertyLeasing) {
        Integer rent_free_period = propertyLeasing.getRent_free_period();  //表示的是免租金
        Integer rent_period = propertyLeasing.getRent_period();   //表示的是租期 按(月份)
        double result = 0;
        //算总的合同
        String monthRental = propertyLeasing.getMonthly_rental();
        String[] months = monthRental.split(";");
        if (months.length > 1) {   //表示的是每个月的月租不一样
            for (int i = rent_free_period; i < rent_period; i++) {
                result += getMonthRent(i, months);
            }
        } else {   //表示的是每个月月租一样的
            Integer remainMonth = rent_period - rent_free_period;
            result = remainMonth * Double.parseDouble(months[0]);
        }
        return result;
    }

    //获取截止某个时间的总应该付的租金  如果不在时间范围内就返回-1
    public static Double getToalRentByCurrentDate(PropertyLeasing propertyLeasing, Date currentDate) {
        Double rent_should = 0d;
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
        if (startRentDate.after(currentDate)) {
            return -1d;
        }
        int month = DateUtils.getDifMonth(startRentDate, currentDate) + 1;   //需要交押金的月份
        int freePriod = propertyLeasing.getRent_free_period();  //获取免租期;
        if (month >= propertyLeasing.getRent_period()) {   //表示的是租期已经到了
            rent_should = getToalRent(propertyLeasing);
        } else {   //表示的是租期没有到
            if (freePriod - month >= 0) {   //表示的是没有过免租期
                rent_should = 0d;
            } else {  //表示的是过了免租期
                String monthRental = propertyLeasing.getMonthly_rental();
                String[] months = monthRental.split(";");
                if (months.length == 1) {  //表示的是每月的租金一样的。
                    rent_should = Double.parseDouble(months[0]) * (month - freePriod);
                } else {   //表示的是每个月的租金不一样。
                    double allRent = 0;
                    for (int i = freePriod; i < month; i++) {
                        allRent += RentUtils.getMonthRent(i, months);
                    }
                    rent_should = allRent;
                }
            }
        }
        return rent_should;
    }


    //获取某个月应该收的租金 如果超过时间范围会返回-1
    public static Double getMonthRent(PropertyLeasing propertyLeasing, Date currentDate) {
        Date startRentDate = propertyLeasing.getRent_start_time();  //租金开始时间
       /* if (currentDate.before(startRentDate) || currentDate.after(endRentDate)) {  //表示的是不在租期范围之内
            return -1d;
        }*/
        int freePriod = propertyLeasing.getRent_free_period();  //获取免租期;
        int month = DateUtils.getDifMonth(startRentDate, currentDate);  //统计两个日期之间相差的月份
        if(month<0){
            return -1d;
        }
        String[] months = propertyLeasing.getMonthly_rental().split(";");
        if (month+1 > propertyLeasing.getRent_period()) {   //表示的是在免租期范围之内
            return -1d;
        } else {
            if (freePriod - month >= 1) {   //表示的是没有过免租期
                return 0d;
            }
            if (months.length > 1) {
                return getMonthRent(month, months);
            } else {
                return Double.parseDouble(months[0]);
            }
        }
    }


    public static void main(String[] args) throws ParseException {
        //获得2010年9月13日22点36分01秒 的Date对象
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate2 = dateFormat2.parse("2016-09-13");
        PropertyLeasing propertyLeasing = new PropertyLeasing();
        propertyLeasing.setRent_start_time(myDate2);
        propertyLeasing.setMonthly_rental("100;200;300");
        propertyLeasing.setRent_end_time(dateFormat2.parse("2019-09-13"));
        propertyLeasing.setRent_free_period(1);
        propertyLeasing.setRent_period(36);
      //  System.out.println(getMonthRent(propertyLeasing, dateFormat2.parse("2019-09-13")));
        // System.out.println(getToalRent(propertyLeasing));
        System.out.println(getToalRentByCurrentDate(propertyLeasing,dateFormat2.parse("2016-08-13")));
    }

}
