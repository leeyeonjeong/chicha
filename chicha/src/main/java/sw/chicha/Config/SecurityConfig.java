package sw.chicha.Config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sw.chicha.OAuth.service.CustomOAuth2UserService;
import sw.chicha.Member.service.MemberService;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;
    private final CustomOAuth2UserService customOAuth2UserService;

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
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("MEMBER")
                .antMatchers("/**").permitAll()
        .and()
                .formLogin()
                .loginPage("/login")
                //.defaultSuccessUrl("/")
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
        //        .oauth2Login()
        //        .userInfoEndpoint()
        //        .userService(customOAuth2UserService);
        //.and()
                // 403 예외처리
                //.exceptionHandling().accessDeniedPage("/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}