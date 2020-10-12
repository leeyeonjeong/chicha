package sw.chicha.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Getter
@Entity
public class Therapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 16, nullable = false)
    private String phoneNumber;

    @Column(length = 12, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean confirm;

    @Builder
    public Therapist(Long id, String name, String phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirm = false;
    }
}
