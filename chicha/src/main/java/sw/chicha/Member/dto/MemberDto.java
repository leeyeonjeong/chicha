package sw.chicha.Member.dto;

import lombok.*;
import sw.chicha.Member.domain.Member;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberDto {

    private Long id;
    private String name;
    private String phone_number;
    private String email;
    private String password;
    private String zipcode;
    private String firstAddr;
    private String secondAddr;


    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .phone_number(phone_number)
                .email(email)
                .password(password)
                .zipcode(zipcode)
                .firstAddr(firstAddr)
                .secondAddr(secondAddr)
                .build();
    }

    @Builder
    public MemberDto(Long id, String name, String phone_number, String email, String password, String zipcode, String firstAddr, String secondAddr) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
    }
}
