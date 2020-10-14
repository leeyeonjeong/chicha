package sw.chicha.Child.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Child.domain.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
