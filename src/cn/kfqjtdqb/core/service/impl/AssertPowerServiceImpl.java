package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertPower;
import cn.kfqjtdqb.core.bean.AssertWater;
import cn.kfqjtdqb.core.dao.AssertPowerDao;
import cn.kfqjtdqb.core.service.AssertPowerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("assertPowerService")
@Transactional
public class AssertPowerServiceImpl implements AssertPowerService {

    @Autowired
    private AssertPowerDao assertPowerDao;

    @Override
    public Page<AssertPower> selectAssertPowerList(Integer page, Integer rows, String property_leasing_num, String assert_num) {
        AssertPower AssertPower = new AssertPower();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            AssertPower.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            AssertPower.setAssert_num(assert_num);
        }
        //当前页
        AssertPower.setStart((page - 1) * rows);
        //每页数
        AssertPower.setRows(rows);
        //查询客户列表
        List<AssertPower> AssertPowers = assertPowerDao.selectAssertPowerList(AssertPower);
        //查询客户列表总记录数
        Integer count = assertPowerDao.selectAssertPowerListCount(AssertPower);
        //创建Page返回对象
        Page<AssertPower> result = new Page<>();
        result.setPage(page);
        result.setRows(AssertPowers);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertPower getAssertPowerById(Long id) {
        return assertPowerDao.getAssertPowerById(id);
    }

    @Override
    public void updateAssertPower(AssertPower AssertPower) {
        assertPowerDao.updateAssertPower(AssertPower);
    }

    @Override
    public void deleteAssertPower(Long id) {
        assertPowerDao.deleteAssertPower(id);
    }

    @Override
    public List<AssertPower> selectAssertPowerListByAssertNum(String property_leasing_num, String assert_num) {
        AssertPower assertPower = new AssertPower();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertPower.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            assertPower.setAssert_num(assert_num);
        }
        List<AssertPower> assertWaters = assertPowerDao.selectAssertPowerList(assertPower);
        return assertWaters;
    }

    @Override
    public int createAssertPower(AssertPower AssertPower) {
        return assertPowerDao.addAssertPower(AssertPower);
    }


}
