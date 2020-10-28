package sw.chicha.Calendar.dto;

import lombok.*;
import sw.chicha.Calendar.domain.CalendarTherapist;
import sw.chicha.Member.domain.Member;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class CalendarMemberDto {

    private Long id;
    private String memo;
    private String child;
    private String currentDate;
    private Member member;

    public CalendarMemberDto toEntity() {
        return CalendarMemberDto.builder()
                .id(id)
                .memo(memo)
                .currentDate(currentDate)
                .member(member)
                .child(child)
                .build();
    }

    @Builder
    public CalendarMemberDto(Long id, String memo, String currentDate, Member member, String child) {
        this.id = id;
        this.memo = memo;
        this.currentDate = currentDate;
        this.member = member;
        this.child = child;
    }
}
