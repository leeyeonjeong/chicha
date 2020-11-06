package sw.chicha.Mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.chicha.Mypage.domain.Mypage;

public interface MypageRepository extends JpaRepository<Mypage, Long> {
}
