package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertEstate;
import cn.kfqjtdqb.core.dao.AssertEstateDao;
import cn.kfqjtdqb.core.service.AssertEstateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("assertEstateService")
@Transactional
public class AssertEstateServiceImpl implements AssertEstateService {

    @Autowired
    private AssertEstateDao AssertEstateDao;

    @Override
    public Page<AssertEstate> selectAssertEstateList(Integer page, Integer rows, String property_leasing_num, String state,String status) {
        AssertEstate AssertEstate = new AssertEstate();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            AssertEstate.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            AssertEstate.setState(state);
        }

        if (StringUtils.isNotBlank(status)) {
            AssertEstate.setStatus(status);
        }
        //当前页
        AssertEstate.setStart((page - 1) * rows);
        //每页数
        AssertEstate.setRows(rows);
        //查询客户列表
        List<AssertEstate> AssertEstates = AssertEstateDao.selectAssertEstateList(AssertEstate);
        //查询客户列表总记录数
        Integer count = AssertEstateDao.selectAssertEstateListCount(AssertEstate);
        //创建Page返回对象
        Page<AssertEstate> result = new Page<>();
        result.setPage(page);
        result.setRows(AssertEstates);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertEstate getAssertEstateById(Long id) {
        return AssertEstateDao.getAssertEstateById(id);
    }

    @Override
    public void updateAssertEstate(AssertEstate AssertEstate) {
        AssertEstateDao.updateAssertEstate(AssertEstate);
    }

    @Override
    public void deleteAssertEstate(Long id) {
        AssertEstateDao.deleteAssertEstate(id);
    }

    @Override
    public int createAssertEstate(AssertEstate AssertEstate) {
        return AssertEstateDao.addAssertEstate(AssertEstate);
    }

    @Override
    public List<AssertEstate> getAssertEstateByLeasingNum(String property_leasing_num) {
        return AssertEstateDao.getAssertEstateByLeasingNum(property_leasing_num);
    }

    @Override
    public BigDecimal getAssertEstateCountByLeasingNum(String property_leasing_num) {
        return AssertEstateDao.getAssertEstateCountByLeasingNum(property_leasing_num);
    }

    @Override
    public void deleteAssertEstateByPropertyLeasingNum(String property_leasing_num) {
        AssertEstateDao.deleteAssertEstateByPropertyLeasingNum(property_leasing_num);
    }

    @Override
    public List<AssertEstate> selectAssertEstateList(String property_leasing_num, String state,String status) {
        AssertEstate assertEstate = new AssertEstate();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertEstate.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            assertEstate.setState(state);
        }

        if (StringUtils.isNotBlank(status)) {
            assertEstate.setStatus(state);
        }
        return AssertEstateDao.selectAssertEstateList(assertEstate);
    }
}
