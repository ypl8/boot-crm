package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertWater;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface  AssertWaterDao {
    List<AssertWater> selectAssertWaterList(AssertWater assertWater);
    Integer selectAssertWaterListCount(AssertWater assertWater);
    AssertWater getAssertWaterById(Long id);
    void updateAssertWater(AssertWater assertWater)throws DataAccessException;
    void deleteAssertWater(Long id);
    int addAssertWater(AssertWater assertWater)throws DataAccessException;
}
