package cn.kfqjtdqb.core.utils;

import com.csvreader.CsvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * csv读写工具类
 * @author cy
 *
 */
public class CSVUtils {
	private static final Logger logger = LoggerFactory.getLogger(CSVUtils.class);
    /** CSV文件字段分隔符 */
    private static final String CSV_COLUMN_SEPARATOR = ",";

    /** CSV文件列分隔符 */
    private static final String CSV_RN = "\r\n";
	
	
	/**
	 * 读取csv文件
	 */
	public static ArrayList<String[]> csvReader(File file){
		// 用来保存数据
        ArrayList<String[]> csvFileList = new ArrayList<String[]>();
		try {
			CsvReader reader = new CsvReader(file.getAbsolutePath(),',',Charset.forName("UTF-8"));
			//跳过表头
			reader.readHeaders();
			//逐行读取
			while(reader.readRecord()){
	            csvFileList.add(reader.getValues()); 
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvFileList;
	}
	
	/**
	 * 写csv文件
	 * csvFileName csv文件名称
	 * dataList 集合数据
	 * colNames 表头部数据
	 * mapKey 表头对应字段的key
	 */
	public static void csvWrite(String csvFileName,List<Map<String, Object>> dataList,String colNames,String mapKey,HttpServletResponse response){
		try{
			final OutputStream os = response.getOutputStream();
			responseSetProperties(csvFileName,response);
			doExport(dataList,colNames,mapKey,os);
			
		}catch(Exception e){
			logger.error("导出csv文件失败",e);
		}
	}
	
	
	/**
     * 
     * @param dataList 集合数据
     * @param colNames 表头部数据
     * @param mapKey 查找的对应数据
     * @param response 返回结果
     */
    public static boolean doExport(List<Map<String, Object>> dataList, String colNames, String mapKey, OutputStream os) {
        try {
            StringBuffer buf = new StringBuffer();

            String[] colNamesArr = null;
            String[] mapKeyArr = null;

            colNamesArr = colNames.split(",");
            mapKeyArr = mapKey.split(",");

            // 完成数据csv文件的封装
            //先将BOM输出到csv文件中防止用excel打开时乱码
            byte[] uft8bom = {(byte)0xef,(byte)0xbb,(byte)0xbf};
            os.write(uft8bom);
            // 输出列头
            for (int i = 0; i < colNamesArr.length; i++) {
                buf.append(colNamesArr[i]).append(CSV_COLUMN_SEPARATOR);
            }
            buf.append(CSV_RN);

            if (null != dataList) { // 输出数据
                for (int i = 0; i < dataList.size(); i++) {
                    for (int j = 0; j < mapKeyArr.length; j++) {
                        buf.append(dataList.get(i).get(mapKeyArr[j])).append(CSV_COLUMN_SEPARATOR);
                    }
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            os.write(buf.toString().getBytes("UTF-8"));
            os.flush();
            return true;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return false;
    }
	
    /**
     * @throws UnsupportedEncodingException
     * 
     *             setHeader
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName +"_"+ sdf.format(new Date()).toString() + ".csv";
        // 读取字符编码
        String utf = "UTF-8";

        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }
	
	
}
