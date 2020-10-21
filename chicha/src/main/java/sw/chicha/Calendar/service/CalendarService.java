package sw.chicha.Calendar.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalendarService {
    private CalendarRepository calendarRepository;
    private ChildRepository childRepository;
    
    public Long saveCalender(CalendarDto calendarDto) {
        return calendarRepository.save(calendarDto.toEntity()).getId();
    }

    // 이름, 성별, 상태, 생년월일, 전화번호, 주소, 메모
    public Long saveChildTherapist(ChildTherapistDto childTherapistDto) {
        return childRepository.save(childTherapistDto.toEntity()).getId();
    }

//    public Long saveChild(ChildDto childDto) {
//        if (childDto.getMember() == null) {
//            childDto.setMember(memberRepository.findByEmail("member").get());
//            if (childDto.getZipcode() == null || childDto.getZipcode() == "") {
//                childDto.setZipcode(childDto.getMember().getZipcode());
//            }
//            if (childDto.getFirstAddr() == null || childDto.getFirstAddr() == "") {
//                childDto.setFirstAddr(childDto.getMember().getFirstAddr());
//            }
//            if (childDto.getSecondAddr() == null || childDto.getSecondAddr() == "") {
//                childDto.setSecondAddr(childDto.getMember().getSecondAddr());
//            }
//            if (childDto.getPhoneNumber() == null || childDto.getPhoneNumber() == "") {
//                childDto.setPhoneNumber(childDto.getMember().getPhoneNumber());
//            }
//        } else {
//            childDto.setPhoneNumber(childDto.getMember().getPhoneNumber());
//            childDto.setZipcode(childDto.getMember().getZipcode());
//            childDto.setFirstAddr(childDto.getMember().getFirstAddr());
//            childDto.setSecondAddr(childDto.getMember().getSecondAddr());
//        }
//        return childRepository.save(childDto.toEntity()).getId();
//    }

    // 이름, 성별, 생년월일
    public List<ChildTherapistDto> getChildTherapistList() {
        List<Child> childs = childRepository.findAll();
        List<ChildTherapistDto> childTherapistDtoList = new ArrayList<>();

        for (Child child : childs) {
            ChildTherapistDto childTherapistDto = ChildTherapistDto.builder()
                    .id(child.getId())
                    .name(child.getName())
                    .gender(child.getGender())
                    .birthday(child.getBirthday())
                    .build();

            childTherapistDtoList.add(childTherapistDto);
        }

        return childTherapistDtoList;
    }

    // 이름, 성별, 상태, 생년월일, 전화번호, 주소, 메모
    @Transactional
    public ChildTherapistDto getChildTherapist(Long id) {
        Optional<Child> childWrapper = childRepository.findById(id);
        Child child = childWrapper.get();

        ChildTherapistDto childTherapistDto = ChildTherapistDto.builder()
                .id(child.getId())
                .name(child.getName())
                .gender(child.getGender())
                .state(child.getState())
                .birthday(child.getBirthday())
                .phoneNumber(child.getPhoneNumber())
                .zipcode(child.getZipcode())
                .firstAddr(child.getFirstAddr())
                .secondAddr(child.getSecondAddr())
                .memo(child.getMemo())
                .build();

        return childTherapistDto;
    }


//    @Transactional
//    public ChildDto getChild(Long id) {
//        Optional<Child> childWrapper = childRepository.findById(id);
//        Child child = childWrapper.get();
//
//        ChildDto childDto = ChildDto.builder()
//                .id(child.getId())
//                .name(child.getName())
//                .picture(child.getPicture())
//                .affiliation(child.getAffiliation())
//                .birthday(child.getBirthday())
//                .field(child.getField())
//                .gender(child.getGender())
//                .zipcode(child.getZipcode())
//                .firstAddr(child.getFirstAddr())
//                .secondAddr(child.getSecondAddr())
//                .phoneNumber(child.getPhoneNumber())
//                .build();
//
//        return childDto;
//    }

    public List<ChildTherapistDto> searchPosts(String keyword) {
        List<Child> childs = childRepository.findByNameContaining(keyword);
        List<ChildTherapistDto> childTherapistDtoList = new ArrayList<>();

        if(childs.isEmpty()) return childTherapistDtoList;

        for(Child child : childs) {
            childTherapistDtoList.add(this.convertEntityToDto(child));
        }

        return childTherapistDtoList;
    }

    private ChildTherapistDto convertEntityToDto(Child child) {
        return ChildTherapistDto.builder()
                .id(child.getId())
                .name(child.getName())
                .build();
    }
}
