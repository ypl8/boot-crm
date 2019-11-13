package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertLeasing;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AssertLeasingService {
    Page<AssertLeasing> selectAssertLeasingList(Integer page, Integer rows, String property_leasing_num, String assert_num);

    AssertLeasing getAssertLeasingById(Long id);

    void updateAssertLeasing(AssertLeasing assertLeasing) throws DataAccessException;

    void deleteAssertLeasing(Long id);

    List<AssertLeasing> selectAssertLeasingListByAssertNum(String property_leasing_num, String assert_num);

    int createAssertLeasing(AssertLeasing assertLeasing) throws DataAccessException;

    void deleteAssertLeasingByPropertyLeasingNum(String property_leasing_num) throws Exception;
}
