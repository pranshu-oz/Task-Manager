package pranshu.task.manager.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pranshu.task.manager.api.entity.Task;
import pranshu.task.manager.api.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	TaskService taskService;

	@PostMapping("/add")
	public String createTask(@RequestBody Task task) {
		
		taskService.createTask(task);
		return "Saved";
	}
	
	@GetMapping("/view")
	public List<Task> viewTask() {
		
		return taskService.getAllTask();
	}
}
