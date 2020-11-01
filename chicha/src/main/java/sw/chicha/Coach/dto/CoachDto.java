package sw.chicha.Coach.dto;

import lombok.*;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;
import sw.chicha.Schedule.domain.Schedule;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class CoachDto {

    private Long id;
    private String name;
    private LocalDate createdDate;
    private String start;
    private String end;
    private String session;
    private String counseling;
    private Schedule schedule;

    public Coach toEntity() {
        return Coach.builder()
                .id(id)
                .name(name)
                .start(start)
                .end(end)
                .session(session)
                .counseling(counseling)
                .schedule(schedule)
                .build();
    }

    @Builder
    public CoachDto(Long id, String name, String start, String end, String session, String counseling, Schedule schedule) {
        this.id = id;
        this.name = name;
        this.createdDate = LocalDate.now();
        this.start = start;
        this.end = end;
        this.session = session;
        this.counseling = counseling;
        this.schedule = schedule;
    }
}
