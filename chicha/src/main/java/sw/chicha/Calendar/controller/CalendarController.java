package sw.chicha.Calendar.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/therapist")    // url Mapping
public class CalendarController {

    @GetMapping("/calendar_month")
    public String therapist_calendar_month() {
        return "캘린더_치료사_월";
    }

    @GetMapping("/calendar_day")
    public String therapist_calendar_day() {
        return "캘린더_치료사_일";
    }

    @GetMapping("/calendar_registration")
    public String therapist_calendar_registration() {
        return "캘린더_치료사_일정등록";
    }

    @GetMapping("/calendar_search")
    public String therapist_calendar_search() {
        return "캘린더_치료사_팝업_아동검색";
    }
}
