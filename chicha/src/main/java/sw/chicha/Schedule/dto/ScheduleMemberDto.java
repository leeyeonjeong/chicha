package sw.chicha.Schedule.dto;

import lombok.*;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Schedule.domain.Schedule;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ScheduleMemberDto {

    private Long id;
    private String memo;
    private String child;
    private Calendar calendar;

    public Schedule toEntity() {
        return Schedule.builder()
                .id(id)
                .memo(memo)
                .child(child)
                .calendar(calendar)
                .build();
    }

    @Builder
    public ScheduleMemberDto(Long id, String memo, String child, Calendar calendar) {
        this.id = id;
        this.memo = memo;
        this.child = child;
        this.calendar = calendar;
    }
}
