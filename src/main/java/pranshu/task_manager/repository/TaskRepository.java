package pranshu.task_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.task_manager.modal.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {



}
