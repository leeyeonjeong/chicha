package sw.chicha.SelfCheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Member.domain.Member;
import sw.chicha.SelfCheck.domain.SelfCheck;

import java.util.Optional;

public interface SelfCheckRepository extends JpaRepository<SelfCheck, Long> {
}
