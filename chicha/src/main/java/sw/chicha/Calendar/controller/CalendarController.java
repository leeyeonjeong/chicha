package sw.chicha.Calendar.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Calendar.service.CalendarService;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;
import sw.chicha.Schedule.domain.Schedule;
import sw.chicha.Schedule.dto.ScheduleDto;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Controller
public class CalendarController {
    private CalendarService calendarService;
    private CalendarRepository calendarRepository;
    private ChildRepository childRepository;
    private TherapistRepository therapistRepository;
    private MemberService memberService;
    private MemberRepository memberRepository;

    /**
     * 캘린더 메인
     *
     * */

    @GetMapping("calendar_month")
    public ModelAndView calendar_month(Principal principal, CalendarDto calendarDto, Model model) {
        ModelAndView mv = new ModelAndView();
        try {
         // 사용자가 치료사일 경우
           if (therapistRepository.findByEmail(principal.getName()).isPresent()) {
               // 처음 캘린더를 실행할 경우
               if (calendarService.getTherapist(therapistRepository.findByEmail(principal.getName()).get().getId()).getId() == null) {
                   calendarDto.setTherapist(therapistRepository.findByEmail(principal.getName()).get());
                   calendarDto.setExpect(0L);
                   calendarDto.setAttendance(0L);
                   calendarDto.setAbsen(0L);
                   calendarDto.setReinforce(0L);
                   calendarDto.setEvaluation(0L);
                   calendarDto.setAccumulation(0L);
                   calendarService.saveCalender(calendarDto);
               } else {
                   calendarDto = calendarService.getCalendar(therapistRepository.findByEmail(principal.getName()).get().getId());
               }
               model.addAttribute("expect", calendarDto.getExpect());
               model.addAttribute("attendance", calendarDto.getAttendance());
               model.addAttribute("absen", calendarDto.getAbsen());
               model.addAttribute("reinforce", calendarDto.getReinforce());
               model.addAttribute("evaluation", calendarDto.getEvaluation());
               model.addAttribute("accumulation", calendarDto.getAccumulation());
               mv.setViewName("calendar/캘린더_치료사_월");
           // 사용자가 일반 멤버일 경우
           } else if (memberRepository.findByEmail(principal.getName()).isPresent()){
              if (calendarService.getMember(memberRepository.findByEmail(principal.getName()).get().getId()).getId() == null) {
                  calendarDto.setMember(memberRepository.findByEmail(principal.getName()).get());
                  calendarDto.setExpect(0L);
                  calendarDto.setAttendance(0L);
                  calendarDto.setAbsen(0L);
                  calendarDto.setReinforce(0L);
                  calendarDto.setEvaluation(0L);
                  calendarDto.setAccumulation(0L);
                  calendarService.saveCalender(calendarDto);
              } else {
                  calendarDto = calendarService.getCalendar(memberRepository.findByEmail(principal.getName()).get().getId());
              }
               model.addAttribute("expect", calendarDto.getExpect());
               model.addAttribute("attendance", calendarDto.getAttendance());
               model.addAttribute("absen", calendarDto.getAbsen());
               model.addAttribute("reinforce", calendarDto.getReinforce());
               model.addAttribute("evaluation", calendarDto.getEvaluation());
               model.addAttribute("accumulation", calendarDto.getAccumulation());
               mv.setViewName("calendar/캘린더_일반_월");
           }
        } catch(Exception e) {
            mv.setViewName("error/null");
        }

        return mv;
    }

    @GetMapping("therapist_calendar_day/{day}")
    public String therapist_calendar_day(@PathVariable(name = "day")String day) {

        return "calendar/캘린더_치료사_일";
    }

    @GetMapping("member_calendar_day")
    public String member_calendar_day() {
        return "calendar/캘린더_일반_일";
    }

}
