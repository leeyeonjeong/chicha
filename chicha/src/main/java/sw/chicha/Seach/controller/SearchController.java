package sw.chicha.Seach.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class SearchController {

    @GetMapping("기관검색")
    public String search() {
        return "search/기관검색";
    }

    @GetMapping("기관검색_목록")
    public String search_list() {
        return "search/기관검색_목록";
    }

    @GetMapping("기관검색_상세")
    public String search_detail() {
        return "search/기관검색_상세";
    }

    @GetMapping("상세_iframe")
    public String search_iframe() {
        return "search/상세_iframe";
    }

}
