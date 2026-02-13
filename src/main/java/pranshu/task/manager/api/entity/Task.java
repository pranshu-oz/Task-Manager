package pranshu.task.manager.api.entity;

import java.util.Date;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	@Id
	private long id;
	
	private String title;
	
	private String description;
	
	private String status;
	
	private Date createdAt;
}
