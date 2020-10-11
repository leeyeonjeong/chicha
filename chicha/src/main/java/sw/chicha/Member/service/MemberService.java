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
    public String idCheck(String email) {
        System.out.println("serviceeeeeeeeeeeeeeeeeeeeeeeee"+memberRepository.findByEmail(email));
        String re = "YES";

        if (memberRepository.findByEmail(email) == null) {
            re = "YES";
        } else {
            re = "NO";
        }
        return re;
    }

}
