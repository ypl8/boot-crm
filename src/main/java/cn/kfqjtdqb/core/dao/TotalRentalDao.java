package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.TotalRental;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TotalRentalDao {
    List<TotalRental> selectTotalRentalList(TotalRental totalRental);
    Integer selectTotalRentalListCount(TotalRental totalRental);
    TotalRental getTotalRentalById(Long id);
    void updateTotalRental(TotalRental totalRental)throws DataAccessException;
    void deleteTotalRental(Long id);
    int addTotalRental(TotalRental TotalRental)throws DataAccessException;
    List<TotalRental>   getTotalRentalByLeasingNum(String property_leasing_num);
    List<TotalRental> selectTotalRentalCommunityList(TotalRental totalRental);
    Integer selectTotalRentalListCommunityCount(TotalRental totalRental);
    List<TotalRental> selectTotalRentalCommunityByYearList(TotalRental totalRental);
    Integer selectTotalRentalListCommunityByYearCount(TotalRental totalRental);
    void deleteTotalRentalByPropertyLeasingNum(String property_leasing_num);
}
