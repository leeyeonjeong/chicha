package sw.chicha.Seach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Seach.domain.Calendar;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Optional<Calendar> findById(Long id);
}
