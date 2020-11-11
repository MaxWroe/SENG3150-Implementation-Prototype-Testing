package group3.seng3150;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final EntityManager em;
    @Autowired
    public WebSecurityConfig(EntityManager em){this.em =em;}

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServices(em);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests()
                .antMatchers("/", "/errorPage", "/faqs", "/login", "/search", "/register", "/home", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //.passwordEncoder(new BCryptPasswordEncoder();
    }
 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").
                loginProcessingUrl("/appLogin").
                defaultSuccessUrl("/home");

        http.authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**", "/errorPage", "/faqs", "/reviews", "/login", "/search", "/register", "/home", "/accessDenied", "/", "RecTesting").permitAll()
                .antMatchers("/accountDetails", "/customerSupport", "/logout", "/manageBooking", "/submitReview", "/wishList", "/reviews", "/wishList", "/wishList", "/travelRecommendations", "/bookingPage").access("hasAnyRole('CUSTOMER','AGENT', 'ADMIN', 'FLIGHTPUB')")
                .antMatchers("/manageAirline", "/manageAirport").access("hasAnyRole('FLIGHTPUB', 'ADMIN')")
                .antMatchers("/travelAgentPage").access("hasAnyRole('AGENT', 'ADMIN')")
                .antMatchers("/addUsers", "/manageUsers").access("hasRole('ADMIN')")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                //.anyRequest().hasAnyRole("CUSTOMER", "AGENT")
                .and()
                .formLogin()
                .and()
                .logout().permitAll().logoutSuccessUrl("/home");
        http.csrf().disable();

    }
}