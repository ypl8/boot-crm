package cn.kfqjtdqb.core.service;
import cn.kfqjtdqb.common.utils.Page;
import cn.kfqjtdqb.core.bean.Permission;
import cn.kfqjtdqb.core.bean.SysLog;

import java.util.List;

public interface SysLogService {

    // 查询客户列表
    Page<SysLog> selectSysLogList(Integer page, Integer rows, String username);
    Integer createSysLog(SysLog sysLog);
}
