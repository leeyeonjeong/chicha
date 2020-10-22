package sw.chicha.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sw.chicha.Calendar.service.CalendarService;
import sw.chicha.Member.dto.MemberDto;
import sw.chicha.Member.dto.TherapistDto;
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
                        .email("member1234")
                        .password("member1234")
                        .zipcode("12345")
                        .firstAddr("adrr")
                        .secondAddr("")
                        .build();

                TherapistDto therapistDto = TherapistDto.builder()
                        .name("therapist")
                        .email("therapist12")
                        .password("therapist12")
                        .phoneNumber("010-1111-1111")
                        .build();

                memberService.joinMember(memberDto);
                memberService.joinTherapist(therapistDto);
            }
        };
    }
}
