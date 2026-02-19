package pranshu.task_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pranshu.task_manager.dto.TaskRequest;
import pranshu.task_manager.dto.TaskResponse;
import pranshu.task_manager.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	//add new Task

	@PostMapping("/add")
	public String createTask(@RequestBody TaskRequest taskRequest) {

		taskService.createTask(taskRequest);
		return "Saved all Task";
	}
	
	// view all Task
	
	@GetMapping("/show")
	public List<TaskResponse> viewTask() {
		System.out.println("got in get mapping");
		return taskService.getAllTask();
	}
	
	// view task by Id
	
	@GetMapping("/show/{id}")
	public TaskResponse viewTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id);
	}
	
	// update task by Id
	
	@PutMapping("/update/{id}")
	public String updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
		taskService.updateTask(id, taskRequest);
		return "Task Updated";
	}
	
	// delete task by Id
	
	@DeleteMapping("/delete/{id}")
	public String deleteTaskById(@PathVariable Long id) {
		
		taskService.deleteTaskById(id);
		return id+" has been deleted";
	}
	
	// delete all task
	
	@DeleteMapping("/deleteAll")
	public String deleteAllTask() {
		
		taskService.deleteAllTask();
		return "All task has been deleted";
	}
}
