package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertInfol;

import java.util.List;

public interface AssertDepositService {
    Page<AssertDeposit> findAssertDepositList(Integer page, Integer rows, String property_leasing_num, String state,String status);

    AssertDeposit getAssertDepositById(Long id);

    void updateAssertDeposit(AssertDeposit assertDeposit);

    void deleteAssertDeposit(Long id);

    int createAssertDeposit(AssertDeposit assertDeposit);

    AssertDeposit getAssertDepositByLeasingNum(String property_leasing_num);

    void deleteAssertDepositByLeasingNum(String property_leasing_num);

    List<AssertDeposit> selectAssertDepositList(String property_leasing_num, String state);
}
