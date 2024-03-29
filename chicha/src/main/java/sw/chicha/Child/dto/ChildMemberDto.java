package sw.chicha.Child.dto;

import lombok.*;
import sw.chicha.Child.domain.Child;
import sw.chicha.Member.domain.Member;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ChildMemberDto {

    private Long id;
    private String name;
    private String gender;
    private String picture;
    private String birthday;
    private String affiliation;
    private String field;
    private Member member;

    public Child toEntity() {
        return Child.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .picture(picture)
                .birthday(birthday)
                .affiliation(affiliation)
                .field(field)
                .member(member)
                .build();
    }

    @Builder
    public ChildMemberDto(Long id, String name, String gender, String picture, String birthday,
                          String affiliation, String field, Member member) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.picture = picture;
        this.birthday = birthday;
        this.affiliation = affiliation;
        this.field = field;
        this.member = member;
    }
}
