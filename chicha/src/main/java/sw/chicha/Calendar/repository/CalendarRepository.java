package sw.chicha.Calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Calendar.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
