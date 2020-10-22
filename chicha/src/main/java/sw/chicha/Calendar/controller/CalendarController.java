package sw.chicha.Calendar.controller;

import lombok.AllArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.apache.struts.mock.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.service.CalendarService;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Child.service.ChildService;
import sw.chicha.Member.repository.TherapistRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private CalendarService calendarService;
    private ChildRepository childRepository;
    private TherapistRepository therapistRepository;

    @GetMapping("therapist_calendar_month")
    public String therapist_calendar_month() {
        return "calendar/캘린더_치료사_월";
    }

    @GetMapping("therapist_calendar_day")
    public String therapist_calendar_day() {
        return "calendar/캘린더_치료사_일";
    }

    // month -> 코칭등록1
    @GetMapping("therapist_calendar_registration1")
    public String dis_therapist_calendar_registration1() {
        return "calendar/캘린더_치료사_일정등록1";
    }

    // 팝업 -> 코칭등록2
    @GetMapping("therapist_calendar_registration")
    public String dis_therapist_calendar_registration() {
        return "calendar/캘린더_치료사_일정등록2";
    }

    // 코칭등록2 -> 저장
    @PostMapping("therapist_calendar_registration")
    public String exec_therapist_calendar_registration(HttpSession session, CalendarDto calendarDto, Principal principal) {
        String currentName = (String)principal.getName();
        calendarDto.setTherapist(therapistRepository.findByEmail(currentName).get());
        calendarDto.setChild(childRepository.findByName((String)session.getAttribute("childName")).get());
        calendarService.saveCalender(calendarDto);
        return "redirect:/";
    }

    // 팝업 -> 코칭등록2
    @GetMapping("therapist_calendar_registration_pop/{id}")
    public String pop_therapist_calendar_registration(@PathVariable("id") Long id, Model model, HttpSession session) {
        ChildTherapistDto childTherapistDto = calendarService.getChildTherapist(id);
        session.setAttribute("childName", childTherapistDto.getName());
        model.addAttribute("childDto", childTherapistDto);
        return "calendar/캘린더_치료사_일정등록2";
    }

    @GetMapping("therapist_calendar_childlist")
    public String therapist_calendar_childlist(Model model) {
        List<ChildTherapistDto> childList = calendarService.getChildTherapistList();

        model.addAttribute("childList", childList);
        return "calendar/아동리스트";
    }

    @GetMapping("therapist_calendar_child_registration")
    public String dis_therapist_calendar_child_registration() {
        return "calendar/아동등록";
    }

    // 치료사 캘린더 -> 아동등록
    @PostMapping("therapist_calendar_child_registration")
    public String exec_therapist_calendar_child_registration(@RequestParam(value = "year") String year,
                                                             @RequestParam(value = "month") String month,
                                                             @RequestParam(value = "day") String day,
                                                             ChildTherapistDto childTherapistDto, Principal principal) {
        String currentName = (String)principal.getName();
        childTherapistDto.setTherapist(therapistRepository.findByEmail(currentName).get());
        String birth = year + "-" + month + "-" + day;
        childTherapistDto.setBirthday(birth);
        calendarService.saveChildTherapist(childTherapistDto);

        return "redirect:/";
    }

    // 치료사 캘린더 -> 아동등록 상세 페이지
    @GetMapping("therapist_calendar_child_registration_detail/{id}")
    public String therapist_calendar_child_registration_detail(@PathVariable("id") Long id, Model model) {
        ChildTherapistDto childTherapistDto = calendarService.getChildTherapist(id);

        model.addAttribute("childDto", childTherapistDto);
        return "calendar/아동등록_상세";
    }

    @GetMapping("/therapist_calendar_search_dis")
    public String dis_therapist_calendar_search(Model model) {
        List<ChildTherapistDto> childList = calendarService.getChildTherapistList();

        model.addAttribute("childList", childList);
        return "calendar/캘린더_치료사_팝업_아동검색";
    }

    @GetMapping("/therapist_calendar_search_exce")
    public String exce_therapist_calendar_search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<ChildTherapistDto> childDtoList = calendarService.searchPosts(keyword);
        model.addAttribute("childList", childDtoList);

        return "calendar/캘린더_치료사_팝업_아동검색";
    }


}
