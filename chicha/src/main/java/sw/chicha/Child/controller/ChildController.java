package sw.chicha.Child.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Child.service.ChildService;
import sw.chicha.Member.repository.MemberRepository;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChildController {
    private ChildService childService;
    private ChildRepository childRepository;

    @PostMapping("/child_registration")
    public String exce_child_registration(@ModelAttribute ChildDto childDto, Principal principal) {
        String currentName = (String)principal.getName();

        childDto.setParent(currentName);
        childService.saveChild(childDto);
        return "redirect:/";
    }

    // member id
    @GetMapping("/child_registration")
    public String dis_child_registration() {
        return "/join/회원가입_아이등록";
    }


}
