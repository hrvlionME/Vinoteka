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

        authProvider.setPreAuthenticationChecks(userDetails -> {
            // Log relevant information before authentication
            System.out.println("Pre-authentication checks: Username - " + userDetails.getUsername() + ", UserDetails - " + userDetails.getPassword());
        });

        authProvider.setPostAuthenticationChecks(userDetails -> {
            // Log relevant information after authentication
            System.out.println("Pre-authentication checks: Username - " + userDetails.getUsername() + ", UserDetails - " + userDetails.getPassword());
        });



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
                .requestMatchers("/auth/register/**", "/auth/register", "/images/**")
                .permitAll()
                .requestMatchers("/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/store/**").hasAuthority("USER")
                .requestMatchers("/showcase/**").anonymous()
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