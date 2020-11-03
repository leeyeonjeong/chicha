package sw.chicha.Untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UntactController {

    /**
     * 일반
     */

    @RequestMapping("언택트코칭_일반")
    public String untact_member() { return "/untact/언택트코칭_일반"; }

    @RequestMapping("언택트코칭_일반_내가쓴글")
    public String untact_member_write() { return "/untact/언택트코칭_일반_내가쓴글"; }

    @RequestMapping("언택트코칭_일반_약관동의")
    public String untact_member_consent() { return "/untact/언택트코칭_일반_약관동의"; }

    @RequestMapping("언택트코칭_일반_코칭등록")
    public String untact_member_coach() { return "/untact/언택트코칭_일반_코칭등록"; }

    @RequestMapping("언택트코칭_일반_코칭등록_완료팝업")
    public String untact_member_coach_complete() { return "/untact/언택트코칭_일반_코칭등록_완료팝업"; }

    @RequestMapping("언택트코칭_일반_코칭상세")
    public String untact_member_coach_detail() { return "/untact/언택트코칭_일반_코칭상세"; }

    @RequestMapping("언택트코칭_일반_코칭상세_비공개")
    public String untact_member_coachi_detail_non() { return "/untact/언택트코칭_일반_코칭상세_비공개"; }

    @RequestMapping("언택트코칭_일반_코칭상세_완료")
    public String untact_member_coachi_detail_complete() { return "/untact/언택트코칭_일반_코칭상세_완료"; }

    /**
     * 치료사
     */

    @RequestMapping("언택트코칭_치료사")
    public String untact_therapist() { return "/untact/언택트코칭_치료사"; }

    @RequestMapping("언택트코칭_치료사_내코칭보기")
    public String untact_therapist_mycoach() { return "/untact/언택트코칭_치료사_내코칭보기"; }

    @RequestMapping("언택트코칭_치료사_약관동의")
    public String untact_therapist_consent() { return "/untact/언택트코칭_치료사_약관동의"; }

    @RequestMapping("언택트코칭_치료사_코칭상세")
    public String untact_therapist_coach_detail() { return "/untact/언택트코칭_치료사_코칭상세"; }

    @RequestMapping("언택트코칭_치료사_코칭상세_완료")
    public String untact_therapist_coachi_detail_complete() { return "/untact/언택트코칭_치료사_코칭상세_완료"; }

}
