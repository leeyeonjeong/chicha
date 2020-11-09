package sw.chicha.Message.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Schedule.domain.Schedule;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Entity
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String session;
    private String counseling;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Builder
    public Message(Long id, String session, String counseling, Schedule schedule) {
        this.id = id;
        this.session = session;
        this.counseling = counseling;
        this.schedule = schedule;
    }

}
