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

    //@Column(length = 256, nullable = false)
    //private String address;

    @Builder
    public Member(Long id, String name, String phone_number, String email, String password) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        //this.address = address;
    }
}
