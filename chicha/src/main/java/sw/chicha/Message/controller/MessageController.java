package sw.chicha.Message.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;
import sw.chicha.Message.domain.Room;
import sw.chicha.Message.dto.RoomDto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class MessageController {

    List<RoomDto> roomList = new ArrayList<RoomDto>();
    static Long rooomNumber = 0L;

    @GetMapping("chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        // 어떤 페이지를 보여줄 것인지
        mv.setViewName("message/chat");

        return mv;
    }

    @GetMapping("room")
    public ModelAndView room() {
        ModelAndView mv = new ModelAndView();
        // 어떤 페이지를 보여줄 것인지
        mv.setViewName("message/room");
        return mv;
    }

    @RequestMapping("createRoom")
    public @ResponseBody List<RoomDto> createRoom(@RequestParam HashMap<Object, Object> params) {
        String roomName = (String) params.get("name");
        if(roomName != null && !roomName.trim().equals("")) {
            RoomDto room = new RoomDto();
            room.setId(++rooomNumber);
            room.setName(roomName);
            roomList.add(room);
        }
        return roomList;
    }

    @RequestMapping("getRoom")
    public @ResponseBody List<RoomDto> getRoom(@RequestParam HashMap<Object, Object> params) {
        return roomList;
    }

    @RequestMapping("moveChating")
    public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
        ModelAndView mv = new ModelAndView();
        Long roomNumber = Long.parseLong((String) params.get("id"));

        List<RoomDto> new_list = roomList.stream().filter(o->o.getId()==roomNumber).collect(Collectors.toList());
        if (new_list != null && new_list.size() > 0) {
            mv.addObject("name", params.get("name"));
            mv.addObject("id", params.get("id"));
            mv.setViewName("message/chat");
        } else {
            mv.setViewName("message/room");
        }
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
