package com.immco.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class JaxbTest {

	public static void main(String[] args) {
		JaxbTest j = new JaxbTest();
		j.createXml();
	}
	
	private void createXml() {
		Subtask subTask = new Subtask();
		subTask.setName("ST-1");
		subTask.setDescription("Sub Task 1");
		subTask.setId("ST-1");
		subTask.setEndpoint(EndPoint.SURVEY_COMPLETE);
		subTask.setPhaseId("SERVICEABILITY");
		subTask.setQueueId("DT SURVEY");
		subTask.setSlaExec("10");
		subTask.setSlaMetrics("MINUTES");
		subTask.setSlaPickupMin("2");
		subTask.setPredecessorid("ST-2");

		Task task = new Task();
		task.setName("T-1");
		task.setId("T-1");
		task.setDescription("Task 1");

		Milestone milestone = new Milestone();
		milestone.setName("M-1");
		milestone.setId("M-1");
		milestone.setDescription("Milestone-1");

		Workflow workflow = new Workflow();
		workflow.setConstructiontype("SMB COAX");
		workflow.setDescription("Workflow for SMB Coax");
		workflow.setName("WF-1");
		workflow.setId("WF-1");

		workflow.getMilestone().add(milestone);
		milestone.getTask().add(task);
		task.getSubtask().add(subTask);

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Workflow.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(workflow, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}