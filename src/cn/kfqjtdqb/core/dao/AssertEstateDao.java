package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertEstate;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

public interface AssertEstateDao {

    List<AssertEstate> selectAssertEstateList(AssertEstate assertEstate);
    Integer selectAssertEstateListCount(AssertEstate assertEstate);
    AssertEstate getAssertEstateById(Long id);
    void updateAssertEstate(AssertEstate assertEstate)throws DataAccessException;
    void deleteAssertEstate(Long id);
    int addAssertEstate(AssertEstate AssertEstate)throws DataAccessException;
    List<AssertEstate>   getAssertEstateByLeasingNum(String property_leasing_num);
    BigDecimal getAssertEstateCountByLeasingNum(String property_leasing_num);   //统计实际已收取的费用
    void deleteAssertEstateByPropertyLeasingNum(String property_leasing_num);
}
