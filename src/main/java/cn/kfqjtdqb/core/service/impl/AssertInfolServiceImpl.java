package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.AssertInfolTotal;

import cn.kfqjtdqb.core.dao.AssertInfolDao;

import cn.kfqjtdqb.core.service.AssertInfolService;
import cn.kfqjtdqb.core.utils.BigDecimalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("assertInfolService")
@Transactional
public class AssertInfolServiceImpl implements AssertInfolService {

    // 定义dao属性
    @Autowired
    private AssertInfolDao assertInfolDao;

    @Override
    public Page<AssertInfol> findAssertInfolList(Integer page, Integer rows, String assert_num, String floor_state, String community_name, String property_leasing_num) {

        AssertInfol assertInfol = new AssertInfol();
        //判断客户名称(公司名称)
        if (StringUtils.isNotBlank(assert_num)) {
            assertInfol.setAssert_num(assert_num);
        }
        //判断客户名称(公司名称)
        if (StringUtils.isNotBlank(floor_state)) {
            assertInfol.setFloor_state(floor_state);
        }
        //判断客户名称(公司名称)
        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }
        //合同编号
        if (StringUtils.isNotBlank(property_leasing_num)) {
            assertInfol.setProperty_leasing_num(property_leasing_num);
        }

        //当前页
        assertInfol.setStart((page - 1) * rows);
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
    public void updateAssertInfol(AssertInfol assertInfol) throws DataAccessException {
        assertInfolDao.updateAssertInfol(assertInfol);
    }

    @Override
    public void deleteAssertInfol(Long id) {
        assertInfolDao.deleteAssertInfol(id);
    }

    @Override
    public int createAssertInfol(AssertInfol assertInfol) throws DataAccessException {
        return assertInfolDao.addAssertInfol(assertInfol);
    }

    @Override
    public List<AssertInfol> findAssertInfolIdleStateList() {
        AssertInfol assertInfol = new AssertInfol();
        assertInfol.setFloor_state("2");
        List<AssertInfol> assertInfols = assertInfolDao.selectAssertInfolList(assertInfol);
        return assertInfols;
    }

    @Override
    public AssertInfol findAssertWithPropertyLeasing(Long id) {
        return assertInfolDao.findAssertWithPropertyLeasing(id);
    }

    @Override
    public AssertInfol getAssertInfolByAssertNum(String assert_num) {
        return assertInfolDao.getAssertInfolByAssertNum(assert_num);
    }

    @Override
    public List<AssertInfol> findAssertInfolListAll(String assert_num, String floor_state, String community_name) {
        AssertInfol assertInfol = new AssertInfol();

        if (StringUtils.isNotBlank(assert_num)) {
            assertInfol.setAssert_num(assert_num);
        }

        if (StringUtils.isNotBlank(floor_state)) {
            assertInfol.setFloor_state(floor_state);
        }

        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }

        List<AssertInfol> assertInfols = assertInfolDao.selectAssertInfolList(assertInfol);
        return assertInfols;
    }

    @Override
    public Page<AssertInfolTotal> findAssertInfolListByCommunity(Integer page, Integer rows, String community_name) {

        AssertInfol assertInfol = new AssertInfol();
        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }
        //当前页
        assertInfol.setStart((page - 1) * rows);
        //每页数
        assertInfol.setRows(rows);
        //查询客户列表
        List<AssertInfolTotal> assertInfols = assertInfolDao.selectAssertInfolTotalList(assertInfol);

        for (int i = 0; i < assertInfols.size(); i++) {
            Integer countSum = assertInfols.get(i).getCountSum();
            if (countSum != null && countSum != 0) {
                Integer countRented = assertInfols.get(i).getCountRented();
                if (countRented == null) {
                    assertInfols.get(i).setAssertRate(BigDecimal.ZERO);
                } else {
                    assertInfols.get(i).setAssertRate(new BigDecimal(countRented * 1.0 + "").divide(new BigDecimal(countSum + ""),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100 + "")));
                    /* assertInfols.get(i).setAssertRate(BigDecimalUtils.getDouble((countRented*1.0/countSum))*100);*/
                }

            } else {
                assertInfols.get(i).setAssertRate(new BigDecimal("100"));
            }

        }
        //查询客户列表总记录数
        Integer count = assertInfolDao.selectAssertInfolTotalListCount(assertInfol);
        //创建Page返回对象
        Page<AssertInfolTotal> result = new Page<>();
        result.setPage(page);
        result.setRows(assertInfols);
        result.setSize(rows);
        result.setTotal(count);
        return result;
        // assertInfolTotal.setAssertRate(Decim);


    }

    @Override
    public AssertInfol findAssertInfolListByCommunityName(String community_name, String building_num, String location) {
        AssertInfol assertInfol = new AssertInfol();
        if (StringUtils.isNotBlank(building_num)) {
            assertInfol.setBuilding_num(building_num);
        }

        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }
        if (StringUtils.isNotBlank(location)) {
            assertInfol.setRoom_number(location);
        }
        return assertInfolDao.findAssertInfolListByCommunityName(assertInfol);
    }

    @Override
    public List<String> findAllCommunityName() {
        return assertInfolDao.findAllCommunityName();
    }

    @Override
    public List<String> findAllBuildNum(String community_name, String floor_state) {
        AssertInfol assertInfol = new AssertInfol();

        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }
        if (StringUtils.isNotBlank(floor_state)) {
            assertInfol.setFloor_state(floor_state);
        }
        return assertInfolDao.findAllBuildNum(assertInfol);
    }

    @Override
    public List<String> findAllRoomNum(String community_name, String building_num, String floor_state) {
        AssertInfol assertInfol = new AssertInfol();

        if (StringUtils.isNotBlank(building_num)) {
            assertInfol.setBuilding_num(building_num);
        }

        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }
        if (StringUtils.isNotBlank(floor_state)) {
            assertInfol.setFloor_state(floor_state);
        }
        //
        return assertInfolDao.findAllRoomNum(assertInfol);
    }

    @Override
    public Integer findEmptyAssertCount(String floor_state) {
        AssertInfol assertInfol = new AssertInfol();
        //判断客户名称(公司名称)
        if (StringUtils.isNotBlank(floor_state)) {
            assertInfol.setFloor_state(floor_state);
        }
        Integer count = assertInfolDao.selectAssertInfolListCount(assertInfol);

        return count;
    }

    @Override
    public List<AssertInfolTotal> findAssertInfolListByCommunity(String community_name) {
        AssertInfol assertInfol = new AssertInfol();
        if (StringUtils.isNotBlank(community_name)) {
            assertInfol.setCommunity_name(community_name);
        }

        //查询客户列表
        List<AssertInfolTotal> assertInfols = assertInfolDao.selectAssertInfolTotalList(assertInfol);

        for (int i = 0; i < assertInfols.size(); i++) {
            Integer countSum = assertInfols.get(i).getCountSum();
            if (countSum != null && countSum != 0) {
                Integer countRented = assertInfols.get(i).getCountRented();
                if (countRented == null) {
                    assertInfols.get(i).setAssertRate(BigDecimal.ZERO);
                } else {
                    assertInfols.get(i).setAssertRate(new BigDecimal(countRented * 1.0 + "").divide(new BigDecimal(countSum + ""),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100 + "")));
                }

            } else {
                assertInfols.get(i).setAssertRate(new BigDecimal("100"));
            }

        }

        return assertInfols;
    }


}
