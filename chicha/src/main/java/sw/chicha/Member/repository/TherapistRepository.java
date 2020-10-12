package sw.chicha.Member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Member.domain.Therapist;

import java.util.Optional;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    Optional<Therapist> findByEmail(String email);
}
