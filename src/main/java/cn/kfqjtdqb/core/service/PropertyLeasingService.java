package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertLeasing;
import cn.kfqjtdqb.core.bean.CountEmpty;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PropertyLeasingService {

    Page<PropertyLeasing> findPropertyLeasingList(Integer page, Integer rows, String property_leasing_num, String collect_rent_way, String collect_rate_way, String property_leasing_state, String community_name,String  assert_num,String  property_leasing_type,String status);

    PropertyLeasing getPropertyLeasingById(Long id);

    void updatePropertyLeasing(PropertyLeasing propertyLeasing) throws DataAccessException;


    void deletePropertyLeasing(Long id);

    int addPropertyLeasing(PropertyLeasing propertyLeasing) throws DataAccessException;


    int addAssertLeasing(AssertLeasing assertLeasing) throws DataAccessException;

    void deleteAssertLeasingByPropertyLeasingNum(String property_leasing_num) throws  Exception;

    void deleteAssertLeasingByAssertNumAndPropertyLeasingNum(String property_leasing_num,String  assert_num) throws  Exception;


    PropertyLeasing findPropertyLeasingWithAssert(Long id);

    Integer selectAssertLeasingListCount(AssertLeasing assertLeasing);

    int updateAssertLeasing(AssertLeasing assertLeasing) throws DataAccessException;

    AssertLeasing selectAssertLeasingByAssertNum(AssertLeasing assertLeasing);

    List<AssertLeasing> selectAssertLeasingByPropertyLeasingNum(String property_leasing_num);

    PropertyLeasing findPropertyLeasingByNum(String property_leasing_num);

    CountEmpty findEmptyPropertyLeasingCount();

    Page<PropertyLeasing> findPropertyLeasingByStateList(Integer page, Integer rows, String depositState, String rentalState, String estateState, String waterState, String powerState, String community_name);
}
