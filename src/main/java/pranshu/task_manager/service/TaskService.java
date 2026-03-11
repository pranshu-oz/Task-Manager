package pranshu.task_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pranshu.task_manager.dto.TaskRequest;
import pranshu.task_manager.dto.TaskResponse;
import pranshu.task_manager.model.Task;
import pranshu.task_manager.model.TaskUser;
import pranshu.task_manager.repository.TaskRepository;
import pranshu.task_manager.repository.TaskUserRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskUserRepository userRepository;
	

	//crate all task
	public void createTask(TaskRequest taskRequest){
		
		taskRepository.save(mapToRequest(taskRequest));
	}
	
	//get all task from db
	public List<TaskResponse> getAllTask() {
		
		//converting all retrieve task and mapping to TaskResponse class
		return convertAllTaskToTaskResponse(taskRepository.findAll());
	}

	//task by id
	public TaskResponse getTaskById(Long id) {
		
		return mapToResponse(taskRepository.findById(id).orElseThrow());
	}

	//put method call updating task
	public void updateTask(Long id, TaskRequest taskRequest) {
	
		Task task = mapToRequest(taskRequest);
		task.setId(id);
		taskRepository.save(task);
	}

	//delete by id 
	public void deleteTaskById(Long id) {
		
		taskRepository.deleteById(id);
	}
	
	//delete all at once lol cheat code.
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
		task.setAssignedTo(userRepository.findById(taskReq.getAssignedTo()).orElseThrow());
		
		return task;
	}
	
	// converting task to task response of list.
	private List<TaskResponse> convertAllTaskToTaskResponse(List<Task> task){
		
		ArrayList<TaskResponse> list = new ArrayList<>();
		
		for(Task t : task) {
			
			list.add(mapToResponse(t));
		}
		return list;
	}

	//Employee only 
	public List<TaskResponse> getMyTask() {
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		TaskUser user=userRepository.findUserByUsername(username).orElseThrow();
		
		return convertAllTaskToTaskResponse(taskRepository.findByAssignedToId(user.getId()));
				
	}
	
	private List<TaskResponse> convertMyTaskToTaskResponse(List<Task> tasks){
		
		ArrayList<TaskResponse> response = new ArrayList<>();
		
		for(Task task : tasks) {
			response.add(mapToResponse(task));
		}
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
