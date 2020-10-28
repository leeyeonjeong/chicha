package sw.chicha.Child.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import sw.chicha.Child.dto.ChildMemberDto;
import sw.chicha.Child.repository.ChildRepository;


@Service
@AllArgsConstructor
public class ChildService {
    private ChildRepository childRepository;

    public Long saveChildMember(ChildMemberDto childMemberDto) {

        return childRepository.save(childMemberDto.toEntity()).getId();
    }

}