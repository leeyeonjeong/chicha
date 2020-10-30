package sw.chicha.Schedule.dto;

import lombok.*;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Member.domain.Therapist;
import sw.chicha.Schedule.domain.Schedule;

import javax.persistence.Column;

@NoArgsConstructor
@Setter
@Getter
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
    private Calendar calendar;

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
                .calendar(calendar)
                .build();
    }

    @Builder
    public ScheduleDto(Long id, String name, String state, String start, String end, String repitation,
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
