package pranshu.task_manager.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String SECRET="TaK+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";
	
	private Key getKey() {
		
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public String generateTocken(String username) {
	
		return Jwts.builder()
		.setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()*1000*60))
		.signWith(getKey(),SignatureAlgorithm.HS256)
		.compact();
		
	}

	public String extractUsername(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	
	
	
	
	
	
}
