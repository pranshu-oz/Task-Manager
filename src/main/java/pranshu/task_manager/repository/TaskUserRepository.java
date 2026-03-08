package pranshu.task_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.task_manager.model.TaskUser;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUser,Long> {

	Optional<TaskUser> findUserByUsername(String username);
}
