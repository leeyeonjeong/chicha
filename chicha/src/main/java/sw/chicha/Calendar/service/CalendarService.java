package sw.chicha.Calendar.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;

@Service
@AllArgsConstructor
public class CalendarService {
    private CalendarRepository calendarRepository;
    
    public Long saveCalender(CalendarDto calendarDto) {
        return calendarRepository.save(calendarDto.toEntity()).getId();
    }
}
