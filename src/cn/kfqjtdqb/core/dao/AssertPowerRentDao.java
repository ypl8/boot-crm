package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertPowerRent;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;


/**
 * 电费输入
 */
public interface AssertPowerRentDao {

    List<AssertPowerRent> selectAssertPowerRentList(AssertPowerRent assertPowerRent);

    Integer selectAssertPowerRentListCount(AssertPowerRent assertPowerRent);

    AssertPowerRent getAssertPowerRentById(Long id);

    void updateAssertPowerRent(AssertPowerRent assertPowerRent);

    void deleteAssertPowerRent(Long id)throws DataAccessException;;

    int addAssertPowerRent(AssertPowerRent AssertPowerRent)throws DataAccessException;;

    List<AssertPowerRent> getAssertPowerRentByLeasingNum(String property_leasing_num);

    BigDecimal getAssertPowerRentCountByLeasingNum(AssertPowerRent assertPowerRent);   //统计实际已收取的费用

    void deleteAssertPowerRentByPropertyLeasingNum(String property_leasing_num)throws Exception;
}
