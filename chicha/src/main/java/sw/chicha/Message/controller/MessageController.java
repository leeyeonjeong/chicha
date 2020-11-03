package sw.chicha.Message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @RequestMapping("메시지_보관")
    public String message_storage() { return "/message/메시지_보관"; }

    @RequestMapping("메시지_상세")
    public String message_detail() { return "/message/메시지_상세"; }

    @RequestMapping("메시지_소통하기_팝업")
    public String message_communication() { return "/message/메시지_소통하기_팝업"; }

    @RequestMapping("메시지_일반")
    public String message_member() { return "/message/메시지_일반"; }

    @RequestMapping("메시지_읽지않음")
    public String message_noread() { return "/message/메시지_읽지않음"; }

    @RequestMapping("메시지_치료사")
    public String message_therapist() { return "/message/메시지_치료사"; }
}
