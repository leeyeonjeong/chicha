package sw.chicha.Coach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Schedule.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findBySchedule_id (Long schedule_id);
}
