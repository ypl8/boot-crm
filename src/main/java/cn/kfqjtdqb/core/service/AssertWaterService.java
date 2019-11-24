package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertWater;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;

public interface AssertWaterService {
    Page<AssertWater> selectAssertWaterList(Integer page, Integer rows, String property_leasing_num, String assert_num,String status);
    AssertWater getAssertWaterById(Long id);
    void updateAssertWater(AssertWater AssertWater)throws DataAccessException;;
    void deleteAssertWater(Long id);
    List <AssertWater> selectAssertWaterListByAssertNum(String property_leasing_num, String assert_num,String status);
    int createAssertWater(AssertWater AssertWater) throws DataAccessException;
    void deleteAssertWaterByPropertyLeasingNum(String property_leasing_num)throws Exception ;
    BigDecimal getMaxWaterNum();
}
