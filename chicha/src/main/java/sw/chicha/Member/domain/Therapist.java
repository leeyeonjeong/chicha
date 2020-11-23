package sw.chicha.Member.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import sw.chicha.Calendar.domain.Calendar;

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
public class Therapist {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirm;     // 자격증 인증
    @CreationTimestamp  // 시간이 자동으로 입력
    private Timestamp createDate;
    @Enumerated(EnumType.STRING)
    private Role role; // Enum을 쓰는게 좋다. // ADMIN, USER

    @OneToMany(mappedBy = "therapist")
    private List<Calendar> calendars = new ArrayList<Calendar>();

    @Builder
    public Therapist(Long id, String name, String phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirm = "0";
    }
}
