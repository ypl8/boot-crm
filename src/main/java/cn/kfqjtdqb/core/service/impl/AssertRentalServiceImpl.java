package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertRental;
import cn.kfqjtdqb.core.dao.AssertDepositDao;
import cn.kfqjtdqb.core.dao.AssertRentalDao;
import cn.kfqjtdqb.core.service.AssertDepositService;
import cn.kfqjtdqb.core.service.AssertRentalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("assertRentalService")
@Transactional
public class AssertRentalServiceImpl implements AssertRentalService {

    @Autowired
    private AssertRentalDao assertRentalDao;

    @Override
    public Page<AssertRental> selectAssertRentalList(Integer page, Integer rows, String property_leasing_num, String state, String status) {
        AssertRental assertRental = new AssertRental();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertRental.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            assertRental.setState(state);
        }

        if (StringUtils.isNotBlank(status)) {
            assertRental.setStatus(status);
        }

        //当前页
        assertRental.setStart((page - 1) * rows);
        //每页数
        assertRental.setRows(rows);
        //查询客户列表
        List<AssertRental> assertRentals = assertRentalDao.selectAssertRentalList(assertRental);
        //查询客户列表总记录数
        Integer count = assertRentalDao.selectAssertRentalListCount(assertRental);
        //创建Page返回对象
        Page<AssertRental> result = new Page<>();
        result.setPage(page);
        result.setRows(assertRentals);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertRental getAssertRentalById(Long id) {
        return assertRentalDao.getAssertRentalById(id);
    }

    @Override
    public void updateAssertRental(AssertRental assertRental) {
        assertRentalDao.updateAssertRental(assertRental);
    }

    @Override
    public void deleteAssertRental(Long id) {
        assertRentalDao.deleteAssertRental(id);
    }

    @Override
    public int createAssertRental(AssertRental assertRental) {
        return assertRentalDao.addAssertRental(assertRental);
    }

    @Override
    public List<AssertRental> getAssertRentalByLeasingNum(String property_leasing_num) {
        return assertRentalDao.getAssertRentalByLeasingNum(property_leasing_num);
    }

    @Override
    public BigDecimal getAssertRentalCountByLeasingNum(String property_leasing_num) {
        return assertRentalDao.getAssertRentalCountByLeasingNum(property_leasing_num);
    }

    @Override
    public void deleteAssertRentalByPropertyLeasingNum(String property_leasing_num) {
        assertRentalDao.deleteAssertRentalByPropertyLeasingNum(property_leasing_num);
    }

    @Override
    public List<AssertRental> selectAssertRentalAllList(String property_leasing_num, String state,String status) {
        AssertRental assertRental = new AssertRental();
        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertRental.setProperty_leasing_num(property_leasing_num);
        }
        if (StringUtils.isNotBlank(state)) {
            assertRental.setState(state);
        }
        if (StringUtils.isNotBlank(status)) {
            assertRental.setStatus(status);
        }
        List<AssertRental> assertRentals = assertRentalDao.selectAssertRentalList(assertRental);
        return assertRentals;
    }
}
