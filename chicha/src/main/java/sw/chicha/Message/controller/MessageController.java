package sw.chicha.Message.controller;

import lombok.AllArgsConstructor;
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
public class MessageController {

    @GetMapping("message_storage")
    public String message_storage() {
        return "message/메시지_보관";
    }

    @GetMapping("message_detail")
    public String message_detail() {
        return "message/메시지_상세";
    }

    @GetMapping("message_communication")
    public String message_communication() {
        return "message/메시지_소통하기_팝업";
    }

    @GetMapping("message_noread")
    public String message_noread() {
        return "message/메시지_읽지않음";
    }

    @GetMapping("message_member")
    public String message_member() {
        return "message/메시지_일반";
    }

    @GetMapping("message_therapist")
    public String message_therapist() {
        return "message/메시지_치료사";
    }

}
