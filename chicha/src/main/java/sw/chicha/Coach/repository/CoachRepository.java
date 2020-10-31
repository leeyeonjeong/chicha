package sw.chicha.Coach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Coach.domain.Coach;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findById(Long id);
}
