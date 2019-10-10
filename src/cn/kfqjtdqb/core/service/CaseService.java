package cn.kfqjtdqb.core.service;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Case;


public interface CaseService {

    // 查询客户列表
    public Page<Case> findCaseList(Integer page, Integer rows,
                                   String asserNo);
    public Case getCaseById(Long id);

    public void updateCase(Case caser);

    public void deleteCase(Long id);

    int createCase(Case caser);
}
