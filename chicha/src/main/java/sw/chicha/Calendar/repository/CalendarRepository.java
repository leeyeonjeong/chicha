package sw.chicha.Calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Calendar.domain.CalendarTherapist;
import sw.chicha.Child.domain.Child;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<CalendarTherapist, Long> {
    Optional<CalendarTherapist> findByName(String name);
    Long countByChild(Child child);
}
