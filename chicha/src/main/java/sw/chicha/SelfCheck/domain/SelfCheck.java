package sw.chicha.SelfCheck.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 (protected type)
@Getter
@Entity
public class SelfCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String age;
    private String type;
    private Integer score;

    @Builder
    public SelfCheck(Long id, String gender, String age, String type, Integer score) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.type = type;
        this.score = score;
    }
}
