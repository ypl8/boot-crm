package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.TotalRental;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TotalRentalService {

    Page<TotalRental> selectTotalRentalList(Integer page, Integer rows, String property_leasing_num, String year_months, String community_name);

    TotalRental getTotalRentalById(Long id);

    void updateTotalRental(TotalRental totalRental) throws DataAccessException;


    void deleteTotalRental(Long id);

    int createTotalRental(TotalRental totalRental) throws DataAccessException;


    List<TotalRental> getTotalRentalByLeasingNum(String property_leasing_num);

    //按照小区名称分组统计
    Page<TotalRental> selectTotalRentalCommunityList(Integer page, Integer rows, String year_months, String community_name);

    Page<TotalRental> selectTotalRentalCommunityByYearList(Integer page, Integer rows, String years, String community_name);

    void deleteTotalRentalByPropertyLeasingNum(String property_leasing_num);
}
