package sw.chicha.Schedule.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
    private String gender;
    private String birthday;

    @CreatedDate
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Builder
    public Schedule(Long id, String name, String state, String start, String end, String repitation,
                    String memo, String child, String gender, String birthday, LocalDate createdDate,Calendar calendar) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.start = start;
        this.end = end;
        this.repitation = repitation;
        this.memo = memo;
        this.child = child;
        this.gender = gender;
        this.birthday = birthday;
        this.createdDate = createdDate;
        this.calendar = calendar;
    }

}
