package ba.sum.fsre.vinoteka.config;

import ba.sum.fsre.vinoteka.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/auth/register/**", "/auth/register", "/images/**", "/uploads/**")
                .permitAll()
                .requestMatchers("/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/store/**").hasAuthority("USER")
                .requestMatchers("/showcase/**").anonymous()
                .requestMatchers("/upload/**").permitAll() // Allow unauthenticated access to the file upload endpoint
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().successHandler(myAuthenticationSuccessHandler())
                .loginPage("/auth/login")
                .permitAll()
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();

        http.authenticationProvider(authenticationProvider());
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}