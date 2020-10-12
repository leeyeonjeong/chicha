package sw.chicha.Member.dto;

import lombok.*;
import sw.chicha.Member.domain.Member;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class TherapistDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Boolean confirm;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public TherapistDto(Long id, String name, String phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirm = false;
    }
}
