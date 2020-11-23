package sw.chicha.Schedule.dto;

import lombok.*;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Schedule.domain.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@ToString
public class ScheduleMemberDto {

    private Long id;
    private String memo;
    private String child;
    private LocalDate createdDate;
    private Calendar calendar;

    public Schedule toEntity() {
        return Schedule.builder()
                .id(id)
                .memo(memo)
                .child(child)
                .createdDate(createdDate)
                .calendar(calendar)
                .build();
    }

    @Builder
    public ScheduleMemberDto(Long id, String memo, String child, LocalDate createdDate,Calendar calendar) {
        this.id = id;
        this.memo = memo;
        this.child = child;
        this.calendar = calendar;
        this.createdDate = createdDate;
    }
}
