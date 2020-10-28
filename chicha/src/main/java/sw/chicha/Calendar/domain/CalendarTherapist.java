package sw.chicha.Calendar.domain;

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
public class CalendarTherapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String state;   // 전체 상태
    @Column(nullable = false)
    private String start;
    @Column(nullable = false)
    private String end;
    @Column(nullable = false)
    private String repitation;
    @Column(nullable = false)
    private String memo;
    private String child;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @Builder
    public CalendarTherapist(Long id, String name, String state, String start, String end, String repitation,
                             String memo, Therapist therapist, String child) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.start = start;
        this.end = end;
        this.repitation = repitation;
        this.memo = memo;
        this.therapist = therapist;
        this.child = child;
    }

}
