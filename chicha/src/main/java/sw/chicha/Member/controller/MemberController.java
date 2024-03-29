package sw.chicha.Member.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.CertificationService;
import sw.chicha.Member.service.MemberService;

import java.security.Principal;
import java.util.Map;
import java.util.Random;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private CertificationService certificationService;

    /**
     * 공통
     * */

    // 회원가입 선택
    @GetMapping("join_select")
    public String join_select() {
        return "join/회원가입선택";
    }

    // SMS 인증
    @GetMapping("/check/sendSMS")
    public @ResponseBody
    String sendSMS(String phoneNumber) {

        Random rand  = new Random();
        String numStr = "";

        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        certificationService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }

    // 로그인 페이지
    @GetMapping("login")
    public String login() {
        return "login/로그인";
    }

    // 로그아웃 페이지
    @GetMapping("logout")
    public String logout() {
        return "redirect:/";
    }


    /**
     * 일반 회원
     * */

     // 회원가입 페이지 (일반)
    @GetMapping("join_general")
    public String dis_join_general() {
        return "join/회원가입_일반";
    }

    // 회원가입 처리 (일반)
    @PostMapping("join_general")
    public String exec_join_general(MemberDto memberDto) {
        memberService.joinMember(memberDto);
        return "redirect:/join_success_general";
    }

    // 회원가입 완료 (일반)
    @GetMapping("join_success_general")
    public String join_success_general() {
        return "join/회원가입완료_일반";
    }

    // 아이등록
    @GetMapping("join_child")
    public String join_child() {
        return "join/회원가입_아이등록";
    }


    /**
     * 치료사 회원
     * */


    // 회원가입 페이지 (치료사)
    @GetMapping("join_therapist")
    public String dis_join_therapist() {
        return "join/회원가입_치료사";
    }

    // 회원가입 처리 (치료사)
    @PostMapping("join_therapist")
    public String exec_join_therapist(TherapistDto therapistDto) {
        memberService.joinTherapist(therapistDto);
        return "redirect:/join_success_therapist";
    }

    // 회원가입 완료 (치료사)
    @GetMapping("join_success_therapist")
    public String join_success_therapist() {
        return "join/회원가입완료_치료사";
    }

}
