package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.TotalEstate;
import cn.kfqjtdqb.core.dao.TotalEstateDao;
import cn.kfqjtdqb.core.service.TotalEstateService;
import cn.kfqjtdqb.core.utils.BigDecimalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("totalEstateService")
@Transactional
public class TotalEstateServiceImpl implements TotalEstateService {

    @Autowired
    private TotalEstateDao TotalEstateDao;

    @Override
    public Page<TotalEstate> selectTotalEstateList(Integer page, Integer rows, String property_leasing_num,String year_months,String community_name) {
        TotalEstate TotalEstate = new TotalEstate();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            TotalEstate.setProperty_leasing_num(property_leasing_num);
        }
        if (StringUtils.isNotBlank(year_months)) {
            TotalEstate.setYear_months(year_months);
        }
        if (StringUtils.isNotBlank(community_name)) {
            TotalEstate.setCommunity_name(community_name);
        }
        //当前页
        TotalEstate.setStart((page - 1) * rows);
        //每页数
        TotalEstate.setRows(rows);
        //查询客户列表
        List<TotalEstate> TotalEstates = TotalEstateDao.selectTotalEstateList(TotalEstate);
        //查询客户列表总记录数
        Integer count = TotalEstateDao.selectTotalEstateListCount(TotalEstate);
        //创建Page返回对象
        Page<TotalEstate> result = new Page<>();
        result.setPage(page);
        result.setRows(TotalEstates);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public TotalEstate getTotalEstateById(Long id) {
        return TotalEstateDao.getTotalEstateById(id);
    }

    @Override
    public void updateTotalEstate(TotalEstate TotalEstate) {
        TotalEstateDao.updateTotalEstate(TotalEstate);
    }

    @Override
    public void deleteTotalEstate(Long id) {
        TotalEstateDao.deleteTotalEstate(id);
    }

    @Override
    public int createTotalEstate(TotalEstate TotalEstate) {
        return TotalEstateDao.addTotalEstate(TotalEstate);
    }

    @Override
    public List<TotalEstate> getTotalEstateByLeasingNum(String property_leasing_num) {
        return TotalEstateDao.getTotalEstateByLeasingNum(property_leasing_num);
    }

    @Override
    public Page<TotalEstate> selectTotalEstateCommunityList(Integer page, Integer rows, String year_months, String community_name) {
        TotalEstate TotalEstate = new TotalEstate();
        if (StringUtils.isNotBlank(year_months)) {
            TotalEstate.setYear_months(year_months);
        }
        if (StringUtils.isNotBlank(community_name)) {
            TotalEstate.setCommunity_name(community_name);
        }
        //当前页
        TotalEstate.setStart((page - 1) * rows);
        //每页数
        TotalEstate.setRows(rows);
        //查询客户列表
        List<TotalEstate> TotalEstates = TotalEstateDao.selectTotalEstateCommunityList(TotalEstate);
        for (int i = 0; i < TotalEstates.size(); i++) {
            TotalEstates.get(i).setDifference(TotalEstates.get(i).getEstate() .subtract( TotalEstates.get(i).getReality_estate()));
            if (TotalEstates.get(i).getEstate() != null && TotalEstates.get(i).getEstate() .compareTo(BigDecimal.ZERO)==0 ) {
                TotalEstates.get(i).setCollectionRate(new BigDecimal("100"));
            } else {
                TotalEstates.get(i).setCollectionRate(TotalEstates.get(i).getReality_estate() .divide( TotalEstates.get(i).getEstate(),2,BigDecimal.ROUND_HALF_UP) .multiply(new BigDecimal("100")));
            }
        }
        //查询客户列表总记录数
        Integer count = TotalEstateDao.selectTotalEstateCommunityListCount(TotalEstate);
        //创建Page返回对象
        Page<TotalEstate> result = new Page<>();
        result.setPage(page);
        result.setRows(TotalEstates);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public Page<TotalEstate> selectTotalEstateCommunityByYearList(Integer page, Integer rows, String years, String community_name) {
        TotalEstate TotalEstate = new TotalEstate();
        if (StringUtils.isNotBlank(years)) {
            TotalEstate.setYear_months(years);
        }
        if (StringUtils.isNotBlank(community_name)) {
            TotalEstate.setCommunity_name(community_name);
        }
        //当前页
        TotalEstate.setStart((page - 1) * rows);
        //每页数
        TotalEstate.setRows(rows);
        //查询客户列表
        List<TotalEstate> TotalEstates = TotalEstateDao.selectTotalEstateCommunityByYearList(TotalEstate);
        for (int i = 0; i < TotalEstates.size(); i++) {
            TotalEstates.get(i).setDifference(TotalEstates.get(i).getEstate() .subtract( TotalEstates.get(i).getReality_estate()));
            if (TotalEstates.get(i).getEstate() != null && TotalEstates.get(i).getEstate().compareTo(BigDecimal.ZERO)==0) {
                TotalEstates.get(i).setCollectionRate(new BigDecimal("100"));
            } else {
                TotalEstates.get(i).setCollectionRate(TotalEstates.get(i).getReality_estate() .divide( TotalEstates.get(i).getEstate(),2,BigDecimal.ROUND_HALF_UP) .multiply(new BigDecimal("100")));
            }
        }
        //查询客户列表总记录数
        Integer count = TotalEstateDao.selectTotalEstateCommunityListByYearCount(TotalEstate);
        //创建Page返回对象
        Page<TotalEstate> result = new Page<>();
        result.setPage(page);
        result.setRows(TotalEstates);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public void deleteTotalEstateByPropertyLeasingNum(String property_leasing_num) {
        TotalEstateDao.deleteTotalEstateByPropertyLeasingNum(property_leasing_num);
    }


}
