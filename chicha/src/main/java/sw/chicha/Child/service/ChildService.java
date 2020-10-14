package sw.chicha.Child.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.repository.MemberRepository;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class ChildService {
    private ChildRepository childRepository;

    public Long saveChild(ChildDto childDto) {
        return childRepository.save(childDto.toEntity()).getId();
    }
}