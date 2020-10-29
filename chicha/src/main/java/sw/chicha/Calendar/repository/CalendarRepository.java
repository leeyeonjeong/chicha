package sw.chicha.Calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Calendar.domain.Calendar;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Optional<Calendar> findByName(String name);
}
