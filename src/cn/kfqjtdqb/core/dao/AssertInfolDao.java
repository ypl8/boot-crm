package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.AssertInfol;
import cn.kfqjtdqb.core.bean.Case;

import java.util.List;

public interface AssertInfolDao {
    List<AssertInfol> selectAssertInfolList(AssertInfol assertInfol);
    Integer selectAssertInfolListCount(AssertInfol assertInfol);
    AssertInfol getAssertInfolById(Long id);
    void updateAssertInfol(AssertInfol assertInfol);
    void deleteAssertInfol(Long id);
    int  addAssertInfol(AssertInfol assertInfol);
}
