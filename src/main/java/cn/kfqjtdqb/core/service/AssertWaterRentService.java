package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertWaterRent;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

public interface AssertWaterRentService {
    Page<AssertWaterRent> selectAssertWaterRentList(Integer page, Integer rows, String property_leasing_num,String assert_num, String state,String status);
    AssertWaterRent getAssertWaterRentById(Long id);
    void updateAssertWaterRent(AssertWaterRent assertWaterRent)throws DataAccessException;;
    void deleteAssertWaterRent(Long id);
    int createAssertWaterRent(AssertWaterRent assertWaterRent)throws DataAccessException;;
    List<AssertWaterRent>  getAssertWaterRentByLeasingNum(String property_leasing_num);
    BigDecimal getAssertWaterRentCountByLeasingNum(AssertWaterRent assertWaterRent);   //统计已经提交的钱
    void deleteAssertWaterRentByPropertyLeasingNum(String property_leasing_num)throws Exception ;
    List<AssertWaterRent> selectAssertWaterRentList(String property_leasing_num, String assert_num, String state,String status);
}
