package co.grandcircus.capstone_6.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import co.grandcircus.capstone_6.Task;
import co.grandcircus.capstone_6.User;

public interface TaskDao extends JpaRepository<Task, Long> {
	
	Task findByUser(User user);
	List<Task> findAllByUser(User user);
}
