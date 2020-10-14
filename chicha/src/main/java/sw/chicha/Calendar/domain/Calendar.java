package sw.chicha.Calendar.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Getter
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String startTime;
    @Column(nullable = false)
    private String endTime;
    @Column(nullable = false)
    private String repitation;
    @Column(nullable = false)
    private String memo;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @Builder
    public Calendar(Long id, String name, String state, String startTime, String endTime, String repitation, String memo, Therapist therapist) {
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
