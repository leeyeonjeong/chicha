package sw.chicha.Child.domain;

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
    private String phoneNumber;
    private String zipcode;
    private String firstAddr;
    private String secondAddr;
    private String memo;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Child(Long id, String name, String gender, String picture, String birthday, String affiliation, String field, String phoneNumber,
                 String zipcode, String firstAddr, String secondAddr, String memo,Member member) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.picture = picture;
        this.birthday = birthday;
        this.affiliation = affiliation;
        this.field = field;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
        this.memo = memo;
        this.member = member;
    }
}
