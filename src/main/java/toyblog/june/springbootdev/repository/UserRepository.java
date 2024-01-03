package toyblog.june.springbootdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyblog.june.springbootdev.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
