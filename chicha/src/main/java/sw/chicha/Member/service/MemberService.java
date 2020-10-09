package sw.chicha.Member.service;

import lombok.AllArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.logging.log4j.message.Message;
import org.json.simple.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Role;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;

    public Long joinUser(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(email);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        // 권한생성
        if (("admin").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        // Detail에서 새로운 객체 반환
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    // 아이디 중복 체크 함수
    public Boolean emailCheck(String email) {
        System.out.println("EEEEEEEEEEEEMMMMMMMMMMMMMMMAAAAAAAAAAAIL"+email);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44"+memberRepository.findByEmail(email));
        if (memberRepository.findByEmail(email) == null) {
            return true;
        } else {
            return false;
        }
    }

    public void certificationService(String phoneNumber, String cerNum) {
        String api_key = "내 API KEY";
        String api_secret = "내 API SECRET";
        //Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber); // 수신 전화번호
        params.put("from", "발송할 번호 입력");
        params.put("type", "SMS");
        params.put("text", "인증번호: "+ "["+cerNum+"]"+"입니다.");
        params.put("app_version", "text");

        //try {
        //    JSONObject obj = (JSONObject) coolsms.send(params);
        //    System.out.println(obj.toString());
        //} catch (CoolsmsException e) {
        //    System.out.println(e.getMessage());
        //    System.out.println(e.getCode());
        //}
    }
}
