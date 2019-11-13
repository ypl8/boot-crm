package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertInfol;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * 押金收入
 */
public interface AssertDepositDao {

    List<AssertDeposit> selectAssertDepositList(AssertDeposit assertDeposit);
    Integer selectAssertDepositListCount(AssertDeposit assertDeposit);
    AssertDeposit getAssertDepositById(Long id);
    void updateAssertDeposit(AssertDeposit assertDeposit)throws DataAccessException;;
    void deleteAssertDeposit(Long id);
    int addAssertDeposit(AssertDeposit assertDeposit)throws DataAccessException;;
    AssertDeposit   getAssertDepositByLeasingNum(String property_leasing_num);
    void deleteAssertDepositByLeasingNum(String property_leasing_num);
}
