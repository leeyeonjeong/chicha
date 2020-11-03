package sw.chicha.Child.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.dto.ChildMemberDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Child.service.ChildService;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.service.MemberService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class ChildController {
    private ChildService childService;
    private MemberRepository memberRepository;
    private MemberService memberService;

    @GetMapping("/child_registration")
    public String dis_child_registration() {
        return "/join/회원가입_아이등록";
    }

    // 보호자 -> 아이등록
    @PostMapping("/child_registration")
    public String exce_child_registration(@ModelAttribute ChildMemberDto childmemberDto, Principal principal) {
        String currentName = (String)principal.getName();
        childmemberDto.setMember(memberRepository.findByEmail(currentName).get());
        childService.saveChildMember(childmemberDto);
        return "redirect:/";
    }

}
