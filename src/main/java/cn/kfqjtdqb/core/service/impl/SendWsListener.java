package cn.kfqjtdqb.core.service.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.kfqjtdqb.common.utils.ConstUtils;
import cn.kfqjtdqb.core.bean.AssertDeposit;
import cn.kfqjtdqb.core.bean.AssertEstate;
import cn.kfqjtdqb.core.bean.PropertyLeasing;
import cn.kfqjtdqb.core.dao.AssertDepositDao;
import cn.kfqjtdqb.core.dao.AssertEstateDao;
import cn.kfqjtdqb.core.dao.AssertRentalDao;
import cn.kfqjtdqb.core.dao.PropertyLeasingDao;
import cn.kfqjtdqb.core.utils.DateUtils;
import cn.kfqjtdqb.core.utils.RentUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.repository.Deployment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class SendWsListener implements ServletContextListener {

    private final static Logger logger = Logger.getLogger(SendWsListener.class);
    private WebApplicationContext webApplicationContext = null;

    @Autowired
    private RepositoryService repositoryService;

    private Deployment deploy;

    private Deployment deploy2;

    private Deployment deploy3;

    private Deployment deploy4;

    private Deployment deploy5;

    private Deployment deploy6;

    private Deployment deploy7;

    private Deployment deploy8;

    private Deployment deploy9;

    private Deployment deploy10;


    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        if (deploy != null) {
            repositoryService.deleteDeployment(deploy.getId(), true);
        }
        if (deploy2 != null) {
            repositoryService.deleteDeployment(deploy2.getId(), true);
        }
        if (deploy3 != null) {
            repositoryService.deleteDeployment(deploy3.getId(), true);
        }

        if (deploy4 != null) {
            repositoryService.deleteDeployment(deploy4.getId(), true);
        }
        if (deploy5 != null) {
            repositoryService.deleteDeployment(deploy5.getId(), true);
        }
        if (deploy6 != null) {
            repositoryService.deleteDeployment(deploy6.getId(), true);
        }
        if (deploy7 != null) {
            repositoryService.deleteDeployment(deploy7.getId(), true);
        }

        if (deploy8 != null) {
            repositoryService.deleteDeployment(deploy8.getId(), true);
        }
        if (deploy9 != null) {
            repositoryService.deleteDeployment(deploy9.getId(), true);
        }

        if (deploy10 != null) {
            repositoryService.deleteDeployment(deploy10.getId(), true);
        }
        logger.info("activiti 删除流程部署");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        if (null == webApplicationContext) {
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
            if (webApplicationContext != null) {
                repositoryService = (RepositoryService) webApplicationContext.getBean("repositoryService");

            }
        }
        deploy = repositoryService.createDeployment().addClasspathResource("bpmn/assert.bpmn").addClasspathResource("bpmn/assert.png").name("资产审核流程").deploy();
        deploy2 = repositoryService.createDeployment().addClasspathResource("bpmn/contract.bpmn").addClasspathResource("bpmn/contract.png").name("合同审核流程").deploy();
        deploy3 = repositoryService.createDeployment().addClasspathResource("bpmn/rental.bpmn").addClasspathResource("bpmn/rental.png").name("租金审核流程").deploy();
        deploy4 = repositoryService.createDeployment().addClasspathResource("bpmn/estate.bpmn").addClasspathResource("bpmn/estate.png").name("租金审核流程").deploy();
        deploy5 = repositoryService.createDeployment().addClasspathResource("bpmn/assertWater.bpmn").addClasspathResource("bpmn/assertWater.png").name("水录入审批流程").deploy();
        deploy6 = repositoryService.createDeployment().addClasspathResource("bpmn/assertWaterRental.bpmn").addClasspathResource("bpmn/assertWaterRental.png").name("水费录入审批流程").deploy();
        deploy7 = repositoryService.createDeployment().addClasspathResource("bpmn/assertPower.bpmn").addClasspathResource("bpmn/assertPower.png").name("电录入审批流程").deploy();
        deploy8 = repositoryService.createDeployment().addClasspathResource("bpmn/assertPowerRental.bpmn").addClasspathResource("bpmn/assertPowerRental.png").name("电费录入审批流程").deploy();
        deploy9 = repositoryService.createDeployment().addClasspathResource("bpmn/deposit.bpmn").addClasspathResource("bpmn/deposit.png").name("押金审批流程").deploy();
        deploy10 = repositoryService.createDeployment().addClasspathResource("bpmn/assertLeasing.bpmn").addClasspathResource("bpmn/assertLeasing.png").name("水电初始化审批流程").deploy();
        logger.info("activiti  开始流程部署");
    }


}