package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertWaterRent;
import cn.kfqjtdqb.core.dao.AssertWaterRentDao;
import cn.kfqjtdqb.core.service.AssertWaterRentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("assertWaterRentService")
@Transactional
public class AssertWaterRentServiceImpl implements AssertWaterRentService {

    @Autowired
    private AssertWaterRentDao assertWaterRentDao;

    @Override
    public Page<AssertWaterRent> selectAssertWaterRentList(Integer page, Integer rows, String property_leasing_num, String assert_num,String state) {
        AssertWaterRent AssertWaterRent = new AssertWaterRent();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            AssertWaterRent.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            AssertWaterRent.setState(state);
        }
        if (StringUtils.isNotBlank(assert_num)) {
            AssertWaterRent.setAssert_num(assert_num);
        }

        //当前页
        AssertWaterRent.setStart((page - 1) * rows);
        //每页数
        AssertWaterRent.setRows(rows);
        //查询客户列表
        List<AssertWaterRent> AssertWaterRents = assertWaterRentDao.selectAssertWaterRentList(AssertWaterRent);
        //查询客户列表总记录数
        Integer count = assertWaterRentDao.selectAssertWaterRentListCount(AssertWaterRent);
        //创建Page返回对象
        Page<AssertWaterRent> result = new Page<>();
        result.setPage(page);
        result.setRows(AssertWaterRents);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertWaterRent getAssertWaterRentById(Long id) {
        return assertWaterRentDao.getAssertWaterRentById(id);
    }

    @Override
    public void updateAssertWaterRent(AssertWaterRent AssertWaterRent) {
        assertWaterRentDao.updateAssertWaterRent(AssertWaterRent);
    }

    @Override
    public void deleteAssertWaterRent(Long id) {
        assertWaterRentDao.deleteAssertWaterRent(id);
    }

    @Override
    public int createAssertWaterRent(AssertWaterRent AssertWaterRent) {
        return assertWaterRentDao.addAssertWaterRent(AssertWaterRent);
    }

    @Override
    public List<AssertWaterRent> getAssertWaterRentByLeasingNum(String property_leasing_num) {
        return assertWaterRentDao.getAssertWaterRentByLeasingNum(property_leasing_num);
    }

    @Override
    public BigDecimal getAssertWaterRentCountByLeasingNum(AssertWaterRent assertWaterRent) {
        return assertWaterRentDao.getAssertWaterRentCountByLeasingNum(assertWaterRent);
    }
}
