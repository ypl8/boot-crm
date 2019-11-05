package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.dao.AssertDepositDao;
import cn.kfqjtdqb.core.dao.CustomerDao;
import cn.kfqjtdqb.core.service.AssertDepositService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("assertDepositService")
@Transactional
public class AssertDepositServiceImpl implements AssertDepositService {


    @Autowired
    private AssertDepositDao assertDepositDao;

    @Override
    public Page<AssertDeposit> findAssertDepositList(Integer page, Integer rows, String property_leasing_num,String  state) {
        AssertDeposit assertDeposit = new AssertDeposit();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertDeposit.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(state)) {
            assertDeposit.setState(state);
        }

        //当前页
        assertDeposit.setStart((page - 1) * rows);
        //每页数
        assertDeposit.setRows(rows);
        //查询客户列表
        List<AssertDeposit> assertInfols = assertDepositDao.selectAssertDepositList(assertDeposit);
        //查询客户列表总记录数
        Integer count = assertDepositDao.selectAssertDepositListCount(assertDeposit);
        //创建Page返回对象
        Page<AssertDeposit> result = new Page<>();
        result.setPage(page);
        result.setRows(assertInfols);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertDeposit getAssertDepositById(Long id) {
        return assertDepositDao.getAssertDepositById(id);
    }

    @Override
    public void updateAssertDeposit(AssertDeposit assertDeposit) {
        assertDepositDao.updateAssertDeposit(assertDeposit);
    }

    @Override
    public void deleteAssertDeposit(Long id) {
        assertDepositDao.deleteAssertDeposit(id);
    }

    @Override
    public int createAssertDeposit(AssertDeposit assertDeposit) {
        return assertDepositDao.addAssertDeposit(assertDeposit);
    }

    @Override
    public AssertDeposit getAssertDepositByLeasingNum(String property_leasing_num) {
        return assertDepositDao.getAssertDepositByLeasingNum(property_leasing_num);
    }

    @Override
    public void deleteAssertDepositByLeasingNum(String property_leasing_num) {
        assertDepositDao.deleteAssertDepositByLeasingNum(property_leasing_num);
    }


}
