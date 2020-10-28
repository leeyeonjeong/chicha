package sw.chicha.Calendar.dto;

import lombok.*;
import sw.chicha.Calendar.domain.CalendarTherapist;
import sw.chicha.Member.domain.Therapist;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class CalendarTherapistDto {

    private Long id;
    private String name;
    private String state;
    private String start;
    private String end;
    private String repitation;
    private String memo;
    private Therapist therapist;
    private String child;

    public CalendarTherapist toEntity() {
        return CalendarTherapist.builder()
                .id(id)
                .name(name)
                .state(state)
                .start(start)
                .end(end)
                .repitation(repitation)
                .memo(memo)
                .therapist(therapist)
                .child(child)
                .build();
    }

    @Builder
    public CalendarTherapistDto(Long id, String name, String state, String start, String end, String repitation,
                                String memo, Therapist therapist, String child) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.start = start;
        this.end = end;
        this.repitation = repitation;
        this.memo = memo;
        //this.therapist = therapist;
        this.child = child;
    }
}
