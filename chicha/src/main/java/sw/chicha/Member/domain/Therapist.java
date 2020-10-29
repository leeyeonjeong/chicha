package sw.chicha.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Calendar.domain.Calendar;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Getter
@Entity
public class Therapist {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,20}")
    private String email;

    @Column(nullable = false)
    @NotBlank
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,20}")
    private String password;

    // 자격증 인증
    @Column(nullable = false)
    private String confirm;

    @OneToMany(mappedBy = "therapist")
    private List<Calendar> calendars = new ArrayList<Calendar>();

    private Long expect;    // 예정
    private Long attend;  // 출석
    private Long absent;  // 결석
    private Long strengthen;   // 보강완료
    private Long evaluation;  // 평가완료

    @Builder
    public Therapist(Long id, String name, String phoneNumber, String email, String password,
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
