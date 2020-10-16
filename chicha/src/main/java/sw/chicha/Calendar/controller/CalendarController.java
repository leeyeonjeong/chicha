package sw.chicha.Calendar.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.service.CalendarService;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.service.ChildService;

import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private CalendarService calendarService;
    private ChildService childService;

    @GetMapping("therapist_calendar_month")
    public String therapist_calendar_month() {
        return "calendar/캘린더_치료사_월";
    }

    @GetMapping("therapist_calendar_day")
    public String therapist_calendar_day() {
        return "calendar/캘린더_치료사_일";
    }

    @GetMapping("therapist_calendar_registration")
    public String dis_therapist_calendar_registration() {
        return "calendar/캘린더_치료사_일정등록";
    }

    @GetMapping("therapist_calendar_childlist")
    public String therapist_calendar_childlist(Model model) {
        List<ChildDto> childList = childService.getChildList();

        model.addAttribute("childList", childList);
        return "calendar/아동리스트";
    }

    @GetMapping("therapist_calendar_child_registration")
    public String dis_therapist_calendar_child_registration() {
        return "calendar/아동등록";
    }

    @PostMapping("therapist_calendar_child_registration")
    public String exec_therapist_calendar_child_registration(ChildDto childDto) {
        childService.saveChild(childDto);

        return "redirect:/";
    }

    @GetMapping("therapist_calendar_child_registration_detail}")
    public String therapist_calendar_child_registration_detail() {
        return "calendar/아동등록_상세";
    }



    @PostMapping("therapist_calendar_registration")
    public String exec_therapist_calendar_registration(CalendarDto calendarDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("AUthhhhhhhhhhhhhhhhhh" +userDetails.getAuthorities());
        System.out.println("NAMMMeEEEEEEEEEEEEEEEEEEEEEe" +userDetails.getUsername());

        calendarService.saveCalender(calendarDto);
        return "redirect:/therapist_calendar_month";
    }
}
