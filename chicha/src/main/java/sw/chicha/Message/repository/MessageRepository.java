package sw.chicha.Message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Message.domain.Room;

public interface MessageRepository extends JpaRepository<Room, Long> {
}
