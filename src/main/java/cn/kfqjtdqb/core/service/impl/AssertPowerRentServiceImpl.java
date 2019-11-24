package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertPowerRent;
import cn.kfqjtdqb.core.dao.AssertPowerRentDao;
import cn.kfqjtdqb.core.service.AssertPowerRentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("assertPowerRentService")
@Transactional
public class AssertPowerRentServiceImpl implements AssertPowerRentService {

    @Autowired
    private AssertPowerRentDao assertPowerRentDao;

    @Override
    public Page<AssertPowerRent> selectAssertPowerRentList(Integer page, Integer rows, String property_leasing_num, String assert_num,String state,String status) {
        AssertPowerRent AssertPowerRent = new AssertPowerRent();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            AssertPowerRent.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            AssertPowerRent.setState(state);
        }
        if (StringUtils.isNotBlank(assert_num)) {
            AssertPowerRent.setAssert_num(assert_num);
        }

        if (StringUtils.isNotBlank(status)) {
            AssertPowerRent.setStatus(status);
        }

        //当前页
        AssertPowerRent.setStart((page - 1) * rows);
        //每页数
        AssertPowerRent.setRows(rows);
        //查询客户列表
        List<AssertPowerRent> AssertPowerRents = assertPowerRentDao.selectAssertPowerRentList(AssertPowerRent);
        //查询客户列表总记录数
        Integer count = assertPowerRentDao.selectAssertPowerRentListCount(AssertPowerRent);
        //创建Page返回对象
        Page<AssertPowerRent> result = new Page<>();
        result.setPage(page);
        result.setRows(AssertPowerRents);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertPowerRent getAssertPowerRentById(Long id) {
        return assertPowerRentDao.getAssertPowerRentById(id);
    }

    @Override
    public void updateAssertPowerRent(AssertPowerRent AssertPowerRent) {
        assertPowerRentDao.updateAssertPowerRent(AssertPowerRent);
    }

    @Override
    public void deleteAssertPowerRent(Long id) {
        assertPowerRentDao.deleteAssertPowerRent(id);
    }

    @Override
    public int createAssertPowerRent(AssertPowerRent AssertPowerRent) {
        return assertPowerRentDao.addAssertPowerRent(AssertPowerRent);
    }

    @Override
    public List<AssertPowerRent> getAssertPowerRentByLeasingNum(String property_leasing_num) {
        return assertPowerRentDao.getAssertPowerRentByLeasingNum(property_leasing_num);
    }

    @Override
    public BigDecimal getAssertPowerRentCountByLeasingNum(AssertPowerRent assertPowerRent) {
        return assertPowerRentDao.getAssertPowerRentCountByLeasingNum(assertPowerRent);
    }

    @Override
    public void deleteAssertPowerRentByPropertyLeasingNum(String property_leasing_num) throws Exception {
        assertPowerRentDao.deleteAssertPowerRentByPropertyLeasingNum(property_leasing_num);
    }

    @Override
    public List<AssertPowerRent> selectAssertPowerRentList(String property_leasing_num, String assert_num, String state,String status) {
        AssertPowerRent AssertPowerRent = new AssertPowerRent();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            AssertPowerRent.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            AssertPowerRent.setState(state);
        }
        if (StringUtils.isNotBlank(assert_num)) {
            AssertPowerRent.setAssert_num(assert_num);
        }

        if (StringUtils.isNotBlank(status)) {
            AssertPowerRent.setStatus(status);
        }
        List<AssertPowerRent> AssertPowerRents = assertPowerRentDao.selectAssertPowerRentList(AssertPowerRent);
        return AssertPowerRents;
    }
}
