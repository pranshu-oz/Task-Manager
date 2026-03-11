package pranshu.task_manager.dto;

public class TaskRequest {
	
	
	private String title;
	
	private String discription;
	
	private String status;
	
	private Long assignedTo;
	
	

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getTitle() {
		return title;
	}

	public String getDiscription() {
		return discription;
	}

	public String getStatus() {
		return status;
	}

	
}
