package sw.chicha.Schedule.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Entity
@Getter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;   // 전체 상태
    private String start;
    private String end;
    private String repitation;
    private String memo;
    private String child;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Builder
    public Schedule(Long id, String name, String state, String start, String end, String repitation,
                    String memo, String child, Calendar calendar) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.start = start;
        this.end = end;
        this.repitation = repitation;
        this.memo = memo;
        this.child = child;
        this.calendar = calendar;
    }

}
