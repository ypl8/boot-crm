package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.Case;
import cn.kfqjtdqb.core.dao.AssertInfolDao;
import cn.kfqjtdqb.core.dao.CaseDao;
import cn.kfqjtdqb.core.service.AssertInfolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("assertInfolService")
@Transactional
public class AssertInfolServiceImpl implements AssertInfolService {

    // 定义dao属性
    @Autowired
    private AssertInfolDao assertInfolDao;

    @Override
    public Page<AssertInfol> findAssertInfolList(Integer page, Integer rows, String assert_num) {

        AssertInfol assertInfol = new AssertInfol();
        //判断客户名称(公司名称)
        if(StringUtils.isNotBlank(assert_num)){
            assertInfol.setAssert_num(assert_num);
        }
        //当前页
        assertInfol.setStart((page-1) * rows) ;
        //每页数
        assertInfol.setRows(rows);
        //查询客户列表
        List<AssertInfol> assertInfols = assertInfolDao.selectAssertInfolList(assertInfol);
        //查询客户列表总记录数
        Integer count = assertInfolDao.selectAssertInfolListCount(assertInfol);
        //创建Page返回对象
        Page<AssertInfol> result = new Page<>();
        result.setPage(page);
        result.setRows(assertInfols);
        result.setSize(rows);
        result.setTotal(count);
        return result;

    }

    @Override
    public AssertInfol getAssertInfolById(Long id) {
        return assertInfolDao.getAssertInfolById(id);
    }

    @Override
    public void updateAssertInfol(AssertInfol assertInfol) {
        assertInfolDao.updateAssertInfol(assertInfol);
    }

    @Override
    public void deleteAssertInfol(Long id) {
        assertInfolDao.deleteAssertInfol(id);
    }

    @Override
    public int createAssertInfol(AssertInfol assertInfol) {
        return assertInfolDao.addAssertInfol(assertInfol);
    }

    @Override
    public List<AssertInfol> findAssertInfolIdleStateList() {
        AssertInfol assertInfol = new AssertInfol();
        assertInfol.setFloor_state('2');
        List<AssertInfol> assertInfols = assertInfolDao.selectAssertInfolList(assertInfol);
        return assertInfols;
    }
}
