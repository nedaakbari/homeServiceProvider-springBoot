package ir.maktab.homeserviceprovider.configuration;

import ir.maktab.data.repository.AdminRepository;
import ir.maktab.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final AdminRepository managerRepository;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebConfigSecurity(AuthenticationSuccessHandler authenticationSuccessHandler
            , UserRepository userRepository, AdminRepository managerRepository) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return new SystemUserDetailsService(userRepository, passwordEncoder(), managerRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/register", "/login","/admin", "/error", "/user_confirmation/**"
                        , "/swagger-ui.html/**").permitAll()
                .antMatchers("/css/**", "/images/**", "/js/**","/tags/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and().csrf().disable();
    }
}
