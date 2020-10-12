package sw.chicha.OAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.OAuth.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
