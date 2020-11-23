package sw.chicha.Config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sw.chicha.Member.service.MemberService;

import static org.hibernate.criterion.Restrictions.and;
import static org.springframework.security.config.oauth2.client.CommonOAuth2Provider.FACEBOOK;
import static org.springframework.security.config.oauth2.client.CommonOAuth2Provider.GOOGLE;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)  // 컨트롤러에 직접 권한 지정 가능
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 하위 목록 인증 패스
        web.ignoring().antMatchers("/css/**", "/js/**", "/image/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한
                .antMatchers("/","/join_select", "/join_general", "/join_therapist", "/join_success_therapist", "/join_success_general", "/check/sendSMS")
                .permitAll()                .anyRequest()
                .authenticated()
        .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                //.logoutRequestMatcher(new AndRequestMatcher("/logout"))
                //.logoutRequestMatcher("/logout/result")
                .invalidateHttpSession(true);
        //.and()
                //.oauth2Login();
                //.userInfoEndpoint()
        //.and()
                // 403 예외처리
                //.exceptionHandling().accessDeniedPage("/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
