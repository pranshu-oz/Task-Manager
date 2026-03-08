package pranshu.task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pranshu.task_manager.model.TaskUser;
import pranshu.task_manager.service.TaskUserService;

@RestController
public class LoginController {
	
	@Autowired
	TaskUserService userService;
	
	@GetMapping("/login")
	public String validateLogin() {
		
		return "login successful";
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody TaskUser user) {
		
		userService.addNewUser(user);
		
		return "user added";
	}

}
