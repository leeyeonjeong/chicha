package sw.chicha.Child.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Child.domain.Child;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByNameContaining(String keyword);
    Optional<Child> findByName(String name);
    Long countByName(String name);
    Long findByMemberId(Long member_id);
}
