package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertPower;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AssertPowerDao {
    List<AssertPower> selectAssertPowerList(AssertPower assertPower);
    Integer selectAssertPowerListCount(AssertPower assertPower);
    AssertPower getAssertPowerById(Long id);
    void updateAssertPower(AssertPower assertPower) throws DataAccessException;;
    void deleteAssertPower(Long id);
    int addAssertPower(AssertPower assertPower)throws DataAccessException;;

    void deleteAssertPowerByPropertyLeasingNum(String property_leasing_num);
}
