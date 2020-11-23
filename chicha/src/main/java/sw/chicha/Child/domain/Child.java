package sw.chicha.Child.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Entity
@Getter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String gender;
    private String picture;
    private String birthday;
    private String affiliation;
    private String field;
    private String state;   // 상태
    private String phoneNumber;
    private String zipcode;
    private String firstAddr;
    private String secondAddr;
    private String memo;
    @CreationTimestamp  // 시간이 자동으로 입력
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @Builder
    public Child(Long id, String name, String gender, String picture, String birthday, String affiliation, String state, String field, String phoneNumber,
                 String zipcode, String firstAddr, String secondAddr, String memo,Member member, Therapist therapist) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.picture = picture;
        this.birthday = birthday;
        this.affiliation = affiliation;
        this.field = field;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
        this.memo = memo;
        this.member = member;
        this.therapist = therapist;
    }
}
