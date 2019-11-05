package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.AssertInfolTotal;
import cn.kfqjtdqb.core.bean.Case;

import java.util.List;


public interface AssertInfolService {
    Page<AssertInfol> findAssertInfolList(Integer page, Integer rows,
                                          String assert_num, String floor_state, String community_name, String property_leasing_num);

    public AssertInfol getAssertInfolById(Long id);

    public void updateAssertInfol(AssertInfol assertInfol);

    public void deleteAssertInfol(Long id);

    int createAssertInfol(AssertInfol assertInfol);

    //查找空闲状态的资产信息
    List<AssertInfol> findAssertInfolIdleStateList();

    //查看资产信息
    AssertInfol findAssertWithPropertyLeasing(Long id);

    AssertInfol getAssertInfolByAssertNum(String assert_num);

    List<AssertInfol> findAssertInfolListAll(String assert_num, String floor_state, String community_name);

    Page<AssertInfolTotal> findAssertInfolListByCommunity(Integer page, Integer rows, String community_name);

    AssertInfol findAssertInfolListByCommunityName(String community_name, String building_num, String location);

    List<String> findAllCommunityName();

    List<String> findAllBuildNum(String community_name, String floor_state);

    List<String> findAllRoomNum(String community_name, String building_num, String floor_state);
}
