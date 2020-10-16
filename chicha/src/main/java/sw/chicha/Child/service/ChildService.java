package sw.chicha.Child.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.repository.MemberRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChildService {
    private ChildRepository childRepository;
    private MemberRepository memberRepository;

    public Long saveChild(ChildDto childDto) {
        if (childDto.getMember() == null) {
            childDto.setMember(memberRepository.findByEmail("member").get());
            if (childDto.getZipcode() == null || childDto.getZipcode() == "") {
                childDto.setZipcode(childDto.getMember().getZipcode());
            }
            if (childDto.getFirstAddr() == null || childDto.getFirstAddr() == "") {
                childDto.setFirstAddr(childDto.getMember().getFirstAddr());
            }
            if (childDto.getSecondAddr() == null || childDto.getSecondAddr() == "") {
                childDto.setSecondAddr(childDto.getMember().getSecondAddr());
            }
            if (childDto.getPhoneNumber() == null || childDto.getPhoneNumber() == "") {
                childDto.setPhoneNumber(childDto.getMember().getPhoneNumber());
            }
        } else {
            childDto.setPhoneNumber(childDto.getMember().getPhoneNumber());
            childDto.setZipcode(childDto.getMember().getZipcode());
            childDto.setFirstAddr(childDto.getMember().getFirstAddr());
            childDto.setSecondAddr(childDto.getMember().getSecondAddr());
        }
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

    @Transactional
    public ChildDto getChild(Long id) {
        Optional<Child> childWrapper = childRepository.findById(id);
        Child child = childWrapper.get();

        ChildDto childDto = ChildDto.builder()
                .id(child.getId())
                .name(child.getName())
                .picture(child.getPicture())
                .affiliation(child.getAffiliation())
                .birthday(child.getBirthday())
                .field(child.getField())
                .gender(child.getGender())
                .zipcode(child.getZipcode())
                .firstAddr(child.getFirstAddr())
                .secondAddr(child.getSecondAddr())
                .phoneNumber(child.getPhoneNumber())
                .build();

        return childDto;
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