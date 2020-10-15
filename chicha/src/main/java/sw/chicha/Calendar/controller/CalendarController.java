package sw.chicha.Calendar.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.service.CalendarService;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/therapist/calendar")    // url Mapping
public class CalendarController {
    private CalendarService calendarService;

    @GetMapping("/month")
    public String therapist_calendar_month() {
        return "calendar/캘린더_치료사_월";
    }

    @GetMapping("/day")
    public String therapist_calendar_day() {
        return "calendar/캘린더_치료사_일";
    }

    @GetMapping("/registration")
    public String dis_therapist_calendar_registration() {
        return "calendar/캘린더_치료사_일정등록";
    }

    @PostMapping("/registration")
    public String exec_therapist_calendar_registration(CalendarDto calendarDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("AUthhhhhhhhhhhhhhhhhh" +userDetails.getAuthorities());
        System.out.println("NAMMMeEEEEEEEEEEEEEEEEEEEEEe" +userDetails.getUsername());

        calendarService.saveCalender(calendarDto);
        return "redirect:/therapist/calendar/month";
    }
}
