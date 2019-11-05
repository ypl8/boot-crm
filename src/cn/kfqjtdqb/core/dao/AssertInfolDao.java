package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.AssertInfolTotal;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AssertInfolDao {

    List<AssertInfol> selectAssertInfolList(AssertInfol assertInfol);

    Integer selectAssertInfolListCount(AssertInfol assertInfol);

    AssertInfol getAssertInfolById(Long id);

    void updateAssertInfol(AssertInfol assertInfol) throws DataAccessException;

    void deleteAssertInfol(Long id);

    int addAssertInfol(AssertInfol assertInfol)throws DataAccessException;

    //查看资产信息对应的合同
    AssertInfol findAssertWithPropertyLeasing(Long id);

    //根据资产编号查找资产信息
    AssertInfol getAssertInfolByAssertNum(String assert_num);

    //统计总数
    List<AssertInfolTotal> selectAssertInfolTotalList(AssertInfol assertInfol);

    Integer selectAssertInfolTotalListCount(AssertInfol assertInfol);


    //查看所有的小区名字
    List<String> findAllCommunityName();

    //查看小区所有的栋号
    List<String> findAllBuildNum(AssertInfol assertInfol);

    //查看小区栋对应的房间号和栋号
    List<String> findAllRoomNum(AssertInfol assertInfol);

    //查看对应的资产 通过 小区名称  栋号   位置
    AssertInfol findAssertInfolListByCommunityName(AssertInfol assertInfol);
}
