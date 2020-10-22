package sw.chicha.Calendar.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Child.domain.Child;
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
    private String start;
    @Column(nullable = false)
    private String end;
    @Column(nullable = false)
    private String repitation;
    @Column(nullable = false)
    private String memo;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @Builder
    public Calendar(Long id, String name, String state, String start, String end, String repitation,
                    String memo, Therapist therapist, Child child) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.start = start;
        this.end = end;
        this.repitation = repitation;
        this.memo = memo;
        this.therapist = therapist;
        this.child = child;
    }
}
