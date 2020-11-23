package sw.chicha.Mypage.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class MypageController {

    TherapistRepository therapistRepository;
    MemberService memberService;
    MemberRepository memberRepository;

    /**
     * 마이페이지 공통
     * */

    @GetMapping("account_management")
    public String dis_account_management() {
        return "mypage/계정관리";
    }

    @PostMapping("account_management")
    public String exec_account_management(Principal principal, String pwd) {
        String re = "redirect:/mypage";
        // 사용자가 치료사일 경우
        if (therapistRepository.findByEmail(principal.getName()).isPresent()) {
            TherapistDto therapistDto = memberService.getTherapist(therapistRepository.findByEmail(principal.getName()).get().getId());
            if (therapistDto.getPassword() == pwd) {
                re = "redirect:/account_management_detail";
            }
        } else if (memberRepository.findByEmail(principal.getName()).isPresent()) {  // 일반 사용자일 경우
            MemberDto memberDto = memberService.getMember(memberRepository.findByEmail(principal.getName()).get().getId());
            if (memberDto.getPassword() == pwd) {
                re= "redirect:/mypage";
            }
        }
        return re;
    }

    // 계정관리 상세
    @GetMapping("account_management_detail")
    public String account_management_detail(Principal principal, Model model) {
        // 사용자가 치료사일 경우
        if (therapistRepository.findByEmail(principal.getName()).isPresent()) {
            TherapistDto therapistDto = memberService.getTherapist(therapistRepository.findByEmail(principal.getName()).get().getId());
            model.addAttribute("therapistDto", therapistDto);
            return "mypage/계정관리_상세_치료사";
        } else if (memberRepository.findByEmail(principal.getName()).isPresent()) {  // 일반 사용자일 경우
            MemberDto memberDto = memberService.getMember(memberRepository.findByEmail(principal.getName()).get().getId());
            model.addAttribute("memberDto", memberDto);
            return "mypage/계정관리_상세_일반";
        }
        return "main/메인";
    }

    @GetMapping("account_management_update_therapist/{id}")
    public String dis_account_management_update_therapist(@PathVariable("id") Long id, Model model) {
        // 사용자가 치료사일 경우
        TherapistDto therapistDto = memberService.getTherapist(id);
        model.addAttribute("therapistDto", therapistDto);
        return "mypage/계정관리_수정_치료사";
    }

    @PutMapping("account_management_update_therapist/{id}")
    public String exec_account_management_update(TherapistDto therapistDto) {
        memberService.saveTherapist(therapistDto);
        return "redirect:/";
    }

    @GetMapping("account_management_update_member/{id}")
    public String dis_account_management_update_member(@PathVariable("id") Long id, Model model) {
        MemberDto memberDto = memberService.getMember(id);
        model.addAttribute("memberDto", memberDto);
        return "mypage/계정관리_수정_일반";
    }

    @PutMapping("account_management_update_member/{id}")
    public String the_account_management_update_member(MemberDto memberDto) {
        memberService.saveMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("mypage")
    public String mypage(Principal principal, Model model) {
        String memberName = memberRepository.findByEmail(principal.getName()).get().getName();
        model.addAttribute("memberName", memberName);
        return "mypage/마이페이지";
    }



}
