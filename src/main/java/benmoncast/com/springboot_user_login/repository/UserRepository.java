package benmoncast.com.springboot_user_login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import benmoncast.com.springboot_user_login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserRepository findByEmail(String email);
}

