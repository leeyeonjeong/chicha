package sw.chicha.Member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
