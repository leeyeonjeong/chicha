package sw.chicha.Member.dto;

import lombok.*;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class TherapistDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirm;

    private Long expect;    // 예정
    private Long attend;  // 출석
    private Long absent;  // 결석
    private Long strengthen;   // 보강완료
    private Long evaluation;  // 평가완료

    public Therapist toEntity() {
        return Therapist.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .expect(expect)
                .attend(attend)
                .absent(absent)
                .strengthen(strengthen)
                .evaluation(evaluation)
                .build();
    }

    @Builder
    public TherapistDto(Long id, String name, String phoneNumber, String email, String password,
                        Long expect, Long attend, Long absent, Long strengthen, Long evaluation) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirm = "0";
        this.expect = expect;
        this.attend = attend;
        this.absent = absent;
        this.strengthen = strengthen;
        this.evaluation = evaluation;
    }
}
