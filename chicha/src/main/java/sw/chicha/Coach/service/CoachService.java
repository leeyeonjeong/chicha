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

    public CoachDto getCoach(Long id) {
        Optional<Coach> coachWrapper = coachRepository.findById(id);
        if (coachWrapper.isPresent()) {
            Coach coach = coachWrapper.get();

            CoachDto coachDto = CoachDto.builder()
                    .id(coach.getId())
                    .name(coach.getSchedule().getChild())
                    .counseling(coach.getCounseling())
                    .start(coach.getSchedule().getStart())
                    .end(coach.getSchedule().getEnd())
                    .session(coach.getSession())
                    .schedule(coach.getSchedule())
                    .build();

            return coachDto;
        } else {
            return CoachDto.builder().build();
        }
    }

}
