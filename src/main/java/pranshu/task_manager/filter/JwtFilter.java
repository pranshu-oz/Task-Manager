package pranshu.task_manager.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pranshu.task_manager.service.JwtService;
import pranshu.task_manager.service.TaskUserService;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	TaskUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader!=null & authHeader.startsWith("Bearer ")) {
			
			String token = authHeader.substring(7);
			String username = jwtService.extractUsername(token);
			
			System.out.println("got username------------------------");
			
			if(username!=null) {
				
				UserDetails user = userService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				
				SecurityContextHolder.getContext()
					.setAuthentication(auth);
			}
		}
			
		filterChain.doFilter(request, response);
		
	}

}
