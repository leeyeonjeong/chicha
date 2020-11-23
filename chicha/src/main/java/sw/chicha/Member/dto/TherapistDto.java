package sw.chicha.Member.dto;

import lombok.*;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Role;
import sw.chicha.Member.domain.Therapist;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TherapistDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirm;
    @Enumerated(EnumType.STRING)
    private Role role; // Enum을 쓰는게 좋다. // ADMIN, USER

    public Therapist toEntity() {
        return Therapist.builder()
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
        this.confirm = "0";
    }
}
