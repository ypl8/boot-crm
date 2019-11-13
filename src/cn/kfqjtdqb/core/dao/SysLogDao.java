package cn.kfqjtdqb.core.dao;

import cn.kfqjtdqb.core.bean.SysLog;

import java.util.List;

public interface SysLogDao {
	List<SysLog> selectSysLogList(SysLog sysLog);
	Integer selectSysLogListCount(SysLog sysLog);
	Integer addSysLog(SysLog sysLog);
}