package sw.chicha.Mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {

    /**
     * 마이페이지 공통
     * */

    @RequestMapping("계정관리")
    public String account_management(){ return "/mypage/계정관리"; }

    @RequestMapping("계정관리_상세")
    public String account_management_detail(){ return "/mypage/계정관리_상세"; }

    @RequestMapping("계정관리_수정")
    public String account_management_update(){ return "/mypage/계정관리_수정"; }

    @RequestMapping("계정관리_수정완료팝업")
    public String account_management_update_pop(){ return "/mypage/계정관리_수정완료팝업"; }

    @RequestMapping("고객센터")
    public String customer_service(){ return "/mypage/고객센터"; }

    @RequestMapping("고객센터_완료팝업")
    public String customer_service_pop(){ return "/mypage/고객센터_완료팝업"; }

    /**
     * 마이페이지 일반
     * */

    @RequestMapping("결제내역")
    public String payment_details(){ return "/mypage/member/결제내역"; }

    @RequestMapping("결제내역_결제완료")
    public String payment_details_complete(){ return "/mypage/member/결제내역_결제완료"; }

    @RequestMapping("마이센터")
    public String mycenter(){ return "/mypage/member/마이센터"; }

    @RequestMapping("마이센터_목록수정")
    public String mycenter_listupdate(){ return "/mypage/member/마이센터_목록수정"; }

    @RequestMapping("마이페이지")
    public String mypage(){ return "/mypage/member/마이페이지"; }

    @RequestMapping("아이등록")
    public String mypage_child_registration(){ return "/mypage/member/아이등록"; }

    @RequestMapping("아이등록관리")
    public String mypage_child_registration_management(){ return "/mypage/member/아이등록관리"; }

    @RequestMapping("아이등록관리_수정")
    public String mypage_child_registration_management_update(){ return "/mypage/member/아이등록관리_수정"; }

    @RequestMapping("지난내역")
    public String last_details(){ return "/mypage/member/지난내역"; }

    /**
     * 마이페이지 치료사
     * */

    @RequestMapping("마이페이지_치료사")
    public String mypage_therapist(){ return "/mypage/therapist/마이페이지_치료사"; }

    @RequestMapping("자격인증등록")
    public String certification(){ return "/mypage/therapist/자격인증등록"; }

    @RequestMapping("자격인증등록_거절")
    public String certification_refusal(){ return "/mypage/therapist/자격인증등록_거절"; }

    @RequestMapping("자격인증등록_승인완료")
    public String certification_approve(){ return "/mypage/therapist/자격인증등록_승인완료"; }

    @RequestMapping("자격인증등록_승인요청중")
    public String certification_approving(){ return "/mypage/therapist/자격인증등록_승인요청중"; }

    @RequestMapping("자격인증등록_요청조회")
    public String certification_request(){ return "/mypage/therapist/자격인증등록_요청조회"; }

    @RequestMapping("자격인증등록_추가등록")
    public String certification_add(){ return "/mypage/therapist/자격인증등록_추가등록"; }

    @RequestMapping("지난내역")
    public String last_datails_therapist(){ return "/mypage/therapist/지난내역"; }

    @RequestMapping("코칭내역")
    public String coaching(){ return "/mypage/therapist/코칭내역"; }

}

