package co.grandcircus.capstone_6.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.capstone_6.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByEmail(String email);

}
