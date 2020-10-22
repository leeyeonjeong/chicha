package sw.chicha.Calendar.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Child.domain.Child;
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

    public CalendarDto getCalendar(Long id) {
        Optional<Calendar> calendarWrapper = calendarRepository.findById(id);
        Calendar calendar = calendarWrapper.get();

        CalendarDto calendarDto = CalendarDto.builder()
                .id(calendar.getId())
                .name(calendar.getName())
                .state(calendar.getState())
                .child(calendar.getChild())
                .end(calendar.getEnd())
                .memo(calendar.getMemo())
                .repitation(calendar.getRepitation())
                .start(calendar.getStart())
                .therapist(calendar.getTherapist())
                .build();

        return calendarDto;
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
