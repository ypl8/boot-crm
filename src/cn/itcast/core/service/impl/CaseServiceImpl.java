package cn.itcast.core.service.impl;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Case;
import cn.itcast.core.bean.Customer;
import cn.itcast.core.dao.BaseDictDao;
import cn.itcast.core.dao.CaseDao;
import cn.itcast.core.dao.CustomerDao;
import cn.itcast.core.service.CaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("caseService")
public class CaseServiceImpl implements CaseService {
    // 定义dao属性
    @Autowired
    private CaseDao caseDao;
    @Autowired
    private BaseDictDao baseDictDao;

    @Override
    public Page<Case> findCaseList(Integer page, Integer rows, String caseNo) {
        Case caser = new Case();
        //判断客户名称(公司名称)
        if(StringUtils.isNotBlank(caseNo)){
            caser.setCase_no(caseNo);
        }
        //当前页
        caser.setStart((page-1) * rows) ;
        //每页数
        caser.setRows(rows);
        //查询客户列表
        List<Case> customers = caseDao.selectCaseList(caser);
        //查询客户列表总记录数
        Integer count = caseDao.selectCaseListCount(caser);
        //创建Page返回对象
        Page<Case> result = new Page<>();
        result.setPage(page);
        result.setRows(customers);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }

    @Override
    public Case getCaseById(Long id) {
        Case caser = caseDao.getCaseById(id);
        return caser;
    }

    @Override
    public void updateCase(Case caser) {
        caseDao.updateCase(caser);
    }

    @Override
    public void deleteCase(Long id) {
        caseDao.deleteCase(id);
    }


    @Override
    public int createCase(Case caser) {
        return caseDao.addCase(caser);
    }
}
