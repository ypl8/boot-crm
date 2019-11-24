package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertPowerRent;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

public interface AssertPowerRentService {
    Page<AssertPowerRent> selectAssertPowerRentList(Integer page, Integer rows, String property_leasing_num, String assert_num, String state,String status);
    AssertPowerRent getAssertPowerRentById(Long id);
    void updateAssertPowerRent(AssertPowerRent assertPowerRent)throws DataAccessException;;
    void deleteAssertPowerRent(Long id);
    int createAssertPowerRent(AssertPowerRent assertPowerRent)throws DataAccessException;;
    List<AssertPowerRent>  getAssertPowerRentByLeasingNum(String property_leasing_num);
    BigDecimal getAssertPowerRentCountByLeasingNum(AssertPowerRent assertPowerRent);   //统计已经提交的钱
    void deleteAssertPowerRentByPropertyLeasingNum(String property_leasing_num)throws Exception ;
    List<AssertPowerRent> selectAssertPowerRentList(String property_leasing_num, String assert_num, String state,String status);
}
