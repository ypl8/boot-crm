package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.Case;

import java.util.List;


public interface AssertInfolService {
    public Page<AssertInfol> findAssertInfolList(Integer page, Integer rows,
                                          String assert_num);
    public AssertInfol getAssertInfolById(Long id);
    public void updateAssertInfol(AssertInfol assertInfol);
    public void deleteAssertInfol(Long id);
    int createAssertInfol(AssertInfol assertInfol);
    //查找空闲状态的资产信息
    public List<AssertInfol> findAssertInfolIdleStateList();

}
