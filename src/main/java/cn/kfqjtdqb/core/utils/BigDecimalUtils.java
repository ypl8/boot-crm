package cn.kfqjtdqb.core.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {


    //保留2位小数
    public  static  double  getDouble(double  number){
        BigDecimal bg = new BigDecimal(number);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return  f1;
    }
}
