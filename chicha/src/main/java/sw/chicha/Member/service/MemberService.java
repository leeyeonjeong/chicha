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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import sw.chicha.Calendar.dto.CalendarDto;
import sw.chicha.Member.domain.Member;
import sw.chicha.Member.domain.Role;
import sw.chicha.Member.domain.Therapist;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;
import sw.chicha.Schedule.domain.Schedule;
import sw.chicha.Schedule.dto.ScheduleDto;

import java.util.*;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;
    private TherapistRepository therapistRepository;

    // 일반 저장
    public Long saveMember(MemberDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    // 치료사 저장
    public Long saveTherapist(TherapistDto therapistDto) {
        return therapistRepository.save(therapistDto.toEntity()).getId();
    }


    // 일반 회원가입
    public Long joinMember(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    // 치료사 회원가입
    public Long joinTherapist(TherapistDto therapistDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        therapistDto.setPassword(passwordEncoder.encode(therapistDto.getPassword()));

        return therapistRepository.save(therapistDto.toEntity()).getId();
    }

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(email);

        // 치료사 로그인일 때
        if (!userEntityWrapper.isPresent()) {
            Optional<Therapist> userEntityWrapper2 = therapistRepository.findByEmail(email);
            Therapist userEntity = userEntityWrapper2.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            // 권한생성
            if (("admin").equals(email)) {
                authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
            } else {
                authorities.add(new SimpleGrantedAuthority(Role.THERAPIST.getValue()));
            }

            // Detail에서 새로운 객체 반환
            return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        } else {
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
    }

    // 일반 멤버 반환
    public MemberDto getMember(Long id) {
        Optional<Member> memberWrapper = memberRepository.findById(id);
        if (memberWrapper.isPresent()) {
            Member member = memberWrapper.get();

            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .zipcode(member.getZipcode())
                    .firstAddr(member.getFirstAddr())
                    .secondAddr(member.getSecondAddr())
                    .child(member.getChild())
                    .build();

            return memberDto;
        } else {
            return MemberDto.builder().build();
        }
    }

    // 치료사 반환
    public TherapistDto getTherapist(Long id) {
        Optional<Therapist> therapistWrapper = therapistRepository.findById(id);
        if (therapistWrapper.isPresent()) {
            Therapist therapist = therapistWrapper.get();

            TherapistDto therapistDto = TherapistDto.builder()
                    .id(therapist.getId())
                    .name(therapist.getName())
                    .email(therapist.getEmail())
                    .password(therapist.getPassword())
                    .phoneNumber(therapist.getPhoneNumber())
                    .build();

            return therapistDto;
        } else {
            return TherapistDto.builder().build();
        }
    }

    // 유효성검사 핸들러
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
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
