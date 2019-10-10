package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.PropertyLeasing;

import java.util.List;

public interface PropertyLeasingDao {
    List<PropertyLeasing> selectPropertyLeasingList(PropertyLeasing propertyLeasing);
    Integer selectPropertyLeasingListCount(PropertyLeasing propertyLeasing);
    PropertyLeasing getPropertyLeasingById(Long id);
    void updatePropertyLeasing(PropertyLeasing propertyLeasing);
    void deletePropertyLeasing(Long id);
    int  addPropertyLeasing(PropertyLeasing propertyLeasing);
}
