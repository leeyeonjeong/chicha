package sw.chicha.SelfCheck.dto;

import lombok.*;
import sw.chicha.Member.domain.Member;
import sw.chicha.SelfCheck.domain.SelfCheck;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class SelfCheckDto {
    private Long id;
    private String gender;
    private String age;
    private String type;
    private Integer score;

    public SelfCheck toEntity() {
        return SelfCheck.builder()
                .id(id)
                .gender(gender)
                .age(age)
                .type(type)
                .score(score)
                .build();
    }

    @Builder
    public SelfCheckDto(Long id, String gender, String age, String type, Integer score) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.type = type;
        this.score = score;
    }
}
