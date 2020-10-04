package sw.chicha.Member.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.service.MemberService;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

     // 회원가입 페이지
    @GetMapping("join_general")
    public String dis_join_general() {
        return "/join/join_general";
    }

    // 회원가입 처리
    @PostMapping("join_general")
    public String exec_join_general(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/login";
    }

    // 아이디 중복 확인
    @ResponseBody
    @RequestMapping(value = "emailCheck", method = RequestMethod.POST)
    public String emailCheck(@RequestBody String email) {

        System.out.println("######################33"+memberService.emailCheck(email));
        if (memberService.emailCheck(email) == true) {
            return "1";
        } else {
            return "0";
        }
    }

    // 로그인 페이지
    @GetMapping("login")
    public String login() {
        return "/login/login";
    }


    @GetMapping("join_select")
    public String join_select() {
        return "/join/join_select";
    }

    @GetMapping("join_therapist")
    public String join_therapist() {
        return "/join/join_therapist";
    }
}
