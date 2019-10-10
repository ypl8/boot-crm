package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.PropertyLeasing;

import java.util.List;

public interface PropertyLeasingService {

    public Page<PropertyLeasing> findPropertyLeasingList(Integer page, Integer rows,
                                                 String property_leasing_num);
    PropertyLeasing getPropertyLeasingById(Long id);
    void updatePropertyLeasing(PropertyLeasing propertyLeasing);
    void deletePropertyLeasing(Long id);
    int  addPropertyLeasing(PropertyLeasing propertyLeasing);
}
