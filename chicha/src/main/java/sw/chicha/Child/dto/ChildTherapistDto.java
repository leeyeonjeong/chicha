package sw.chicha.Child.dto;

import lombok.*;
import sw.chicha.Child.domain.Child;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Therapist;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChildTherapistDto {
    // 이름, 성별, 상태, 생년월일, 전화번호, 주소, 메모
    private Long id;
    private String name;
    private String gender;
    private String state;
    private String birthday;
    private String phoneNumber;
    private String zipcode;
    private String firstAddr;
    private String secondAddr;
    private String memo;
    private Therapist therapist;

    public Child toEntity() {
        return Child.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .state(state)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .zipcode(zipcode)
                .firstAddr(firstAddr)
                .secondAddr(secondAddr)
                .memo(memo)
                .therapist(therapist)
                .build();
    }

    @Builder
    public ChildTherapistDto(Long id, String name, String gender, String state, String phoneNumber, String birthday,
                             String zipcode, String firstAddr, String secondAddr, String memo, Therapist therapist) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.zipcode = zipcode;
        this.firstAddr = firstAddr;
        this.secondAddr = secondAddr;
        this.memo = memo;
        this.therapist = therapist;
    }
}
