package sw.chicha.Member.dto;

import lombok.*;
import sw.chicha.Child.domain.Child;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MemberDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String zipcode;
    private String firstAddr;
    private String secondAddr;
    private List<Child> childs;
    @Enumerated(EnumType.STRING)
    private Role role; // Enum을 쓰는게 좋다. // ADMIN, USER

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .zipcode(zipcode)
                .firstAddr(firstAddr)
                .secondAddr(secondAddr)
                .childs(childs)
                .build();
    }

    @Builder
    public MemberDto(Long id, String name, String phoneNumber, String email, String password,
                     String zipcode, String firstAddr, String secondAddr, List<Child> childs) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
        this.childs = childs;
    }
}
