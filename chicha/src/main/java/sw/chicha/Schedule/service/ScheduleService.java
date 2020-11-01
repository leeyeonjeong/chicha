package sw.chicha.Schedule.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Schedule.domain.Schedule;
import sw.chicha.Schedule.dto.ScheduleDto;
import sw.chicha.Schedule.dto.ScheduleMemberDto;
import sw.chicha.Schedule.repository.ScheduleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private ChildRepository childRepository;
    private TherapistRepository therapistRepository;

    // 일정 전체 저장
    public Long saveCalender(ScheduleDto scheduleDto) {
        return scheduleRepository.save(scheduleDto.toEntity()).getId();
    }

    // 멤버
    public Long saveCalenderMember(ScheduleMemberDto scheduleMemberDto) {
        return scheduleRepository.save(scheduleMemberDto.toEntity()).getId();
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

    // 일정 반환 리스트
    public List<ScheduleDto> getScheduleList(Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        List<Calendar> calendarList = therapistRepository.findByEmail(userDetails.getUsername()).get().getCalendars();
        Long calendar_id = calendarList.stream().findAny().get().getId();

        List<Schedule> schedules = scheduleRepository.findByCalendar_id(calendar_id);
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();
        for (Schedule schedule : schedules) {
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
                    .gender(schedule.getGender())
                    .birthday(schedule.getBirthday())
                    .build();
            scheduleDtoList.add(scheduleDto);
        }

        return scheduleDtoList;
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
                    .birthday(schedule.getBirthday())
                    .gender(schedule.getGender())
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
