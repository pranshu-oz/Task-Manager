package pranshu.task_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pranshu.task_manager.modal.Task;
import pranshu.task_manager.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	//add new Task

	@PostMapping("/add")
	public String createTask(@RequestBody Task task) {

		taskService.createTask(task);
		System.out.println(task.toString());
		return "Saved";
	}
	
	// view all Task
	
	@GetMapping("/show")
	public List<Task> viewTask() {
		System.out.println("got in get mapping");
		return taskService.getAllTask();
	}
	
	// view task by Id
	
	@GetMapping("/show/{id}")
	public Task viewTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id);
	}
	
	// update task by Id
	
	@PutMapping("/update")
	public String updateTask(@RequestBody Task task) {
		taskService.updateTask(task);
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
