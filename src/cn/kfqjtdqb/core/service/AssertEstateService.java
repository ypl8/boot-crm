package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertEstate;

import java.math.BigDecimal;
import java.util.List;

public interface AssertEstateService {
    Page<AssertEstate> selectAssertEstateList(Integer page, Integer rows, String property_leasing_num, String state);

    AssertEstate getAssertEstateById(Long id);

    void updateAssertEstate(AssertEstate assertEstate);

    void deleteAssertEstate(Long id);

    int createAssertEstate(AssertEstate assertEstate);

    List<AssertEstate> getAssertEstateByLeasingNum(String property_leasing_num);

    BigDecimal getAssertEstateCountByLeasingNum(String property_leasing_num);   //统计已经提交的钱

    void deleteAssertEstateByPropertyLeasingNum(String property_leasing_num);

    List<AssertEstate> selectAssertEstateList(String property_leasing_num, String state);
}
