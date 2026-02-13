package pranshu.task.manager.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.task.manager.api.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

	List<Task> geAllTask();

}
