package benmoncast.com.springboot_user_login.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import benmoncast.com.springboot_user_login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}

