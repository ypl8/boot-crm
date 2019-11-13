package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.TotalEstate;
import org.springframework.dao.DataAccessException;
import java.util.List;

public interface TotalEstateDao {
    List<TotalEstate> selectTotalEstateList(TotalEstate totalEstate);
    Integer selectTotalEstateListCount(TotalEstate totalEstate);
    TotalEstate getTotalEstateById(Long id);
    void updateTotalEstate(TotalEstate totalEstate)throws DataAccessException;
    void deleteTotalEstate(Long id)throws DataAccessException;
    int addTotalEstate(TotalEstate totalEstate)throws DataAccessException;
    List<TotalEstate>   getTotalEstateByLeasingNum(String property_leasing_num);
    List<TotalEstate> selectTotalEstateCommunityList(TotalEstate totalEstate);
    Integer selectTotalEstateCommunityListCount(TotalEstate totalEstate);
    List<TotalEstate> selectTotalEstateCommunityByYearList(TotalEstate totalEstate);
    Integer selectTotalEstateCommunityListByYearCount(TotalEstate totalEstate);
    void deleteTotalEstateByPropertyLeasingNum(String property_leasing_num);
}
