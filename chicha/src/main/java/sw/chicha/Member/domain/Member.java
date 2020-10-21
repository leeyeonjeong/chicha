package sw.chicha.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,20}")
    private String email;

    @Column(nullable = false)
    @NotBlank
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,20}")
    private String password;

    // 우편번호
    @Column(nullable = false)
    @NotBlank
    private String zipcode;

    // 지번주소
    @Column(nullable = false)
    @NotBlank
    private String firstAddr;

    // 상세주소
    @Column(nullable = false)
    private String secondAddr;

    @Builder
    public Member(Long id, String name, String phoneNumber, String email, String password, String zipcode, String firstAddr, String secondAddr) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
    }
}
