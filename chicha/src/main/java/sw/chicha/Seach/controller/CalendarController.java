package sw.chicha.Seach.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Member.service.MemberService;
import sw.chicha.Seach.dto.CalendarDto;
import sw.chicha.Seach.repository.CalendarRepository;
import sw.chicha.Seach.service.CalendarService;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class CalendarController {

    @GetMapping("search")
    public String search() {
        return "search/기관검색";
    }

    @GetMapping("search_list")
    public String search_list() {
        return "search/기관검색_목록";
    }

    @GetMapping("search_detail")
    public String search_detail() {
        return "search/기관검색_상세";
    }

}
