package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.core.dao.PropertyLeasingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SpringTaskImpl {

    @Autowired
    private PropertyLeasingDao propertyLeasingDao;

    @Scheduled(cron = "0 0/1 0/1 1/1 1/1 ? ")
    public void task() {
        propertyLeasingDao.updateState();
    }
}
