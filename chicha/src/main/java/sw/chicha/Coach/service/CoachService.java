package sw.chicha.Coach.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Coach.dto.CoachDto;
import sw.chicha.Coach.repository.CoachRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachService {
    private CoachRepository coachRepository;

    public Long saveCalender(CoachDto coachDto) {
        return coachRepository.save(coachDto.toEntity()).getId();
    }

    public CoachDto getCalendar(Long id) {
        Optional<Coach> calendarWrapper = coachRepository.findById(id);
        if (calendarWrapper.isPresent()) {
            Coach coach = calendarWrapper.get();

            CoachDto coachDto = CoachDto.builder()
                    .id(coach.getId())
                    .expect(coach.getExpect())
                    .attendance(coach.getAttendance())
                    .absen(coach.getAbsen())
                    .reinforce(coach.getReinforce())
                    .accumulation(coach.getAccumulation())
                    .evaluation(coach.getEvaluation())
                    .therapist(coach.getTherapist())
                    .build();

            return coachDto;
        } else {
            return CoachDto.builder().build();
        }
    }

    public CoachDto getTherapist(Long id) {
        Optional<Coach> calendarWrapper = coachRepository.findById(id);
        if (calendarWrapper.isPresent()) {
            Coach coach = calendarWrapper.get();

            CoachDto coachDto = CoachDto.builder()
                    .id(coach.getId())
                    .therapist(coach.getTherapist())
                    .build();

            return coachDto;
        } else {
            return CoachDto.builder().build();
        }
    }

    public CoachDto getMember(Long id) {
        Optional<Coach> calendarWrapper = coachRepository.findById(id);
        if (calendarWrapper.isPresent()) {
            Coach coach = calendarWrapper.get();

            CoachDto coachDto = CoachDto.builder()
                    .id(coach.getId())
                    .member(coach.getMember())
                    .build();

            return coachDto;
        } else {
            return CoachDto.builder().build();
        }
    }

}
