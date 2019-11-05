package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertWater;
import cn.kfqjtdqb.core.dao.AssertWaterDao;
import cn.kfqjtdqb.core.service.AssertWaterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AssertWaterService")
@Transactional
public class AssertWaterServiceImpl implements AssertWaterService {

    @Autowired
    private AssertWaterDao assertWaterDao;

    @Override
    public Page<AssertWater> selectAssertWaterList(Integer page, Integer rows, String property_leasing_num, String assert_num) {
        AssertWater AssertWater = new AssertWater();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            AssertWater.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            AssertWater.setAssert_num(assert_num);
        }
        //当前页
        AssertWater.setStart((page - 1) * rows);
        //每页数
        AssertWater.setRows(rows);
        //查询客户列表
        List<AssertWater> AssertWaters = assertWaterDao.selectAssertWaterList(AssertWater);
        //查询客户列表总记录数
        Integer count = assertWaterDao.selectAssertWaterListCount(AssertWater);
        //创建Page返回对象
        Page<AssertWater> result = new Page<>();
        result.setPage(page);
        result.setRows(AssertWaters);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public AssertWater getAssertWaterById(Long id) {
        return assertWaterDao.getAssertWaterById(id);
    }

    @Override
    public void updateAssertWater(AssertWater AssertWater) {
        assertWaterDao.updateAssertWater(AssertWater);
    }

    @Override
    public void deleteAssertWater(Long id) {
        assertWaterDao.deleteAssertWater(id);
    }

    @Override
    public List<AssertWater> selectAssertWaterListByAssertNum(String property_leasing_num, String assert_num) {
        AssertWater assertWater = new AssertWater();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertWater.setProperty_leasing_num(property_leasing_num);
        }

        if (StringUtils.isNotBlank(assert_num)) {
            assertWater.setAssert_num(assert_num);
        }
        List<AssertWater> assertWaters = assertWaterDao.selectAssertWaterList(assertWater);

        return assertWaters;
    }

    @Override
    public int createAssertWater(AssertWater AssertWater)  {
        return assertWaterDao.addAssertWater(AssertWater);
    }


}
