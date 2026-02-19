package pranshu.task_manager.dto;

import java.util.Date;

public class TaskResponse {
	
	private Long id;
	
	private String title;
	
	private String discription;
	
	private String status;
	
	private Date createdAt;

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public Long getId() {
		return id;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "TaskResponse [id=" + id + ", title=" + title + ", discription=" + discription + ", status=" + status
				+ ", createdAt=" + createdAt + "]";
	}
	
}
