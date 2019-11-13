package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.AssertPower;
import cn.kfqjtdqb.core.bean.SysLog;
import cn.kfqjtdqb.core.dao.SysLogDao;
import cn.kfqjtdqb.core.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysLogService")
@Transactional
public class SysLogServiceeImpl  implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public Page<SysLog> selectSysLogList(Integer page, Integer rows, String username) {
        SysLog sysLog = new SysLog();

        if (StringUtils.isNotBlank(username)) {
            sysLog.setUsername(username);
        }
        //当前页
        sysLog.setStart((page - 1) * rows);
        //每页数
        sysLog.setRows(rows);
        //查询客户列表
        List<SysLog> sysLogs = sysLogDao.selectSysLogList(sysLog);
        //查询客户列表总记录数
        Integer count = sysLogDao.selectSysLogListCount(sysLog);
        //创建Page返回对象
        Page<SysLog> result = new Page<>();
        result.setPage(page);
        result.setRows(sysLogs);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }



    @Override
    public Integer createSysLog(SysLog sysLog) {
        return sysLogDao.addSysLog(sysLog);
    }
}
