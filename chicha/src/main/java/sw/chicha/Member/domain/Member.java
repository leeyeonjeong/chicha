package sw.chicha.Member.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import sw.chicha.Child.domain.Child;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감(Oracle-시퀀스, MySQL- auto_increment)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String zipcode;     // 우편번호
    @Column(nullable = false)
    private String firstAddr;      // 지번주소
    private String secondAddr;      // 상세주소
    @CreationTimestamp  // 시간이 자동으로 입력
    private Timestamp createDate;
    @Enumerated(EnumType.STRING)
    private Role role; // Enum을 쓰는게 좋다. // ADMIN, USER

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Child> childs = new ArrayList<Child>();

    @Builder
    public Member(Long id, String name, String phoneNumber, String email, String password,
                  String zipcode, String firstAddr, String secondAddr, List<Child> childs) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
        this.childs = childs;
    }
}
