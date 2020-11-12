package sw.chicha.Coach.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Coach.dto.CoachDto;
import sw.chicha.Coach.repository.CoachRepository;
import sw.chicha.Schedule.domain.Schedule;
import sw.chicha.Schedule.dto.ScheduleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachService {
    private CoachRepository coachRepository;

    public Long saveCalender(CoachDto coachDto) {
        return coachRepository.save(coachDto.toEntity()).getId();
    }

    public List<CoachDto> getcoachList(Long id) {
        List<Coach> coaches = coachRepository.findBySchedule_id(id);
        List<CoachDto> coachDtoList = new ArrayList<>();

        if(coaches.isEmpty()) return coachDtoList;

        for (Coach coach : coaches) {
            CoachDto coachDto = CoachDto.builder()
                    .id(coach.getId())
                    .counseling(coach.getCounseling())
                    .session(coach.getSession())
                    .schedule(coach.getSchedule())
                    .build();
            coachDtoList.add(coachDto);
        }

        return coachDtoList;
    }

    public CoachDto getCoach(Long id) {
        try {
        if (coachRepository.findBySchedule_id(id) != null) {
            Optional<Coach> coachWrapper = coachRepository.findBySchedule_id(id).stream().findAny();
            if (coachWrapper.isPresent()) {
                Coach coach = coachWrapper.get();

                CoachDto coachDto = CoachDto.builder()
                        .id(coach.getId())
                        .counseling(coach.getCounseling())
                        .session(coach.getSession())
                        .schedule(coach.getSchedule())
                        .build();

                return coachDto;
            }
        }} catch (Exception e){
            e.printStackTrace();
        }
        return CoachDto.builder().build();
    }

}
