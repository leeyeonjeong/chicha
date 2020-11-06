package sw.chicha.Seach.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class SearchController {

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
