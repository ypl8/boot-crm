package cn.kfqjtdqb.core.service.impl;

import cn.kfqjtdqb.core.bean.*;
import cn.kfqjtdqb.core.dao.AssertInfolDao;
import cn.kfqjtdqb.core.dao.RoleDao;
import cn.kfqjtdqb.core.service.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("activitiService")
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private AssertInfolDao assertInfolDao;


    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private PropertyLeasingService propertyLeasingService;

    @Autowired
    private AssertRentalService assertRentalService;

    @Autowired
    private AssertEstateService assertEstateService;


    @Autowired
    private AssertWaterService  assertWaterService;

    @Autowired
    private AssertWaterRentService  assertWaterRentService;

    @Autowired
    private AssertPowerService  assertPowerService;

    @Autowired
    private AssertPowerRentService  assertPowerRentService;


    @Autowired
    private  AssertDepositService  assertDepositService;



    @Autowired
    private  AssertLeasingService  assertLeasingService;

    @Override
    public void changeState(DelegateExecution execution, String status) {

        //获取资产信息的id
        String instanceBusinessKey = execution.getProcessInstanceBusinessKey();

        //获取流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(execution.getProcessDefinitionId()).singleResult();

        //获取key
        if ("assert".equals(processDefinition.getKey())) {
            //修改状态
            AssertInfol assertInfol = assertInfolDao.getAssertInfolById(Long.parseLong(instanceBusinessKey));
            assertInfol.setStatus(status);
            assertInfolDao.updateAssertInfol(assertInfol);
        } else if ("contract".equals(processDefinition.getKey())) {
            PropertyLeasing propertyLeasing = propertyLeasingService.getPropertyLeasingById(Long.parseLong(instanceBusinessKey));
            propertyLeasing.setStatus(status);
            propertyLeasingService.updatePropertyLeasing(propertyLeasing);
        }else if ("rental".equals(processDefinition.getKey())) {
            AssertRental  assertRental = assertRentalService.getAssertRentalById(Long.parseLong(instanceBusinessKey));
            assertRental.setStatus(status);
            assertRentalService.updateAssertRental(assertRental);
        }else if("estate".equals(processDefinition.getKey())){
            AssertEstate  assertEstate=assertEstateService.getAssertEstateById(Long.parseLong(instanceBusinessKey));
            assertEstate.setStatus(status);
            assertEstateService.updateAssertEstate(assertEstate);
        }else if("assertWater".equals(processDefinition.getKey())){
            AssertWater  assertWater=assertWaterService.getAssertWaterById(Long.parseLong(instanceBusinessKey));
            assertWater.setStatus(status);
            assertWaterService.updateAssertWater(assertWater);
        }else if("assertWaterRental".equals(processDefinition.getKey())){
            AssertWaterRent  assertWaterRent=assertWaterRentService.getAssertWaterRentById(Long.parseLong(instanceBusinessKey));
            assertWaterRent.setStatus(status);
            assertWaterRentService.updateAssertWaterRent(assertWaterRent);
        }else if("assertPower".equals(processDefinition.getKey())){
            AssertPower  assertPower=assertPowerService.getAssertPowerById(Long.parseLong(instanceBusinessKey));
            assertPower.setStatus(status);
            assertPowerService.updateAssertPower(assertPower);
        }else if("assertPowerRental".equals(processDefinition.getKey())){
            AssertPowerRent  assertPowerRent=assertPowerRentService.getAssertPowerRentById(Long.parseLong(instanceBusinessKey));
            assertPowerRent.setStatus(status);
            assertPowerRentService.updateAssertPowerRent(assertPowerRent);
        }else if("deposit".equals(processDefinition.getKey())){
            AssertDeposit  assertDeposit=assertDepositService.getAssertDepositById(Long.parseLong(instanceBusinessKey));
            assertDeposit.setStatus(status);
            assertDepositService.updateAssertDeposit(assertDeposit);
        }else if("assertLeasing".equals(processDefinition.getKey())){
            AssertLeasing  assertLeasing=assertLeasingService.getAssertLeasingById(Long.parseLong(instanceBusinessKey));
            assertLeasing.setStatus(status);
            assertLeasingService.updateAssertLeasing(assertLeasing);
        }
    }

    @Override
    public List<String> findUser(DelegateExecution execution) {
        List<UserInfo> userInfos = roleDao.findUser(1l);

        List<String> results = new ArrayList<String>();
        for (int i = 0; i < userInfos.size(); i++) {
            results.add(userInfos.get(i).getId().toString());
        }

        return results;
    }

    @Override
    public void startProcesser(Long bizKey, String processDefinitionKey) {  //业务id 资产的id
        // 流程定义key
        runtimeService.startProcessInstanceByKey(processDefinitionKey, bizKey.toString());
    }

    @Override
    public void startProcesser(Long bizKey, String processDefinitionKey, Long userId) {
        Map map = new HashMap<String, Object>();
        map.put("username", userId);
        // 流程定义key
        runtimeService.startProcessInstanceByKey(processDefinitionKey, bizKey.toString(), map);
    }

    @Override
    public List<Task> findTaskByUserId(String userId, String processDefinitionKey) {
        List<Task> list = taskService.createTaskQuery()//
                .processDefinitionKey(processDefinitionKey)//
                .taskCandidateOrAssigned(userId)//只查询该任务负责人的任务
                .list();
        return list;
    }


    @Override
    public void completeTask(String userId, String taskId, String status, String processInstanceId, String comment) {
        taskService.claim(taskId, userId);
        Map map = new HashMap<String, Object>();
        map.put("status", status);
        Authentication.setAuthenticatedUserId(userId);
        // 添加批注信息
        if("30".equals(status)){
            this.taskService.addComment(taskId, processInstanceId, "[ 管理员审批通过 ]" + comment);
        }else{
            this.taskService.addComment(taskId, processInstanceId, "[ 管理员审批拒绝]" + comment);
        }

        taskService.complete(taskId, map);
    }

    @Override
    public void changeState(Long id, String status) {
        //修改状态
        AssertInfol assertInfol = assertInfolDao.findAssertWithPropertyLeasing(id);
        assertInfol.setStatus(status);
        assertInfolDao.updateAssertInfol(assertInfol);
    }


    @Override
    public void completeTask(String userId, String taskId, String processInstanceId, String comment) {
        Authentication.setAuthenticatedUserId(userId);
        // 添加批注信息
        this.taskService.addComment(taskId, processInstanceId, "[ 资产管理员 ]" + comment);
        taskService.complete(taskId);
    }


    public List<Comment> getProcessComments(Long bizKey, String processDefinitionKey) {
        // 根据业务ID查询历史流程实例
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processDefinitionKey)
                .processInstanceBusinessKey(bizKey.toString()).list();
        // 使用taskService+流程实例ID查询批注
        List<Comment> list = new ArrayList<>();
        for (int i = 0; historicProcessInstances != null && i < historicProcessInstances.size(); i++) {
            List<Comment> comments = this.taskService.getProcessInstanceComments(historicProcessInstances.get(i).getId());//
            list.addAll(comments);
        }
        return list;
    }


    //查询某个人的任务

    public List<Task> getTaskByUserId(String userId,String processDefinitionKey ) {

        List<Task> list = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskCandidateOrAssigned(userId).list();
        if (null != list && list.size() > 0) {
            for (Task task : list) {
                System.out.println("任务ID:" + task.getId());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                System.out.println("执行实例ID:" + task.getExecutionId());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务办理人:" + task.getAssignee());
                System.out.println("################################");
            }
        }

        return list;
    }





}
