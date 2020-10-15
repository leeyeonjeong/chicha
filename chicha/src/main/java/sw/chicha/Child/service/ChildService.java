package sw.chicha.Child.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.Child.domain.Child;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.repository.ChildRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChildService {
    private ChildRepository childRepository;

    public Long saveChild(ChildDto childDto) {
        return childRepository.save(childDto.toEntity()).getId();
    }

    public List<Child> getChildList() {

        return childRepository.findAll();

    }

    public List<ChildDto> searchPosts(String keyword) {
        List<Child> childs = childRepository.findByNameContaining(keyword);
        List<ChildDto> childDtoList = new ArrayList<>();

        if(childs.isEmpty()) return childDtoList;

        for(Child child : childs) {
            childDtoList.add(this.convertEntityToDto(child));
        }

        return childDtoList;
    }

    private ChildDto convertEntityToDto(Child child) {
        return ChildDto.builder()
                .id(child.getId())
                .name(child.getName())
                .gender(child.getGender())
                .field(child.getField())
                .birthday(child.getBirthday())
                .affiliation(child.getAffiliation())
                .picture(child.getPicture())
                .build();
    }

}