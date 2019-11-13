package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertWaterRent;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;


/**
 * 水费输入
 */
public interface AssertWaterRentDao {

    List<AssertWaterRent> selectAssertWaterRentList(AssertWaterRent assertWaterRent);
    Integer selectAssertWaterRentListCount(AssertWaterRent assertWaterRent);
    AssertWaterRent getAssertWaterRentById(Long id);
    void updateAssertWaterRent(AssertWaterRent assertWaterRent)throws DataAccessException;
    void deleteAssertWaterRent(Long id);
    int addAssertWaterRent(AssertWaterRent AssertWaterRent)throws DataAccessException;
    List<AssertWaterRent>   getAssertWaterRentByLeasingNum(String property_leasing_num);
    BigDecimal getAssertWaterRentCountByLeasingNum(AssertWaterRent assertWaterRent);   //统计实际已收取的费用
    void deleteAssertWaterRentByPropertyLeasingNum(String property_leasing_num) throws Exception;
}
