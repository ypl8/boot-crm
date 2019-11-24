package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertPower;
import cn.kfqjtdqb.core.bean.AssertWater;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

public interface AssertPowerService {
    Page<AssertPower> selectAssertPowerList(Integer page, Integer rows, String property_leasing_num, String assert_num,String status);
    AssertPower getAssertPowerById(Long id);
    void updateAssertPower(AssertPower assertPower)throws DataAccessException;;
    void deleteAssertPower(Long id);
    List<AssertPower> selectAssertPowerListByAssertNum(String property_leasing_num, String assert_num,String status);
    int createAssertPower(AssertPower assertPower)throws DataAccessException;;
    void deleteAssertPowerByPropertyLeasingNum(String property_leasing_num)throws Exception ;
    BigDecimal getMaxPowerNum();
}
