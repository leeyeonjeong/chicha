package sw.chicha.SelfCheck.dto;

import lombok.*;

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

    @Builder
    public SelfCheckDto(Long id, String gender, String age, String type, Integer score) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.type = type;
        this.score = score;
    }
}
