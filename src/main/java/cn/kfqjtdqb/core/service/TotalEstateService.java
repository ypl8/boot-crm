package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.TotalEstate;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface TotalEstateService {

    Page<TotalEstate> selectTotalEstateList(Integer page, Integer rows, String property_leasing_num, String year_months, String community_name);

    TotalEstate getTotalEstateById(Long id);

    void updateTotalEstate(TotalEstate totalEstate)throws DataAccessException;;

    void deleteTotalEstate(Long id);

    int createTotalEstate(TotalEstate totalEstate) throws DataAccessException;;

    List<TotalEstate> getTotalEstateByLeasingNum(String property_leasing_num);

    Page<TotalEstate> selectTotalEstateCommunityList(Integer page, Integer rows, String year_months, String community_name);

    Page<TotalEstate> selectTotalEstateCommunityByYearList(Integer page, Integer rows, String years, String community_name);

    void deleteTotalEstateByPropertyLeasingNum(String property_leasing_num);

    List<TotalEstate> selectTotalEstateList(String property_leasing_num, String community_name, String year_months);

    List<TotalEstate> selectTotalEstateCommunityByYearList(String years, String community_name);

    List<TotalEstate> selectTotalEstateCommunityList(String year_months, String community_name);
}
