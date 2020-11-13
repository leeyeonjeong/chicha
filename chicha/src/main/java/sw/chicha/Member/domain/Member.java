package sw.chicha.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw.chicha.Child.domain.Child;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
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
