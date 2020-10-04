package sw.chicha.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 16, nullable = false)
    private String phone_number;

    @Column(length = 12, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    // 우편번호
    @Column(nullable = false)
    private String zipcode;

    // 지번주소
    @Column(nullable = false)
    private String firstAddr;

    // 상세주소
    @Column(nullable = false)
    private String secondAddr;

    @Builder
    public Member(Long id, String name, String phone_number, String email, String password, String zipcode, String firstAddr, String secondAddr) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
    }
}
