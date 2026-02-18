package pranshu.task_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pranshu.task_manager.modal.Task;
import pranshu.task_manager.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;

	public void createTask(Task task){
		
		taskRepository.save(task);
	}
	
	public List<Task> getAllTask() {
		
		return taskRepository.findAll();
	}

	public Task getTaskById(Long id) {
		
		return taskRepository.findById(id).orElseThrow();
	}

	public void updateTask(Task task) {
	
		taskRepository.save(task);
	}

	public void deleteTaskById(Long id) {
		
		taskRepository.deleteById(id);
	}
	
	public void deleteAllTask() {
		
		taskRepository.deleteAll();
	}
}
