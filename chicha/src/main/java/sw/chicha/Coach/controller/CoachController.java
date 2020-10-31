package sw.chicha.Coach.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Coach.dto.CoachDto;
import sw.chicha.Coach.repository.CoachRepository;
import sw.chicha.Coach.service.CoachService;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class CoachController {
    private CoachService coachService;
    private CoachRepository coachRepository;
    private ChildRepository childRepository;
    private TherapistRepository therapistRepository;
    private MemberService memberService;
    private MemberRepository memberRepository;

    /**
     * 캘린더 메인
     *
     * */

    @GetMapping("calendar_month")
    public String calendar_month(Principal principal, CoachDto coachDto, Model model) {

         // 사용자가 치료사일 경우
       if (therapistRepository.findByEmail(principal.getName()).isPresent()) {
           // 처음 캘린더를 실행할 경우
           if (coachService.getTherapist(therapistRepository.findByEmail(principal.getName()).get().getId()).getId() == null) {
               coachDto.setTherapist(therapistRepository.findByEmail(principal.getName()).get());
               coachDto.setExpect(0L);
               coachDto.setAttendance(0L);
               coachDto.setAbsen(0L);
               coachDto.setReinforce(0L);
               coachDto.setEvaluation(0L);
               coachDto.setAccumulation(0L);
               coachService.saveCalender(coachDto);
           } else {
               coachDto = coachService.getCalendar(therapistRepository.findByEmail(principal.getName()).get().getId());
           }
           model.addAttribute("expect", coachDto.getExpect());
           model.addAttribute("attendance", coachDto.getAttendance());
           model.addAttribute("absen", coachDto.getAbsen());
           model.addAttribute("reinforce", coachDto.getReinforce());
           model.addAttribute("evaluation", coachDto.getEvaluation());
           model.addAttribute("accumulation", coachDto.getAccumulation());
           return "calendar/캘린더_치료사_월";
       // 사용자가 일반 멤버일 경우
       } else if (memberRepository.findByEmail(principal.getName()).isPresent()){
          if (coachService.getMember(memberRepository.findByEmail(principal.getName()).get().getId()).getId() == null) {
              coachDto.setMember(memberRepository.findByEmail(principal.getName()).get());
              coachDto.setExpect(0L);
              coachDto.setAttendance(0L);
              coachDto.setAbsen(0L);
              coachDto.setReinforce(0L);
              coachDto.setEvaluation(0L);
              coachDto.setAccumulation(0L);
              coachService.saveCalender(coachDto);
          } else {
              coachDto = coachService.getCalendar(memberRepository.findByEmail(principal.getName()).get().getId());
          }
           model.addAttribute("expect", coachDto.getExpect());
           model.addAttribute("attendance", coachDto.getAttendance());
           model.addAttribute("absen", coachDto.getAbsen());
           model.addAttribute("reinforce", coachDto.getReinforce());
           model.addAttribute("evaluation", coachDto.getEvaluation());
           model.addAttribute("accumulation", coachDto.getAccumulation());
           return "calendar/캘린더_일반_월";
        } else {
           return "main/메인";
       }
    }

    @GetMapping("therapist_calendar_day")
    public String therapist_calendar_day() {
        return "calendar/캘린더_치료사_일";
    }

    @GetMapping("member_calendar_day")
    public String member_calendar_day() {
        return "calendar/캘린더_일반_일";
    }

}
