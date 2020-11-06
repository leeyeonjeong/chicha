package sw.chicha.Mypage.dto;

import lombok.*;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Schedule.domain.Schedule;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class MypageDto {

    private Long id;
    private String session;
    private String counseling;
    private Schedule schedule;

    public Coach toEntity() {
        return Coach.builder()
                .id(id)
                .session(session)
                .counseling(counseling)
                .schedule(schedule)
                .build();
    }

    @Builder
    public MypageDto(Long id, String session, String counseling, Schedule schedule) {
        this.id = id;
        this.session = session;
        this.counseling = counseling;
        this.schedule = schedule;
    }
}
