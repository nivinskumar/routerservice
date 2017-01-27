package com.immco.startup;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immco.cache.WorkflowCacheBuilder;
import com.immco.db.proxy.ConstructionServiceProxy;
import com.immco.db.service.router.SubTaskTemplateService;
import com.immco.db.service.router.TaskTemplateService;
import com.immco.db.service.router.UdefFormTemplateService;
import com.immco.db.service.router.WorkflowPhaseService;
import com.immco.db.service.router.WorkflowTemplateService;

@Service
public class ServiceKickStarter {

	@Autowired
	private WorkflowTemplateService workflowTemplateService;
	@Autowired
	private WorkflowPhaseService milestoneTemplateService;
	@Autowired
	private TaskTemplateService taskTemplateService;
	@Autowired
	private SubTaskTemplateService subTaskTemplateService;
	@Autowired
	private UdefFormTemplateService udefFormTemplateService;

	@Autowired
	private ConstructionServiceProxy constructionServiceProxy;

	public ConstructionServiceProxy getConstructionServiceProxy() {
		return constructionServiceProxy;
	}

	public WorkflowTemplateService getWorkflowTemplateService() {
		return workflowTemplateService;
	}

	public WorkflowPhaseService getMilestoneTemplateService() {
		return milestoneTemplateService;
	}

	public TaskTemplateService getTaskTemplateService() {
		return taskTemplateService;
	}

	public SubTaskTemplateService getSubTaskTemplateService() {
		return subTaskTemplateService;
	}

	public UdefFormTemplateService getUdefFormTemplateService() {
		return udefFormTemplateService;
	}

	@PostConstruct
	private void startService() {
		WorkflowCacheBuilder.getInstance().setServiceKickStarter(this);
		WorkflowCacheBuilder.getInstance().start();
	}

	public void stopService() {
		WorkflowCacheBuilder.getInstance().stop();
	}
}
