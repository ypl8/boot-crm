package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertRental;

import java.math.BigDecimal;
import java.util.List;


/**
 * 押金收入
 */
public interface AssertRentalDao {

    List<AssertRental> selectAssertRentalList(AssertRental assertRental);
    Integer selectAssertRentalListCount(AssertRental assertRental);
    AssertRental getAssertRentalById(Long id);
    void updateAssertRental(AssertRental assertRental);
    void deleteAssertRental(Long id);
    int addAssertRental(AssertRental assertRental);
    List<AssertRental>   getAssertRentalByLeasingNum(String property_leasing_num);
    BigDecimal getAssertRentalCountByLeasingNum(String property_leasing_num);   //统计实际已收取的费用
    void deleteAssertRentalByPropertyLeasingNum(String property_leasing_num);
}
