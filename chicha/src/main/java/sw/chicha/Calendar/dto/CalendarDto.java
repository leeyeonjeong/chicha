package sw.chicha.Calendar.dto;

import lombok.*;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Member.domain.Therapist;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class CalendarDto {

    private Long id;
    private String name;
    private String state;
    private String startTime;
    private String endTime;
    private String repitation;
    private String memo;
    private Therapist therapist;

    public Calendar toEntity() {
        return Calendar.builder()
                .id(id)
                .name(name)
                .state(state)
                .startTime(startTime)
                .endTime(endTime)
                .repitation(repitation)
                .memo(memo)
                .therapist(therapist)
                .build();
    }

    @Builder
    public CalendarDto(Long id, String name, String state, String startTime, String endTime, String repitation, String memo, Therapist therapist) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.startTime =startTime;
        this.endTime = endTime;
        this.repitation = repitation;
        this.memo = memo;
        this.therapist = therapist;
    }
}
