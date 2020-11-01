package sw.chicha.Schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Schedule.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByCalendar_id (Long calendar_id);
}
