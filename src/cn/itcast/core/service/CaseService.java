package cn.itcast.core.service;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Case;
import cn.itcast.core.bean.Customer;


public interface CaseService {

    // 查询客户列表
    public Page<Case> findCaseList(Integer page, Integer rows,
                                   String caseNo);
    public Case getCaseById(Long id);

    public void updateCase(Case caser);

    public void deleteCase(Long id);

    int createCase(Case caser);
}
