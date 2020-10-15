package sw.chicha.Child.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Child.domain.Child;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByNameContaining(String keyword);

}
