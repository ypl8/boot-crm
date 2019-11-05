package cn.kfqjtdqb.core.utils;

import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.bean.ResultCode;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.List;

public class ErrorUtils {

    public static ResultCode  getRsult(Errors errors) {
            ResultCode  resultCode=new ResultCode();
            StringBuilder stringBuilder = new StringBuilder();
            // 获取错误信息
            List<FieldError> errorList = errors.getFieldErrors();
            for (FieldError error : errorList) {
                // 打印字段错误信息
                stringBuilder.append("字段 :" + error.getField() + "错误信息" + error.getDefaultMessage());
            }
            resultCode.setCode(-1);
            resultCode.setMsg(stringBuilder.toString());
            return resultCode;
    }

}
