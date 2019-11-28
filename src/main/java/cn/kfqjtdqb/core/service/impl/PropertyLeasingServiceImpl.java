package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.dao.*;
import cn.kfqjtdqb.core.service.AssertEstateService;
import cn.kfqjtdqb.core.service.PropertyLeasingService;
import cn.kfqjtdqb.core.utils.DateUtils;
import cn.kfqjtdqb.core.utils.RentUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("propertyLeasingService")
@Transactional
public class PropertyLeasingServiceImpl implements PropertyLeasingService {

    @Autowired
    private PropertyLeasingDao propertyLeasingDao;

    @Autowired
    private SqlSession sqlSession;

    @Override
    public Page<PropertyLeasing> findPropertyLeasingList(Integer page, Integer rows, String property_leasing_num, String collect_rent_way, String collect_rate_way, String property_leasing_state, String community_name, String assert_num, String property_leasing_type, String status) {
        PropertyLeasing propertyLeasing = new PropertyLeasing();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(collect_rent_way)) {
            propertyLeasing.setCollect_rent_way(collect_rent_way);
        }

        if (StringUtils.isNotBlank(collect_rate_way)) {
            propertyLeasing.setCollect_rate_way(collect_rate_way);
        }

        if (StringUtils.isNotBlank(collect_rate_way)) {
            propertyLeasing.setCollect_rate_way(collect_rate_way);
        }

        if (StringUtils.isNotBlank(property_leasing_state)) {
            propertyLeasing.setProperty_leasing_state(property_leasing_state);
        }

        if (StringUtils.isNotBlank(community_name)) {
            propertyLeasing.setCommunity_name(community_name);
        }
        if (StringUtils.isNotBlank(assert_num)) {
            propertyLeasing.setAssert_num(assert_num);
        }

        if (StringUtils.isNotBlank(property_leasing_type)) {
            propertyLeasing.setProperty_leasing_type(property_leasing_type);
        }

        if (StringUtils.isNotBlank(status)) {
            propertyLeasing.setStatus(status);
        }
        //当前页
        propertyLeasing.setStart((page - 1) * rows);
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
        //创建合同之前进行操作
        return propertyLeasingDao.addPropertyLeasing(propertyLeasing);
    }

    @Override
    public int addAssertLeasing(AssertLeasing assertLeasing) {
        return propertyLeasingDao.addAssertLeasing(assertLeasing);
    }

    @Override
    public void deleteAssertLeasingByPropertyLeasingNum(String property_leasing_num) throws Exception {
        propertyLeasingDao.deleteAssertLeasingByPropertyLeasingNum(property_leasing_num);
    }

    @Override
    public void deleteAssertLeasingByAssertNumAndPropertyLeasingNum(String property_leasing_num, String assert_num) throws Exception {
        AssertLeasing assertLeasing = new AssertLeasing();
        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertLeasing.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            assertLeasing.setAssert_num(assert_num);
        }
        propertyLeasingDao.deleteAssertLeasingByAssertNumAndPropertyLeasingNum(assertLeasing);

    }

    @Override
    public PropertyLeasing findPropertyLeasingWithAssert(Long id) {
        return propertyLeasingDao.findPropertyLeasingWithAssert(id);
    }

    @Override
    public Integer selectAssertLeasingListCount(AssertLeasing assertLeasing) {
        return propertyLeasingDao.selectAssertLeasingListCount(assertLeasing);
    }

    @Override
    public int updateAssertLeasing(AssertLeasing assertLeasing) {
        return propertyLeasingDao.updateAssertLeasing(assertLeasing);
    }

    @Override
    public AssertLeasing selectAssertLeasingByAssertNum(AssertLeasing assertLeasing) {
        return propertyLeasingDao.selectAssertLeasingByAssertNum(assertLeasing);
    }

    @Override
    public List<AssertLeasing> selectAssertLeasingByPropertyLeasingNum(String property_leasing_num) {
        return propertyLeasingDao.selectAssertLeasingByPropertyLeasingNum(property_leasing_num);
    }

    @Override
    public PropertyLeasing findPropertyLeasingByNum(String property_leasing_num) {
        return propertyLeasingDao.findPropertyLeasingByLeasingNum(property_leasing_num);
    }

    @Override
    public CountEmpty findEmptyPropertyLeasingCount() {
        return propertyLeasingDao.findEmptyPropertyLeasingCount();
    }

    @Override
    public Page<PropertyLeasing> findPropertyLeasingByStateList(Integer page, Integer rows, String depositState, String rentalState, String estateState, String waterState, String powerState, String community_name) {
        PropertyLeasing propertyLeasing = new PropertyLeasing();

        if (StringUtils.isNotBlank(community_name)) {
            propertyLeasing.setCommunity_name(community_name);
        }

        if (StringUtils.isNotBlank(depositState)) {
            propertyLeasing.setDepositState(depositState);
        }

        if (StringUtils.isNotBlank(rentalState)) {
            propertyLeasing.setRentalState(rentalState);
        }

        if (StringUtils.isNotBlank(estateState)) {
            propertyLeasing.setEstateState(estateState);
        }

        if (StringUtils.isNotBlank(waterState)) {
            propertyLeasing.setWaterState(waterState);
        }

        if (StringUtils.isNotBlank(powerState)) {
            propertyLeasing.setPowerState(powerState);
        }

        if (StringUtils.isNotBlank(community_name)) {
            propertyLeasing.setCommunity_name(community_name);
        }


        //当前页
        propertyLeasing.setStart((page - 1) * rows);
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
    public void addPropertyLeasingAll(List<PropertyLeasing> propertyLeasings) {
        for (PropertyLeasing propertyLeasing : propertyLeasings) {
            propertyLeasingDao.addPropertyLeasing(propertyLeasing);
        }
    }

    @Override
    public List<PropertyLeasing> findPropertyLeasingList(String property_leasing_num, String collect_rent_way, String collect_rate_way, String property_leasing_state, String community_name, String assert_num, String property_leasing_type, String status) {
        PropertyLeasing propertyLeasing = new PropertyLeasing();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            propertyLeasing.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(collect_rent_way)) {
            propertyLeasing.setCollect_rent_way(collect_rent_way);
        }

        if (StringUtils.isNotBlank(collect_rate_way)) {
            propertyLeasing.setCollect_rate_way(collect_rate_way);
        }

        if (StringUtils.isNotBlank(collect_rate_way)) {
            propertyLeasing.setCollect_rate_way(collect_rate_way);
        }

        if (StringUtils.isNotBlank(property_leasing_state)) {
            propertyLeasing.setProperty_leasing_state(property_leasing_state);
        }

        if (StringUtils.isNotBlank(community_name)) {
            propertyLeasing.setCommunity_name(community_name);
        }
        if (StringUtils.isNotBlank(assert_num)) {
            propertyLeasing.setAssert_num(assert_num);
        }

        if (StringUtils.isNotBlank(property_leasing_type)) {
            propertyLeasing.setProperty_leasing_type(property_leasing_type);
        }

        if (StringUtils.isNotBlank(status)) {
            propertyLeasing.setStatus(status);
        }


        return propertyLeasingDao.selectPropertyLeasingList(propertyLeasing);

    }


}
