package sw.chicha.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Child.domain.Child;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String zipcode;     // 우편번호
    private String firstAddr;      // 지번주소
    private String secondAddr;      // 상세주소

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_id")
    private Child child;

    @Builder
    public Member(Long id, String name, String phoneNumber, String email, String password,
                  String zipcode, String firstAddr, String secondAddr, Child child) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
        this.child = child;
    }
}
