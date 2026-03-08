package pranshu.task_manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class TaskManagerSecurity {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/user").permitAll()
					.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST).hasRole("MANAGER")
					.requestMatchers(HttpMethod.POST).hasRole("MANAGER")
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
			
					
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}