package sw.chicha.SelfCheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sw.chicha.Member.dto.MemberDto;

import javax.servlet.http.HttpSession;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

@Controller
public class SelfCheckController {

    /**
     * *********** 뷰 ***********
     * */

    // 자가진단
    @GetMapping("/self_check")
    public String self_check() {
        return "/self_check/자가진단";
    }

    // 정보입력
    @GetMapping("/self_check_info")
    public String self_check_info() {
        return "/self_check/자가진단_정보입력";
    }

    // ADHD1
    @GetMapping("/self_check_ADHD1")
    public String self_check_ADHD1() {
        return "/self_check/자가진단_ADHD1";
    }

    // ADHD2
    @GetMapping("/self_check_ADHD2")
    public String self_check_ADHD2() {
        return "/self_check/자가진단_ADHD2";
    }

    // ADHD3
    @GetMapping("/self_check_ADHD3")
    public String self_check_ADHD3() {
        return "/self_check/자가진단_ADHD3";
    }

    // ADHD4
    @GetMapping("/self_check_ADHD4")
    public String self_check_ADHD4() {
        return "/self_check/자가진단_ADHD4";
    }

    // ADHD 결과
    @GetMapping("/self_check_result_ADHD")
    public String self_check_result_ADHD() {
        return "/self_check/자가진단_결과";
    }

    // 감각발달 결과
    @GetMapping("/self_check_result_sensory_development")
    public String self_check_result_sensory_development() {
        return "/self_check/자가진단_결과(감각발달)";
    }

    // 심리정서 결과
    @GetMapping("self_check_result_psychological_sentiment")
    public String self_check_result_psychological_sentiment() {
        return "/self_check/자가진단_결과(심리정서)";
    }

    // 언어발달 결과
    @GetMapping("self_check_result_language_development")
    public String self_check_result_language_development() {
        return "/self_check/자가진단_결과(언어발달)";
    }

    // 정상 결과
    @GetMapping("self_check_result_nomal")
    public String self_check_result_nomal() {
        return "/self_check/자가진단_결과(정상)";
    }

    /**
     * *********** 세션 저장 ***********
     * */

    // 진단 타입
    @GetMapping("/saveType")
    public String saveType(@RequestParam(name="type", required = false) String type,
                           HttpSession session) {
        session.setAttribute("type", type);

        return "/self_check/자가진단_정보입력";
    }

    // 정보입력
    @GetMapping("/saveInfo")
    public String saveInfo(@RequestParam(name="gender", required = false) String gender,
                           @RequestParam(name = "age", required = false) String age,
                           HttpSession session) {
        session.setAttribute("gender", gender);
        session.setAttribute("age", age);

        String type = (String)session.getAttribute("type");
        String re = "/self_check/";

        // ADHD만 구현되어있음
        if (type.equals(NULL)){
            re += "자가진단_정보입력";
        } else if (type.equals("language_development")) {
            re += "자가진단_결과(언어발달)";
        } else if (type.equals("ADHD")) {
            re += "자가진단_ADHD1";
        } else if (type.equals("psychological_sentiment")) {
            re += "자가진단_결과(심리정서)";
        } else if (type.equals("sensory_development")){
            re += "자가진단_결과(감각발달)";
        }

        return re;
    }

    /**
     * *********** DB 저장 ***********
     * */


}