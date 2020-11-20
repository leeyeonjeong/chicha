package sw.chicha.Message.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sw.chicha.Message.domain.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class MessageController {

    List<Room> roomList = new ArrayList<Room>();
    static Integer rooomNumber = 0;

    @GetMapping("chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        // 어떤 페이지를 보여줄 것인지
        mv.setViewName("message/chat");
        return mv;
    }

    /**
     * 뷰
     * */

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
