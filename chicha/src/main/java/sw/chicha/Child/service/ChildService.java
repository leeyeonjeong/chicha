package sw.chicha.Child.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.dto.ChildMemberDto;
import sw.chicha.Child.dto.ChildTherapistDto;
import sw.chicha.Child.repository.ChildRepository;
import sw.chicha.Member.repository.MemberRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChildService {
    private ChildRepository childRepository;

    public Long saveChildMember(ChildMemberDto childMemberDto) {

        return childRepository.save(childMemberDto.toEntity()).getId();
    }

}