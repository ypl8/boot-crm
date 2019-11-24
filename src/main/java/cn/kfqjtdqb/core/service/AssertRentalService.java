package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertRental;

import java.math.BigDecimal;
import java.util.List;

public interface AssertRentalService {
    Page<AssertRental> selectAssertRentalList(Integer page, Integer rows, String property_leasing_num, String  state,String status);
    AssertRental getAssertRentalById(Long id);
    void updateAssertRental(AssertRental assertRental);
    void deleteAssertRental(Long id);
    int createAssertRental(AssertRental assertRental);
    List<AssertRental>  getAssertRentalByLeasingNum(String property_leasing_num);
    BigDecimal getAssertRentalCountByLeasingNum(String property_leasing_num);   //统计已经提交的钱
    void deleteAssertRentalByPropertyLeasingNum(String property_leasing_num);
    List<AssertRental> selectAssertRentalAllList(String property_leasing_num, String state,String status);
}
