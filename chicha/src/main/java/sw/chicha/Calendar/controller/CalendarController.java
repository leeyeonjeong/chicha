package sw.chicha.Calendar.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Calendar.repository.CalendarRepository;
import sw.chicha.Calendar.service.CalendarService;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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
     * 캘린더
     *
     * */

    @GetMapping("therapist_calendar_month")
    public String therapist_calendar_month(Model model, Principal principal) {

         // 사용자가 치료사일 경우
       if (therapistRepository.findByEmail(principal.getName()).isPresent()) {
           Long id = therapistRepository.findByEmail(principal.getName()).get().getId();
           CalendarDto calendarDto = calendarService.getCalendar(id);
           //Optional<Therapist> therapist = therapistRepository.findById(calendarTherapistDto.getTherapist().getId());
           model.addAttribute("calendarList", calendarDto);

           if (calendarDto.getTherapist() != null) {
               model.addAttribute("expect", calendarDto.getTherapist().getAttend());
               model.addAttribute("attend", calendarDto.getTherapist().getAttend());
               model.addAttribute("absent", calendarDto.getTherapist().getAbsent());
               model.addAttribute("strengthen", calendarDto.getTherapist().getStrengthen());
               model.addAttribute("evaluation", calendarDto.getTherapist().getEvaluation());
           }

           //누적
           Long childCount = childRepository.countByName(calendarDto.getChild());
           //model.addAttribute("childCount", childCount);
           return "calendar/캘린더_치료사_월";

       } else if (memberRepository.findByEmail(principal.getName()).isPresent()){
          Long id = memberRepository.findByEmail(principal.getName()).get().getId();
          //CalendarMemberDto calendarMemberDto = calendarMemberService.getCalendar(id);
          //model.addAttribute("calendarList", calendarMemberDto);
//           if (calendarMemberDto.getMember() != null) {
//               if (calendarDto.getState() != null) {
//                   calendarDto.getTherapist().updateState(calendarDto.getState());
//               }
//               model.addAttribute("expect", calendarDto.getTherapist().getAttend().longValue());
//               model.addAttribute("attend", calendarDto.getTherapist().getAttend().longValue());
//               model.addAttribute("absent", calendarDto.getTherapist().getAbsent().longValue());
//               model.addAttribute("strengthen", calendarDto.getTherapist().getStrengthen().longValue());
//               model.addAttribute("evaluation", calendarDto.getTherapist().getEvaluation().longValue());
//           }

           //누적
           //Long childCount = calendarMemberRepository.countByChild(calendarMemberDto.getChild());
           //model.addAttribute("childCount", childCount);
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

    /**
     * 일반 일정등록
     * */

    @GetMapping("member_calendar_registration")
    public String dis_member_calendar_registration() {
        return "calendar/캘린더_일반_기록하기";
    }

//    @PostMapping("member_calendar_registration")
//    public String exec_member_calendar_registration(CalendarMemberDto calendarMemberDto, Principal principal) {
//        String currentName = (String)principal.getName();
//        calendarMemberDto.setMember(memberRepository.findByEmail(currentName).get());
//        calendarMemberDto.setChild(calendarMemberDto.getMember().getChild());
//        calendarMemberService.saveCalender(calendarMemberDto);
//        return "redirect:/";
//    }

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

    // 코칭등록2 -> 저장
    @PostMapping("therapist_calendar_registration")
    public String exec_therapist_calendar_registration(HttpSession session, TherapistDto therapistDto , CalendarDto calendarDto, Principal principal) {
        String currentName = (String)principal.getName();
        calendarDto.setTherapist(therapistRepository.findByEmail(currentName).get());
        calendarDto.setChild((String)session.getAttribute("childName"));
        calendarService.saveCalender(calendarDto);

        if (calendarDto.getState() != null) {
            if (calendarDto.getState() == "예정") {
                therapistDto.setExpect(therapistDto.getExpect()+1);
            } else if (calendarDto.getState() == "출석") {
                therapistDto.setAttend(therapistDto.getAttend()+1);
            } else if (calendarDto.getState() == "결석") {
                therapistDto.setAbsent(therapistDto.getAbsent()+1);
            } else if (calendarDto.getState() == "보강") {
                therapistDto.setStrengthen(therapistDto.getStrengthen()+1);
            } else if (calendarDto.getState() == "평가") {
                therapistDto.setEvaluation(therapistDto.getEvaluation()+1);
            }
        }

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

    /**
     * 치료회기
     * */

    @GetMapping("therapist_calendar_session")
    public String therapist_calendar_session(Principal principal, Model model) {
        String currentName = (String)principal.getName();
        Long id = therapistRepository.findByEmail(currentName).get().getId();
        CalendarDto calendarDto = calendarService.getCalendar(id);
        model.addAttribute("calendar", calendarDto);
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
    public String exec_therapist_calendar_session_registration(CalendarDto calendarDto) {
        calendarService.saveCalender(calendarDto);
        return "redirect:/therapist_calendar_sessionlist";
    }

    /**
     * 아동 리스트
     * */

    @GetMapping("therapist_calendar_childlist")
    public String therapist_calendar_childlist(Model model) {
        List<ChildTherapistDto> childList = calendarService.getChildTherapistList();

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

    /**
     * 검색 (아동)
     * */

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
