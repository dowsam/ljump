package cn.com.solex.jbpm;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import javax.sql.DataSource;

import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.activity.ActivityExecution;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryActivityInstanceQuery;
import org.jbpm.api.job.Job;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.PvmEnvironment;
import org.jbpm.pvm.internal.model.Activity;
import org.jbpm.pvm.internal.model.ActivityCoordinatesImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.Transition;
import org.jbpm.pvm.internal.task.OpenTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 封装jbpm4
 * 
 * @author Administrator
 * 
 */
@Component
public class JbpmTemplate {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected ProcessEngine processEngine;

	@Autowired
	private DataSource dataSource;

	/**
	 * 返回最新流程定义
	 * 
	 * @return
	 */
	public List<ProcessDefinition> getLatestProcessDefinitions() {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		List<ProcessDefinition> definitions = repositoryService
				.createProcessDefinitionQuery().orderAsc(
						ProcessDefinitionQuery.PROPERTY_NAME).list();
		Map<String, ProcessDefinition> map = Maps.newHashMap();

		for (ProcessDefinition pd : definitions) {
			String key = pd.getKey();
			ProcessDefinition processDefinition = map.get(key);
			if ((processDefinition == null)
					|| (processDefinition.getVersion() < pd.getVersion())) {
				map.put(key, pd);
			}
		}
		return Lists.newArrayList(map.values());
	}

	/**
	 * 查找暂停流程定义
	 * 
	 * @return
	 */
	public List<ProcessDefinition> getSuspendedProcessDefinitions() {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		return repositoryService.createProcessDefinitionQuery().suspended()
				.orderAsc(ProcessDefinitionQuery.PROPERTY_NAME).list();
	}

	/**
	 * 布署流程定义
	 * 
	 * @param name
	 *            流程定义名称
	 * @param inputStream
	 *            输入流
	 */
	public void deployXml(String name, InputStream inputStream) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment().addResourceFromInputStream(name,
				inputStream).deploy();
	}

	/**
	 * 布署流程定义（打包方式）
	 * 
	 * @param inputStream
	 *            输入流，
	 */
	public void deployZip(InputStream inputStream) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment().addResourcesFromZipInputStream(
				new ZipInputStream(inputStream)).deploy();
	}

	/**
	 * xml字符串的形式布署流程定义
	 * 
	 * @param xml
	 */
	public void deployXml(String xml) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		repositoryService.createDeployment().addResourceFromString(
				"process.jpdl.xml", xml).deploy();
	}

	/**
	 * 暂停流程定义
	 * 
	 * @param id
	 *            流程的ID
	 */
	public void suspendProcessDefinitionById(String id) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();
		repositoryService.suspendDeployment(pd.getDeploymentId());
	}

	/**
	 * 恢复暂停流程定义
	 * 
	 * @param id
	 *            流程ID
	 */
	public void resumeProcessDefinitionById(String id) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();
		repositoryService.resumeDeployment(pd.getDeploymentId());
	}

	/**
	 * 删除流程定义
	 * 
	 * @param id
	 */
	public void removeProcessDefinitionById(String id) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();
		repositoryService.deleteDeploymentCascade(pd.getDeploymentId());
	}

	public Set<String> getTransitionsForTask(String id) {
		TaskService taskService = processEngine.getTaskService();

		return taskService.getOutcomes(id);
	}

	public List<Transition> getTransitionsForSignalProcess(String id) {
		ExecutionService executionService = processEngine.getExecutionService();
		ProcessInstance pi = executionService.findProcessInstanceById(id);
		EnvironmentFactory environmentFactory = (EnvironmentFactory) processEngine;
		PvmEnvironment env = (PvmEnvironment) environmentFactory
				.openEnvironment();

		try {
			ExecutionImpl executionImpl = (ExecutionImpl) pi;
			Activity activity = executionImpl.getActivity();

			return activity.getOutgoingTransitions();
		} finally {
			env.close();
		}
	}

	public void completeTask(String dbid, String transitionName,
			Map<String, Object> variables) {
		TaskService taskService = processEngine.getTaskService();
		taskService.setVariables(dbid, variables);
		taskService.completeTask(dbid, transitionName);
	}

	/**
	 * 启动流程实例
	 * @param id
	 * @param variables
	 */
	public void startProcess(String id, Map<String, Object> variables) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ExecutionService executionService = processEngine.getExecutionService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();

		executionService.startProcessInstanceById(pd.getId(), variables);
	}

	public void signalProcess(String id, String transitionName,
			Map<String, Object> variables) {
		ExecutionService executionService = processEngine.getExecutionService();
		executionService.setVariables(id, variables);

		executionService.signalExecutionById(id, transitionName);
	}

	/**
	 * 获取所有流程实例
	 * 
	 * @return
	 */
	public List<ProcessInstance> getProcessInstances() {
		return processEngine.getExecutionService().createProcessInstanceQuery()
				.list();
	}

	/**
	 * 根据流程定义，获取流程实例
	 * 
	 * @param pdId
	 * @return
	 */
	public List<ProcessInstance> getProcessInstancesByPdId(String pdId) {
		ExecutionService executionService = processEngine.getExecutionService();

		return executionService.createProcessInstanceQuery()
				.processDefinitionId(pdId).list();
	}

	/**
	 * 获取所有暂停的流程实例
	 * 
	 * @return
	 */
	public List<ProcessInstance> getSuspendedProcessInstances() {
		ExecutionService executionService = processEngine.getExecutionService();

		return executionService.createProcessInstanceQuery().suspended().list();
	}

	/**
	 * 根据流程实例id ，返回节点的x,y,width,height
	 * 
	 * @param id
	 * @return
	 */
	public ActivityCoordinates getActivityCoordinates(String id) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ExecutionService executionService = processEngine.getExecutionService();

		ProcessInstance pi = executionService.findProcessInstanceById(id);

		return repositoryService.getActivityCoordinates(pi
				.getProcessDefinitionId(), ((ActivityExecution) pi)
				.getActivityName());
	}

	/**
	 * 暂停流程实例
	 * 
	 * @param id
	 */
	public void suspendProcessInstance(String id) {
		ExecutionService executionService = processEngine.getExecutionService();

		ProcessInstance pi = executionService.findProcessInstanceById(id);

		/**
		 * FIXME: 这个部分肯定是jBPM的bug，
		 * api里的Execution接口和ExecutionService接口竟然都没有suspend和resume方法。
		 */
		((ExecutionImpl) pi).suspend();
	}

	/**
	 * 恢复暂停的流程实例
	 * 
	 * @param id
	 */
	public void resumeProcessInstance(String id) {
		ExecutionService executionService = processEngine.getExecutionService();

		ProcessInstance pi = executionService.createProcessInstanceQuery()
				.suspended().processInstanceId(id).uniqueResult();

		/**
		 * FIXME: 这个部分肯定是jBPM的bug，
		 * api里的Execution接口和ExecutionService接口竟然都没有suspend和resume方法。
		 */
		((ExecutionImpl) pi).resume();
	}

	/**
	 * 结束流程实例
	 * 
	 * @param id
	 */
	public void endProcessInstance(String id) {
		ExecutionService executionService = processEngine.getExecutionService();

		executionService.endProcessInstance(id, Execution.STATE_ENDED);
	}

	/**
	 * 根据流程实例id删除流程实例
	 * 
	 * @param id
	 */
	public void removeProcessInstance(String id) {
		ExecutionService executionService = processEngine.getExecutionService();

		executionService.deleteProcessInstance(id);
	}

	/**
	 * 要把任务ID得到，任务变量
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getVariablesForTask(String id) {
		TaskService taskService = processEngine.getTaskService();
		Set<String> names = taskService.getVariableNames(id);

		return taskService.getVariables(id, names);
	}

	/**
	 * 根据executionId，得到运行时的任务变量
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Object> getVariablesForProcess(String id) {
		ExecutionService executionService = processEngine.getExecutionService();
		Set<String> names = executionService.getVariableNames(id);

		return executionService.getVariables(id, names);
	}

	/**
	 * 得到所有的任务
	 * 
	 * @return
	 */
	public List<Task> getTasks() {
		TaskService taskService = processEngine.getTaskService();

		return taskService.createTaskQuery().list();
	}

	/**
	 * 得到用户的所有待办任务
	 * 
	 * @param username
	 * @return
	 */
	public List<Task> getPersonalTasks(String username) {
		TaskService taskService = processEngine.getTaskService();

		return taskService.findPersonalTasks(username);
	}

	/**
	 * 得到用户所有共享任务列表
	 * 
	 * @param username
	 * @return
	 */
	public List<Task> getGroupTasks(String username) {
		TaskService taskService = processEngine.getTaskService();

		return taskService.findGroupTasks(username);
	}

	/**
	 * 根据任务ID，运行用户任务
	 * 
	 * @param dbid
	 * @param username
	 */
	public void takeTask(String dbid, String username) {
		TaskService taskService = processEngine.getTaskService();
		taskService.takeTask(dbid, username);
	}

	/**
	 * 根据任务ID，取消
	 * 
	 * @param dbid
	 */
	public void cancelTask(String dbid) {
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.getTask(dbid);

		EnvironmentFactory environmentFactory = (EnvironmentFactory) processEngine;

		PvmEnvironment env = (PvmEnvironment) environmentFactory
				.openEnvironment();

		try {
			OpenTask openTask = (OpenTask) task;
			// FIXME: jbpm-4.0.CR1 is cancel, now jbpm-4.0.GA is delete
			openTask.delete("no reason");
		} finally {
			env.close();
		}
	}

	/**
	 * 分配任务
	 * 
	 * @param dbid
	 */
	public void releaseTask(String dbid) {
		TaskService taskService = processEngine.getTaskService();
		taskService.assignTask(dbid, null);
	}

	/**
	 * 根据流程定义的ID返回输入流
	 * 
	 * @param id
	 * @return
	 */
	public InputStream getResourceFromProcessDefinition(String id) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();

		if (pd != null) {
			String processName = pd.getName() + ".jpdl.xml";

			System.out.println("processName0:  " + processName);

			return repositoryService.getResourceAsStream(pd.getDeploymentId(),
					processName);
		} else {
			return null;
		}
	}

	/**
	 * 根据流程实例ID返回流程定义的输入流
	 * 
	 * @param id
	 * @return
	 */
	public InputStream getResourceFromProcessInstance(String id) {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		ExecutionService executionService = processEngine.getExecutionService();

		ProcessInstanceQuery query = executionService
				.createProcessInstanceQuery();
		query.processInstanceId(id);

		Execution processInstance = (Execution) query.uniqueResult();
		ProcessDefinition pd = ((ExecutionImpl) processInstance)
				.getProcessDefinition();
		String processName = pd.getName() + ".jpdl.xml";
		System.out.println("processName:  " + processName);

		return repositoryService.getResourceAsStream(pd.getDeploymentId(),
				processName);
	}

	/**
	 * 根据流程实例返回流程定义
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public ProcessDefinition getProcessDefinitionByProcessInstanceId(
			String processInstanceId) {
		ExecutionService executionService = processEngine.getExecutionService();
		ProcessInstance pi = executionService
				.findProcessInstanceById(processInstanceId);
		EnvironmentFactory environmentFactory = (EnvironmentFactory) processEngine;
		PvmEnvironment env = (PvmEnvironment) environmentFactory
				.openEnvironment();

		try {
			return ((ExecutionImpl) pi).getProcessDefinition();
		} finally {
			env.close();
		}
	}

	/**
	 * 导出全部的流程定义
	 * 
	 * @return
	 */
	public String reportOverallActivity() {
		StringBuffer buff = new StringBuffer(
				"<graph showNames='Overall Activity' decimalPrecision='2'>");
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			Statement state = conn.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT d.DBID_ as dpl, p.STRINGVAL_ as processId"
							+ " FROM JBPM4_DEPLOYMENT d, JBPM4_DEPLOYPROP p"
							+ " WHERE p.KEY_='pdid'"
							+ " AND d.DBID_=p.DEPLOYMENT_"
							+ " GROUP BY d.DBID_,p.STRINGVAL_");

			while (rs.next()) {
				buff.append("<set name='").append(rs.getString("dpl")).append(
						"' value='").append(rs.getString("processId")).append(
						"'/>");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		}

		buff.append("</graph>");

		return buff.toString();
	}

	/**
	 * 导出活动的流程定义
	 * 
	 * @return
	 */
	public String reportMostActiveProcess() {
		StringBuffer buff = new StringBuffer(
				"<graph showNames='Most Active Process' decimalPrecision='2'>");
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			Statement state = conn.createStatement();

			String sql = "  select d.STRINGVAL_ as id, count(p.ID_) as num"
					+ "    from JBPM4_DEPLOYPROP d left join JBPM4_HIST_PROCINST p"
					+ "      on d.STRINGVAL_=p.PROCDEFID_"
					+ "   where d.KEY_='pdid' group by d.STRINGVAL_";
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				buff.append("<set name='").append(rs.getString("id")).append(
						"' value='").append(rs.getString("num")).append("'/>");
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		}

		buff.append("</graph>");

		return buff.toString();
	}

	/**
	 * 查找活动历史定义
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public List<ActivityCoordinates> getHistoryActivities(
			String processInstanceId) {
		HistoryService historyService = processEngine.getHistoryService();
		HistoryActivityInstanceQuery query = historyService
				.createHistoryActivityInstanceQuery();
		List<HistoryActivityInstance> activities = query.executionId(
				processInstanceId).list();

		ProcessDefinitionImpl processDefinition = (ProcessDefinitionImpl) this
				.getProcessDefinitionByProcessInstanceId(processInstanceId);
		List<ActivityCoordinates> list = new ArrayList<ActivityCoordinates>();
		ActivityImpl start = processDefinition.getInitial();
		ActivityCoordinates startAc = start.getCoordinates();
		startAc = new ActivityCoordinatesImpl(startAc.getX(), startAc.getY(),
				48, 48);
		list.add(startAc);

		for (HistoryActivityInstance activity : activities) {
			String activityName = activity.getActivityName();
			ActivityImpl activityImpl = processDefinition
					.findActivity(activityName);
			ActivityCoordinates ac = activityImpl.getCoordinates();

			if (activityImpl.getType().equals("decision")
					|| activityImpl.getType().equals("fork")
					|| activityImpl.getType().equals("join")
					|| activityImpl.getType().equals("end")
					|| activityImpl.getType().equals("end-cancel")
					|| activityImpl.getType().equals("end-error")) {
				ac = new ActivityCoordinatesImpl(ac.getX(), ac.getY(), 48, 48);
			}

			list.add(ac);
		}

		return list;
	}

	/**
	 * 查找历史的定义
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public List<HistoryActivityInstance> getHistoryActivitiesByProcessInstanceId(
			String processInstanceId) {
		HistoryService historyService = processEngine.getHistoryService();
		HistoryActivityInstanceQuery query = historyService
				.createHistoryActivityInstanceQuery();

		return query.executionId(processInstanceId).list();
	}

	/**
	 * 查找所有职位
	 * 
	 * @return
	 */
	public List<Job> getJobs() {
		ManagementService managementService = processEngine
				.getManagementService();

		return managementService.createJobQuery().list();
	}

}
