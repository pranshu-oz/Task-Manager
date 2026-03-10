package pranshu.task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pranshu.task_manager.dto.TaskUserRequest;
import pranshu.task_manager.dto.TaskUserResponse;
import pranshu.task_manager.model.TaskUser;
import pranshu.task_manager.service.JwtService;
import pranshu.task_manager.service.TaskUserService;

@RestController
public class LoginController {
	
	@Autowired
	TaskUserService userService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public String validateLogin(@RequestBody TaskUserRequest user) {
		
		Authentication auth = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						user.getUsername(),user.getPassword()));
		
		if(auth.isAuthenticated()) return jwtService.generateTocken(user.getUsername());
		
		return "Invalid Username";
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody TaskUserResponse user) {
		
		userService.addNewUser(user);
		
		return "user added";
	}

}
