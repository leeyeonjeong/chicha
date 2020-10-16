package sw.chicha.Child.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChildService {
    private ChildRepository childRepository;
    private MemberRepository memberRepository;

    public Long saveChild(ChildDto childDto) {
        if (childDto.getMember() == null) {
            childDto.setMember(memberRepository.findByEmail("member").get());
        }

        childDto.setPhoneNumber(childDto.getMember().getPhoneNumber());
        childDto.setZipcode(childDto.getMember().getZipcode());
        childDto.setFirstAddr(childDto.getMember().getFirstAddr());
        childDto.setSecondAddr(childDto.getMember().getSecondAddr());
        return childRepository.save(childDto.toEntity()).getId();
    }

    public List<ChildDto> getChildList() {
        List<Child> childs = childRepository.findAll();
        List<ChildDto> childDtoList = new ArrayList<>();

        for (Child child : childs) {
            ChildDto childDto = ChildDto.builder()
                    .id(child.getId())
                    .name(child.getName())
                    .gender(child.getGender())
                    .field(child.getField())
                    .birthday(child.getBirthday())
                    .affiliation(child.getAffiliation())
                    .picture(child.getPicture())
                    .build();

            childDtoList.add(childDto);
        }

        return childDtoList;
    }

    public List<ChildDto> searchPosts(String keyword) {
        List<Child> childs = childRepository.findByNameContaining(keyword);
        List<ChildDto> childDtoList = new ArrayList<>();

        if(childs.isEmpty()) return childDtoList;

        for(Child child : childs) {
            childDtoList.add(this.convertEntityToDto(child));
        }

        return childDtoList;
    }

    private ChildDto convertEntityToDto(Child child) {
        return ChildDto.builder()
                .id(child.getId())
                .name(child.getName())
                .gender(child.getGender())
                .field(child.getField())
                .birthday(child.getBirthday())
                .affiliation(child.getAffiliation())
                .picture(child.getPicture())
                .build();
    }

}