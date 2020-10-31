package sw.chicha.Coach.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Entity
@Getter
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expect;    // 예정
    private Long attendance;    // 출석
    private Long absen;     // 결석
    private Long reinforce;     // 보강완료
    private Long evaluation;    // 평가완료
    private Long accumulation;      // 누적

    @OneToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Coach(Long id, Long expect, Long attendance, Long absen, Long reinforce, Long evaluation, Long accumulation,
                 Therapist therapist, Member member) {
        this.id = id;
        this.expect = expect;
        this.attendance = attendance;
        this.absen = absen;
        this.reinforce = reinforce;
        this.evaluation = evaluation;
        this.accumulation = accumulation;
        this.therapist = therapist;
        this.member = member;
    }

}
