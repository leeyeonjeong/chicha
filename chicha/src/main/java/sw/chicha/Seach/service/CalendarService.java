package sw.chicha.Seach.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Seach.domain.Calendar;
import sw.chicha.Seach.dto.CalendarDto;
import sw.chicha.Seach.repository.CalendarRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CalendarService {
    private CalendarRepository calendarRepository;

    public Long saveCalender(CalendarDto calendarDto) {
        return calendarRepository.save(calendarDto.toEntity()).getId();
    }

    public CalendarDto getCalendar(Long id) {
        Optional<Calendar> calendarWrapper = calendarRepository.findById(id);
        if (calendarWrapper.isPresent()) {
            Calendar calendar = calendarWrapper.get();

            CalendarDto calendarDto = CalendarDto.builder()
                    .id(calendar.getId())
                    .expect(calendar.getExpect())
                    .attendance(calendar.getAttendance())
                    .absen(calendar.getAbsen())
                    .reinforce(calendar.getReinforce())
                    .accumulation(calendar.getAccumulation())
                    .evaluation(calendar.getEvaluation())
                    .therapist(calendar.getTherapist())
                    .build();

            return calendarDto;
        } else {
            return CalendarDto.builder().build();
        }
    }

    public CalendarDto getTherapist(Long id) {
        Optional<Calendar> calendarWrapper = calendarRepository.findById(id);
        if (calendarWrapper.isPresent()) {
            Calendar calendar = calendarWrapper.get();

            CalendarDto calendarDto = CalendarDto.builder()
                    .id(calendar.getId())
                    .therapist(calendar.getTherapist())
                    .build();

            return calendarDto;
        } else {
            return CalendarDto.builder().build();
        }
    }

    public CalendarDto getMember(Long id) {
        Optional<Calendar> calendarWrapper = calendarRepository.findById(id);
        if (calendarWrapper.isPresent()) {
            Calendar calendar = calendarWrapper.get();

            CalendarDto calendarDto = CalendarDto.builder()
                    .id(calendar.getId())
                    .member(calendar.getMember())
                    .build();

            return calendarDto;
        } else {
            return CalendarDto.builder().build();
        }
    }

}
