package cn.kfqjtdqb.core.utils;

import com.sun.xml.internal.ws.util.UtilException;
import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常处理类
 * @author cy
 *
 */
public class Assert {
	public static void throwBizException(String msg) throws UtilException {
		throw new UtilException(msg);
	}
	
	public static void isNull(Object obj,String msg) throws UtilException{
		if(obj==null){
			throwBizException(msg);
		}
	}
	public static void isNotNull(Object obj,String msg) throws UtilException{
		if(obj!=null){
			throwBizException(msg);
		}
	}
	public static void isEmpty(String str,String msg)throws UtilException{
		if(StringUtils.isEmpty(str)){
			throwBizException(msg);
		}
	}
	
	/**
	 *检查2个传入值是否相等
	 * @throws UtilException 
	 */
	public static void isEquals(String str1,String str2,String msg) throws UtilException{
		if(!str1.equals(str2)){
			throwBizException(msg);
		}
	}
	
}
