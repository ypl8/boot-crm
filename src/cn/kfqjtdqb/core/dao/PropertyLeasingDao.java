package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.AssertLeasing;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PropertyLeasingDao {

    List<PropertyLeasing> selectPropertyLeasingList(PropertyLeasing propertyLeasing);

    Integer selectPropertyLeasingListCount(PropertyLeasing propertyLeasing);

    PropertyLeasing getPropertyLeasingById(Long id);

    void updatePropertyLeasing(PropertyLeasing propertyLeasing) throws DataAccessException;;

    void deletePropertyLeasing(Long id);

    int addPropertyLeasing(PropertyLeasing propertyLeasing)throws DataAccessException;;

    int addAssertLeasing(AssertLeasing assertLeasing)throws DataAccessException;;

    PropertyLeasing findPropertyLeasingWithAssert(Long id);

    Integer selectAssertLeasingListCount(AssertLeasing assertLeasing);

    int updateAssertLeasing(AssertLeasing assertLeasing) throws DataAccessException;;

    AssertLeasing selectAssertLeasingByAssertNum(AssertLeasing assertLeasing);

    PropertyLeasing  findPropertyLeasingByLeasingNum(String property_leasing_num);
    
    void  updateState();

    List<AssertLeasing> selectAssertLeasingByPropertyLeasingNum(String property_leasing_num);
}
