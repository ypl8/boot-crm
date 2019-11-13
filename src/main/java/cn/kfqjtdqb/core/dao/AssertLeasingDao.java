package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertLeasing;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AssertLeasingDao {
    List<AssertLeasing> selectAssertLeasingList(AssertLeasing assertLeasing);
    Integer selectAssertLeasingListCount(AssertLeasing assertLeasing);
    AssertLeasing getAssertLeasingById(Long id);
    void updateAssertLeasing(AssertLeasing assertLeasing)throws DataAccessException;
    void deleteAssertLeasing(Long id);
    int addAssertLeasing(AssertLeasing assertLeasing)throws DataAccessException;
    void deleteAssertLeasingByPropertyLeasingNum(String property_leasing_num)throws Exception;
}
