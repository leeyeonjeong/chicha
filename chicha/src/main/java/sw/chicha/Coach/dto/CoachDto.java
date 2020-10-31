package sw.chicha.Coach.dto;

import lombok.*;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class CoachDto {

    private Long id;
    private Long expect;    // 예정
    private Long attendance;    // 출석
    private Long absen;     // 결석
    private Long reinforce;     // 보강완료
    private Long evaluation;    // 평가완료
    private Long accumulation;      // 누적
    private Therapist therapist;
    private Member member;

    public Coach toEntity() {
        return Coach.builder()
                .id(id)
                .expect(expect)
                .attendance(attendance)
                .absen(absen)
                .reinforce(reinforce)
                .evaluation(evaluation)
                .accumulation(accumulation)
                .therapist(therapist)
                .member(member)
                .build();
    }

    @Builder
    public CoachDto(Long id, Long expect, Long attendance, Long absen, Long reinforce, Long evaluation, Long accumulation,
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
