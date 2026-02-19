package pranshu.task_manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pranshu.task_manager.dto.TaskRequest;
import pranshu.task_manager.dto.TaskResponse;
import pranshu.task_manager.modal.Task;
import pranshu.task_manager.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;

	//crate all task
	public void createTask(TaskRequest taskRequest){
		
		taskRepository.save(mapToRequest(taskRequest));
	}
	
	//get all task from db
	public List<TaskResponse> getAllTask() {
		
		return convertAllTaskToTaskResponse(taskRepository.findAll());
	}

	public TaskResponse getTaskById(Long id) {
		
		return mapToResponse(taskRepository.findById(id).orElseThrow());
	}

	public void updateTask(Long id, TaskRequest taskRequest) {
	
		Task task = mapToRequest(taskRequest);
		task.setId(id);
		taskRepository.save(task);
	}

	public void deleteTaskById(Long id) {
		
		taskRepository.deleteById(id);
	}
	
	public void deleteAllTask() {
		
		taskRepository.deleteAll();
	}
	
	// define DTO & uses basically its stand for data transfer object.
	
	private TaskResponse mapToResponse(Task task) {
		
		TaskResponse taskRes = new TaskResponse();
		
		taskRes.setId(task.getId());
		taskRes.setTitle(task.getTitle());
		taskRes.setDiscription(task.getDescription());
		taskRes.setStatus(task.getStatus());
		taskRes.setCreatedAt(task.getCreatedAt());
		
		return taskRes;
	}
	
	private Task mapToRequest(TaskRequest taskReq) {
		
		Task task = new Task();
		task.setTitle(taskReq.getTitle());
		task.setDescription(taskReq.getDiscription());
		task.setStatus(taskReq.getStatus());
		return task;
	}
	
	private List<TaskResponse> convertAllTaskToTaskResponse(List<Task> task){
		
		ArrayList<TaskResponse> list = new ArrayList<>();
		
		for(Task t : task) {
			
			list.add(mapToResponse(t));
		}
		return list;
	}
}
