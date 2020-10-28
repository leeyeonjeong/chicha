package sw.chicha.Calendar.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Entity
@Getter
public class CalendarMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String memo;
    @Column(nullable = false)
    private String currentDate;
    private String child;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public CalendarMember(Long id, String memo, String currentDate, Member member, String child) {
        this.id = id;
        this.memo = memo;
        this.currentDate = currentDate;
        this.member = member;
        this.child = child;
    }

}
