package pranshu.task.manager.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pranshu.task.manager.api.entity.Task;
import pranshu.task.manager.api.repository.TaskRepository;

public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	Task task;

	public void createTask(Task task){
		
//		this.task.setId(task.getId());
//		this.task.setDescription(task.getDescription);
//		this.task.setStatus(task.getStatus);
//		this.task.setTitle(task.getTitle);
		System.out.println("Done All Saving");
	}
	
	public List<Task> getAllTask() {
		
		return taskRepository.geAllTask();
	}
}
