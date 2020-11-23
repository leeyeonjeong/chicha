package sw.chicha.Schedule.dto;

import lombok.*;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Coach.dto.CoachDto;
import sw.chicha.Member.domain.Therapist;
import sw.chicha.Schedule.domain.Schedule;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ScheduleDto {

    private Long id;
    private String name;
    private String state;
    private String start;
    private String end;
    private String repitation;
    private String memo;
    private String child;
    private String gender;
    private String birthday;
    private String session;
    private String counseling;

    private Calendar calendar;
    private Coach coach;
    private LocalDate createdDate;

    public Schedule toEntity() {
        return Schedule.builder()
                .id(id)
                .name(name)
                .state(state)
                .start(start)
                .end(end)
                .repitation(repitation)
                .memo(memo)
                .child(child)
                .gender(gender)
                .birthday(birthday)
                .calendar(calendar)
                .createdDate(createdDate)
                .coach(coach)
                .build();
    }

    @Builder
    public ScheduleDto(Long id, String name, String state, String start, String end, String repitation, String memo, String child,
                       String gender, String birthday, Coach coach, Calendar calendar, LocalDate createdDate, String session, String counseling) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.start = start;
        this.end = end;
        this.repitation = repitation;
        this.memo = memo;
        this.child = child;
        this.gender = gender;
        this.birthday =birthday;
        this.coach = coach;
        this.session = session;
        this.counseling = counseling;
        this.calendar = calendar;
        this.createdDate = createdDate;
    }
}
