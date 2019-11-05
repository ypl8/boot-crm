package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.TotalRental;
import cn.kfqjtdqb.core.dao.TotalRentalDao;
import cn.kfqjtdqb.core.service.TotalRentalService;
import cn.kfqjtdqb.core.utils.BigDecimalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("totalRentalService")
@Transactional
public class TotalRentalServiceImpl implements TotalRentalService {

    @Autowired
    private TotalRentalDao TotalRentalDao;

    @Override
    public Page<TotalRental> selectTotalRentalList(Integer page, Integer rows, String property_leasing_num, String year_months, String community_name) {
        TotalRental TotalRental = new TotalRental();

        if (StringUtils.isNotBlank(property_leasing_num)) {
            TotalRental.setProperty_leasing_num(property_leasing_num);
        }
        if (StringUtils.isNotBlank(year_months)) {
            TotalRental.setYear_months(year_months);
        }
        if (StringUtils.isNotBlank(community_name)) {
            TotalRental.setCommunity_name(community_name);
        }
        //当前页
        TotalRental.setStart((page - 1) * rows);
        //每页数
        TotalRental.setRows(rows);
        //查询客户列表
        List<TotalRental> TotalRentals = TotalRentalDao.selectTotalRentalList(TotalRental);
        //查询客户列表总记录数
        Integer count = TotalRentalDao.selectTotalRentalListCount(TotalRental);
        //创建Page返回对象
        Page<TotalRental> result = new Page<>();
        result.setPage(page);
        result.setRows(TotalRentals);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public TotalRental getTotalRentalById(Long id) {
        return TotalRentalDao.getTotalRentalById(id);
    }

    @Override
    public void updateTotalRental(TotalRental TotalRental) {
        TotalRentalDao.updateTotalRental(TotalRental);
    }

    @Override
    public void deleteTotalRental(Long id) {
        TotalRentalDao.deleteTotalRental(id);
    }

    @Override
    public int createTotalRental(TotalRental TotalRental) {
        return TotalRentalDao.addTotalRental(TotalRental);
    }

    @Override
    public List<TotalRental> getTotalRentalByLeasingNum(String property_leasing_num) {
        return TotalRentalDao.getTotalRentalByLeasingNum(property_leasing_num);
    }

    @Override
    public Page<TotalRental> selectTotalRentalCommunityList(Integer page, Integer rows, String year_months, String community_name) {
        TotalRental TotalRental = new TotalRental();

        if (StringUtils.isNotBlank(year_months)) {
            TotalRental.setYear_months(year_months);
        }
        if (StringUtils.isNotBlank(community_name)) {
            TotalRental.setCommunity_name(community_name);
        }
        //当前页
        TotalRental.setStart((page - 1) * rows);
        //每页数
        TotalRental.setRows(rows);
        //查询客户列表
        List<TotalRental> TotalRentals = TotalRentalDao.selectTotalRentalCommunityList(TotalRental);
        for (int i = 0; i < TotalRentals.size(); i++) {
            TotalRentals.get(i).setDifference(TotalRentals.get(i).getRental() .subtract( TotalRentals.get(i).getReality_rental()));
            if (TotalRentals.get(i).getRental() != null && TotalRentals.get(i).getRental().compareTo(BigDecimal.ZERO)==0 ) {
                TotalRentals.get(i).setCollectionRate(new BigDecimal(100+""));
            } else {
                TotalRentals.get(i).setCollectionRate(TotalRentals.get(i).getReality_rental() .divide( TotalRentals.get(i).getRental(),2,BigDecimal.ROUND_HALF_UP) .multiply( new BigDecimal("100")));
            }
        }
        //查询客户列表总记录数
        Integer count = TotalRentalDao.selectTotalRentalListCommunityCount(TotalRental);
        //创建Page返回对象
        Page<TotalRental> result = new Page<>();
        result.setPage(page);
        result.setRows(TotalRentals);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public Page<TotalRental> selectTotalRentalCommunityByYearList(Integer page, Integer rows, String years, String community_name) {
        TotalRental TotalRental = new TotalRental();

        if (StringUtils.isNotBlank(years)) {
            TotalRental.setYear_months(years);
        }
        if (StringUtils.isNotBlank(community_name)) {
            TotalRental.setCommunity_name(community_name);
        }
        //当前页
        TotalRental.setStart((page - 1) * rows);
        //每页数
        TotalRental.setRows(rows);
        //查询客户列表
        List<TotalRental> TotalRentals = TotalRentalDao.selectTotalRentalCommunityByYearList(TotalRental);

        for (int i = 0; i < TotalRentals.size(); i++) {
            TotalRentals.get(i).setDifference(TotalRentals.get(i).getRental() .subtract( TotalRentals.get(i).getReality_rental()));
            if (TotalRentals.get(i).getRental() != null && TotalRentals.get(i).getRental() .compareTo(BigDecimal.ZERO)==0 ) {
                TotalRentals.get(i).setCollectionRate(new BigDecimal("100"));
            } else {
                TotalRentals.get(i).setCollectionRate((TotalRentals.get(i).getReality_rental() .divide( TotalRentals.get(i).getRental(),2,BigDecimal.ROUND_HALF_UP)) .multiply( new BigDecimal("100")));
            }

        }
        //查询客户列表总记录数
        Integer count = TotalRentalDao.selectTotalRentalListCommunityByYearCount(TotalRental);
        //创建Page返回对象
        Page<TotalRental> result = new Page<>();
        result.setPage(page);
        result.setRows(TotalRentals);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public void deleteTotalRentalByPropertyLeasingNum(String property_leasing_num) {
        TotalRentalDao.deleteTotalRentalByPropertyLeasingNum(property_leasing_num);
    }


}
