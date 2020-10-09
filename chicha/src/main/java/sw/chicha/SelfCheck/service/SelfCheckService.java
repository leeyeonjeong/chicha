package sw.chicha.SelfCheck.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sw.chicha.SelfCheck.dto.SelfCheckDto;
import sw.chicha.SelfCheck.repository.SelfCheckRepository;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class SelfCheckService {
    private SelfCheckRepository selfCheckRepository;

    public Long saveCheck(SelfCheckDto selfCheckDto) {
        return selfCheckRepository.save(selfCheckDto.toEntity()).getId();
    }

}
