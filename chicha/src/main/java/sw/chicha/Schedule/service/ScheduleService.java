package sw.chicha.Schedule.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Schedule.domain.Schedule;
import sw.chicha.Schedule.dto.ScheduleDto;
import sw.chicha.Schedule.repository.ScheduleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private ChildRepository childRepository;

    // 일정 전체 저장
    public Long saveCalender(ScheduleDto scheduleDto) {
        return scheduleRepository.save(scheduleDto.toEntity()).getId();
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

    // 일정 전체 반환
    public ScheduleDto getSchedule(Long id) {
        Optional<Schedule> scheduleWrapper = scheduleRepository.findById(id);
        if (scheduleWrapper.isPresent()) {
            Schedule schedule = scheduleWrapper.get();

            ScheduleDto scheduleDto = ScheduleDto.builder()
                    .id(schedule.getId())
                    .name(schedule.getName())
                    .state(schedule.getState())
                    .child(schedule.getChild())
                    .end(schedule.getEnd())
                    .memo(schedule.getMemo())
                    .repitation(schedule.getRepitation())
                    .start(schedule.getStart())
                    .calendar(schedule.getCalendar())
                    .build();

            return scheduleDto;
        } else {
            return ScheduleDto.builder().build();
        }
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
