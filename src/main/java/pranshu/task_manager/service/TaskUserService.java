package pranshu.task_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pranshu.task_manager.dto.TaskUserResponse;
import pranshu.task_manager.model.TaskUser;
import pranshu.task_manager.repository.TaskUserRepository;

@Service
public class TaskUserService implements UserDetailsService {

	
	@Autowired
	TaskUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		TaskUser user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
		
		return User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles())
				.build();
	}

	public void addNewUser(TaskUserResponse response) {
		
		TaskUser user = mapToTaskUserResponse(response);
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		
	}
	
	private TaskUser mapToTaskUserResponse(TaskUserResponse user) {
		
		TaskUser taskUser = new TaskUser();
		taskUser.setPassword(user.getPassword());
		taskUser.setUsername(user.getUsername());
		taskUser.setRoles(user.getRoles());
		return taskUser;
	}
	
}
