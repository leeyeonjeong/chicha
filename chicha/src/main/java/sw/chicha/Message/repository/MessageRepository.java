package sw.chicha.Message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Message.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
