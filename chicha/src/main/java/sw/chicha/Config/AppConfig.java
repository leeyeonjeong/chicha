package sw.chicha.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sw.chicha.Child.dto.ChildDto;
import sw.chicha.Child.service.ChildService;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.service.MemberService;

@Configuration
public class AppConfig {

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            MemberService memberService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                MemberDto memberDto = MemberDto.builder()
                        .name("name")
                        .phoneNumber("010-1234-5678")
                        .email("member")
                        .password("member")
                        .zipcode("12345")
                        .firstAddr("adrr")
                        .secondAddr("")
                        .build();
                memberService.joinMember(memberDto);
            }
        };
    }
}
