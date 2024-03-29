package sw.chicha.Coach.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import sw.chicha.Calendar.domain.Calendar;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Child.service.ChildService;
import sw.chicha.Coach.domain.Coach;
import sw.chicha.Coach.dto.CoachDto;
import sw.chicha.Coach.repository.CoachRepository;
import sw.chicha.Coach.service.CoachService;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;
import sw.chicha.Schedule.dto.ScheduleDto;
import sw.chicha.Schedule.repository.ScheduleRepository;
import sw.chicha.Schedule.service.ScheduleService;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class CoachController {
    private CoachService coachService;
    private TherapistRepository therapistRepository;
    private CalendarRepository calendarRepository;
    private ScheduleService scheduleService;
    private ChildService childService;

    /**
     * 코칭회기
     * */

    // 캘린더 -> 코칭회기 클릭 -> 목록
    @GetMapping("therapist_calendar_sessionlist")
    public String therapist_calendar_sessionlist(Principal principal, Model model) {
        String currentName = (String)principal.getName();
        Long therapist_id = therapistRepository.findByEmail(currentName).get().getId();
        Long calendar_id = calendarRepository.findById(therapist_id).get().getId();
        List<ScheduleDto> scheduleDto = scheduleService.getScheduleList(calendar_id);
        model.addAttribute("scheduleDto", scheduleDto);
        return "calendar/캘린더_치료사_치료회기_목록";
    }

    // 코칭회기 목록 -> 상세
    @GetMapping("therapist_calendar_sessionlist/{id}")
    public String therapist_calendar_session(@PathVariable(name="id") Long id, Model model, Principal principal) {
        Long therapist_id = therapistRepository.findByEmail(principal.getName()).get().getId();
        List<ScheduleDto> scheduleDto = scheduleService.getScheduleList(therapist_id);
        ScheduleDto scheduleDto1 = scheduleService.getSchedule(id);
        List<CoachDto> coachList = coachService.getcoachList(id);
        
        if (!coachList.isEmpty()) {
            scheduleDto.stream().findAny().get().setCoach(coachList.stream().findAny().get().toEntity());
            scheduleDto.stream().findAny().get().setSession(coachList.stream().findAny().get().toEntity().getSession());
            scheduleDto.stream().findAny().get().setCounseling(coachList.stream().findAny().get().toEntity().getCounseling());
        }

        model.addAttribute("scheduleDto", scheduleDto);
        model.addAttribute("scheduleDto1", scheduleDto1);
        return "calendar/캘린더_치료사_치료회기";
    }

    // 코칭회기 등록
    @GetMapping("therapist_calendar_session_registration/{id}")
    public String dis_therapist_calendar_session_registration(@PathVariable(name="id") Long id, Principal principal, Model model) {
        Long therapist_id = therapistRepository.findByEmail(principal.getName()).get().getId();
        ScheduleDto scheduleDto = scheduleService.getSchedule(therapist_id);
        String childName = scheduleService.getSchedule(id).getName();
        model.addAttribute("scheduleDto", scheduleDto);
        model.addAttribute("childName", childName);
        return "calendar/캘린더_치료사_치료회기_등록";
    }

    // 코칭회기 저장
    @PostMapping("therapist_calendar_session_registration")
    public String exec_therapist_calendar_session_registration(Principal principal, CoachDto coachDto) {
        Long therapist_id = therapistRepository.findByEmail(principal.getName()).get().getId();
        ScheduleDto scheduleDto = scheduleService.getSchedule(therapist_id);

        coachDto.setSchedule(scheduleDto.toEntity());
        coachService.saveCalender(coachDto);

        return "redirect:/";
    }
}
