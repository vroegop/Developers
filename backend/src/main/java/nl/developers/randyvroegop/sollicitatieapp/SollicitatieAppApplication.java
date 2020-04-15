package nl.developers.randyvroegop.sollicitatieapp;

import lombok.extern.slf4j.Slf4j;
import nl.developers.randyvroegop.sollicitatieapp.resume.ResumeMongoRepository;
import nl.developers.randyvroegop.sollicitatieapp.resume.model.Experience;
import nl.developers.randyvroegop.sollicitatieapp.resume.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Slf4j
@SpringBootApplication
@EnableWebFluxSecurity
public class SollicitatieAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SollicitatieAppApplication.class, args);
    }

    @Autowired
    private ResumeMongoRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll().subscribe();

        Experience thePatientSafetyCompany = Experience.builder()
                .companyName("ThePatientSafetyCompany")
                .yearsExperience(2)
                .build();

        Experience quintor = Experience.builder()
                .companyName("Quintor")
                .yearsExperience(2)
                .build();

        Resume randy_vroegop = Resume.builder()
                .id("123")
                .name("Randy Vroegop")
                .phonenumber("+31633831814")
                .experience(List.of(thePatientSafetyCompany, quintor)).build();

        repository.save(randy_vroegop).subscribe();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user, user2);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/resume/**").hasRole("USER")
                .pathMatchers(HttpMethod.POST, "/resume/find").hasRole("USER")
                .pathMatchers(HttpMethod.POST, "/resume").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .httpBasic().and()
                // Disable to allow Postman requests
                .csrf().disable()
                .formLogin();
        return http.build();
    }
}
