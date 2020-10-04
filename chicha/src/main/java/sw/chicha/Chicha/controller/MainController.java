package sw.chicha.Chicha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Main
    @GetMapping("/")
    public String main() {
        return "/main/main";
    }

    // Menu
    @GetMapping("/menu")
    public String menu() {
        return "/main/menu";
    }


}
