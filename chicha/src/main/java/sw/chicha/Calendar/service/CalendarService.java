package sw.chicha.Calendar.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Schedule.domain.Schedule;
import sw.chicha.Schedule.dto.ScheduleDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
        try{if (calendarWrapper.isPresent()) {
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
        } }catch (Exception e){
            e.printStackTrace();
        }
        return CalendarDto.builder().build();
    }

    public CalendarDto getTherapist(Long id) {
        Optional<Calendar> calendarWrapper = calendarRepository.findById(id);
        try {
            if (calendarWrapper.isPresent()) {
                Calendar calendar = calendarWrapper.get();

                CalendarDto calendarDto = CalendarDto.builder()
                        .id(calendar.getId())
                        .therapist(calendar.getTherapist())
                        .build();

                return calendarDto;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return CalendarDto.builder().build();
    }

    public CalendarDto getMember(Long id) {
        Optional<Calendar> calendarWrapper = calendarRepository.findById(id);
        try {if (calendarWrapper.isPresent()) {
            Calendar calendar = calendarWrapper.get();

            CalendarDto calendarDto = CalendarDto.builder()
                    .id(calendar.getId())
                    .member(calendar.getMember())
                    .build();

            return calendarDto;
        }} catch (Exception e){
            e.printStackTrace();
        }
        return CalendarDto.builder().build();
    }

}
