package cn.kfqjtdqb.core.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.hibernate.validator.constraints.EAN;

import java.util.List;


public interface ActivitiService {

    void changeState(DelegateExecution execution, String status);

    List<String> findUser(DelegateExecution execution);

    void startProcesser(Long bizKey, String processDefinitionKey);

    void startProcesser(Long bizKey, String processDefinitionKey, Long userId);

    List<Task> findTaskByUserId(String userId, String processDefinitionKey);

    void completeTask(String userId, String taskId, String status, String processInstanceId, String comment);

    void changeState(Long id, String status);

    void completeTask(String userId, String taskId, String processInstanceId, String comment);

    List<Comment> getProcessComments(Long bizKey,String processDefinitionKey);

    List<Task> getTaskByUserId(String userId,String processDefinitionKey );
}
