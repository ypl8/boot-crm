package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertLeasing;
import cn.kfqjtdqb.core.dao.AssertLeasingDao;
import cn.kfqjtdqb.core.service.AssertLeasingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("assertLeasingService")
@Transactional
public class AssertLeasingServiceImpl implements AssertLeasingService {

    @Autowired
    private AssertLeasingDao assertLeasingDao;

    @Override
    public Page<AssertLeasing> selectAssertLeasingList(Integer page, Integer rows, String property_leasing_num, String assert_num,String status) {
        AssertLeasing assertLeasing = new AssertLeasing();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertLeasing.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            assertLeasing.setAssert_num(assert_num);
        }

        if (StringUtils.isNotBlank(status)) {
            assertLeasing.setStatus(status);
        }
        //当前页
        assertLeasing.setStart((page - 1) * rows);
        //每页数
        assertLeasing.setRows(rows);
        //查询客户列表
        List<AssertLeasing> AssertLeasings = assertLeasingDao.selectAssertLeasingList(assertLeasing);
        //查询客户列表总记录数
        Integer count = assertLeasingDao.selectAssertLeasingListCount(assertLeasing);
        //创建Page返回对象
        Page<AssertLeasing> result = new Page<>();
        result.setPage(page);
        result.setRows(AssertLeasings);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertLeasing getAssertLeasingById(Long id) {
        return assertLeasingDao.getAssertLeasingById(id);
    }

    @Override
    public void updateAssertLeasing(AssertLeasing AssertLeasing) {
        assertLeasingDao.updateAssertLeasing(AssertLeasing);
    }

    @Override
    public void deleteAssertLeasing(Long id) {
        assertLeasingDao.deleteAssertLeasing(id);
    }

    @Override
    public List<AssertLeasing> selectAssertLeasingListByAssertNum(String property_leasing_num, String assert_num) {
        AssertLeasing assertLeasing = new AssertLeasing();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertLeasing.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            assertLeasing.setAssert_num(assert_num);
        }
        List<AssertLeasing> assertLeasings = assertLeasingDao.selectAssertLeasingList(assertLeasing);

        return assertLeasings;
    }

    @Override
    public int createAssertLeasing(AssertLeasing AssertLeasing)  {
        return assertLeasingDao.addAssertLeasing(AssertLeasing);
    }

    @Override
    public void deleteAssertLeasingByPropertyLeasingNum(String property_leasing_num) throws Exception {
        assertLeasingDao.deleteAssertLeasingByPropertyLeasingNum(property_leasing_num);
    }


}
