package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.dao.AssertInfolDao;
import cn.kfqjtdqb.core.dao.PropertyLeasingDao;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("propertyLeasingServiceImpl")
@Transactional
public class PropertyLeasingServiceImpl  implements PropertyLeasingService {

    @Autowired
    private PropertyLeasingDao propertyLeasingDao;

    @Override
    public Page<PropertyLeasing> findPropertyLeasingList(Integer page, Integer rows, String property_leasing_num) {
        PropertyLeasing propertyLeasing = new PropertyLeasing();
        //判断客户名称(公司名称)
        if(StringUtils.isNotBlank(property_leasing_num)){
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
        }
        //当前页
        propertyLeasing.setStart((page-1) * rows) ;
        //每页数
        propertyLeasing.setRows(rows);
        //查询客户列表
        List<PropertyLeasing> propertyLeasings = propertyLeasingDao.selectPropertyLeasingList(propertyLeasing);
        //查询客户列表总记录数
        Integer count = propertyLeasingDao.selectPropertyLeasingListCount(propertyLeasing);
        //创建Page返回对象
        Page<PropertyLeasing> result = new Page<>();
        result.setPage(page);
        result.setRows(propertyLeasings);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public PropertyLeasing getPropertyLeasingById(Long id) {
        return propertyLeasingDao.getPropertyLeasingById(id);
    }

    @Override
    public void updatePropertyLeasing(PropertyLeasing propertyLeasing) {
        propertyLeasingDao.updatePropertyLeasing(propertyLeasing);
    }

    @Override
    public void deletePropertyLeasing(Long id) {
        propertyLeasingDao.deletePropertyLeasing(id);
    }

    @Override
    public int addPropertyLeasing(PropertyLeasing propertyLeasing) {
        return propertyLeasingDao.addPropertyLeasing(propertyLeasing);
    }



}
