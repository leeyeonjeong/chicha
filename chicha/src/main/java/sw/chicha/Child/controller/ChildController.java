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
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Child.service.ChildService;
import sw.chicha.Member.repository.MemberRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class ChildController {
    private ChildService childService;
    private MemberRepository memberRepository;

    @GetMapping("/child_registration")
    public String dis_child_registration() {
        return "/join/회원가입_아이등록";
    }

    @PostMapping("/child_registration")
    public String exce_child_registration(@ModelAttribute ChildDto childDto, Principal principal) {
        String currentName = (String)principal.getName();
        childDto.setMember(memberRepository.findByEmail(currentName).get());

        childService.saveChild(childDto);
        return "redirect:/";
    }

    @GetMapping("/therapist_calendar_search_dis")
    public String dis_therapist_calendar_search(Model model) {
        List<ChildDto> childList = childService.getChildList();

        model.addAttribute("childList", childList);
        return "calendar/캘린더_치료사_팝업_아동검색";
    }

    @GetMapping("/therapist_calendar_search_exce")
    public String exce_therapist_calendar_search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<ChildDto> childDtoList = childService.searchPosts(keyword);
        model.addAttribute("childList", childDtoList);

        return "calendar/캘린더_치료사_팝업_아동검색";
    }

}
