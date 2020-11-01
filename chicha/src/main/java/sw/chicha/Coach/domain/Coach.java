package sw.chicha.Coach.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;
import sw.chicha.Schedule.domain.Schedule;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Entity
@Getter
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String session;
    private String counseling;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Builder
    public Coach(Long id, String session, String counseling, Schedule schedule) {
        this.id = id;
        this.session = session;
        this.counseling = counseling;
        this.schedule = schedule;
    }

}
