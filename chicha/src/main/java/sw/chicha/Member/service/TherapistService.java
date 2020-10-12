package sw.chicha.Member.service;

import lombok.AllArgsConstructor;
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
import sw.chicha.Member.domain.Therapist;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.dto.TherapistDto;
import sw.chicha.Member.repository.MemberRepository;
import sw.chicha.Member.repository.TherapistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TherapistService implements UserDetailsService {

    private TherapistRepository therapistRepository;

    public Long joinUser(TherapistDto therapistDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        therapistDto.setPassword(passwordEncoder.encode(therapistDto.getPassword()));

        return therapistRepository.save(therapistDto.toEntity()).getId();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Therapist> userEntityWrapper = therapistRepository.findByEmail(email);
        Therapist userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        // 권한생성
        if (("admin").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.THERAPIST.getValue()));
        }

        // Detail에서 새로운 객체 반환
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

}
