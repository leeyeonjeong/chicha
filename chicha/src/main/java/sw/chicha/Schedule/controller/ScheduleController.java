package sw.chicha.Schedule.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Calendar.service.CalendarService;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;
import sw.chicha.Schedule.dto.ScheduleDto;
import sw.chicha.Schedule.dto.ScheduleMemberDto;
import sw.chicha.Schedule.repository.ScheduleRepository;
import sw.chicha.Schedule.service.ScheduleService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
public class ScheduleController {
    private ScheduleService scheduleService;
    private TherapistRepository therapistRepository;
    private MemberRepository memberRepository;
    private CalendarRepository calendarRepository;
    private CalendarService calendarService;

    /**
     * 코칭등록
     * */

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

    // 팝업 -> 코칭등록2
    @GetMapping("therapist_calendar_registration_pop/{id}")
    public String pop_therapist_calendar_registration(@PathVariable("id") Long id, Model model, HttpSession session) {
        ChildTherapistDto childTherapistDto = scheduleService.getChildTherapist(id);
        session.setAttribute("childName", childTherapistDto.getName());
        model.addAttribute("childDto", childTherapistDto);
        return "calendar/캘린더_치료사_일정등록2";
    }

    // 코칭등록2 -> 저장
    @PostMapping("therapist_calendar_registration")
    public String exec_therapist_calendar_registration(HttpSession session, Principal principal, ScheduleDto scheduleDto) {
        String currentName = (String)principal.getName();
        Long therapist_id = therapistRepository.findByEmail(currentName).get().getId();
        Long calendar_id = calendarRepository.findById(therapist_id).get().getId();
        CalendarDto calendarDto = calendarService.getCalendar(calendar_id);

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%"+scheduleDto.getState());
        if (scheduleDto.getState().equals((String)"예정")) {
            calendarDto.setExpect(calendarDto.getExpect()+1);
        } else if (scheduleDto.getState().equals((String)"출석")) {
            calendarDto.setAttendance(calendarDto.getAttendance()+1);
        } else if (scheduleDto.getState().equals((String)"결석")) {
            calendarDto.setAbsen(calendarDto.getAbsen()+1);
        } else if (scheduleDto.getState().equals((String)"보강")) {
            calendarDto.setReinforce(calendarDto.getReinforce()+1);
        } else if (scheduleDto.getState().equals((String)"평가")) {
            calendarDto.setEvaluation(calendarDto.getEvaluation()+1);
        }
        // 누적
        calendarDto.setAccumulation(calendarDto.getExpect()+calendarDto.getAttendance()+calendarDto.getAbsen()+
                calendarDto.getReinforce()+calendarDto.getEvaluation());

        scheduleDto.setCalendar(calendarDto.toEntity());
        scheduleDto.setChild((String)session.getAttribute("childName"));

        calendarService.saveCalender(calendarDto);
        scheduleService.saveCalender(scheduleDto);

        return "redirect:/";
    }

    /**
     * 코칭회기
     * */

    @GetMapping("therapist_calendar_session")
    public String therapist_calendar_session(Principal principal, Model model) {
        String currentName = (String)principal.getName();
        Long therapist_id = therapistRepository.findByEmail(currentName).get().getId();
        Long calendar_id = calendarRepository.findById(therapist_id).get().getId();

        ScheduleDto scheduleDto = scheduleService.getSchedule(calendar_id);
        model.addAttribute("calendar", scheduleDto);
        return "calendar/캘린더_치료사_치료회기";
    }

    @GetMapping("therapist_calendar_sessionlist")
    public String therapist_calendar_sessionlist() {

        return "calendar/캘린더_치료사_치료회기_목록";
    }

    @GetMapping("therapist_calendar_session_registration")
    public String dis_therapist_calendar_session_registration() {
        return "calendar/캘린더_치료사_치료회기_등록";
    }

    // 치료회기 등록
    @PutMapping("therapist_calendar_session_registration/{id}")
    public String exec_therapist_calendar_session_registration(ScheduleDto scheduleDto) {
        scheduleService.saveCalender(scheduleDto);
        return "redirect:/therapist_calendar_sessionlist";
    }

    /**
     * 아동 리스트
     * */

    @GetMapping("therapist_calendar_childlist")
    public String therapist_calendar_childlist(Model model) {
        List<ChildTherapistDto> childList = scheduleService.getChildTherapistList();

        model.addAttribute("childList", childList);
        return "calendar/아동리스트";
    }

    /**
     * 아동 등록
     * */

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
        scheduleService.saveChildTherapist(childTherapistDto);

        return "redirect:/";
    }

    // 치료사 캘린더 -> 아동등록 상세 페이지
    @GetMapping("therapist_calendar_child_registration_detail/{id}")
    public String therapist_calendar_child_registration_detail(@PathVariable("id") Long id, Model model) {
        ChildTherapistDto childTherapistDto = scheduleService.getChildTherapist(id);

        model.addAttribute("childDto", childTherapistDto);
        return "calendar/아동등록_상세";
    }

    /**
     * 검색 (아동)
     * */

    @GetMapping("/therapist_calendar_search_dis")
    public String dis_therapist_calendar_search(Model model) {
        List<ChildTherapistDto> childList = scheduleService.getChildTherapistList();

        model.addAttribute("childList", childList);
        return "calendar/캘린더_치료사_팝업_아동검색";
    }

    @GetMapping("/therapist_calendar_search_exce")
    public String exce_therapist_calendar_search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<ChildTherapistDto> childDtoList = scheduleService.searchPosts(keyword);
        model.addAttribute("childList", childDtoList);

        return "calendar/캘린더_치료사_팝업_아동검색";
    }

    /**
     * 일반 일정등록
     * */

    @GetMapping("member_calendar_registration")
    public String dis_member_calendar_registration() {
        return "calendar/캘린더_일반_기록하기";
    }

    @PostMapping("member_calendar_registration")
    public String exec_member_calendar_registration(Principal principal, ScheduleMemberDto scheduleMemberDto) {
        String currentName = (String)principal.getName();
        Long member_id = memberRepository.findByEmail(currentName).get().getId();
        Long calendar_id = calendarRepository.findById(member_id).get().getId();
        CalendarDto calendarDto = calendarService.getCalendar(calendar_id);

        scheduleMemberDto.setChild(calendarRepository.findById(calendar_id).get().getMember().getChild().getName());
        scheduleMemberDto.setCalendar(calendarDto.toEntity());

        scheduleService.saveCalenderMember(scheduleMemberDto);
        return "redirect:/";
    }

}
