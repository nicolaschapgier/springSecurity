package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
            try {
                authz
                        .requestMatchers("/avengers/assemble/**").hasRole("CHAMPION")
                        .requestMatchers("/secret-bases/**").hasRole("HASBU")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                        .and().formLogin()
                        .and().httpBasic();
            } catch (Exception e) {

                e.printStackTrace();
            }
        });
        return http.build();
    }

//    Cette déclaration permet de déclarer des utilisateurs "en mémoire" -- c'est-à-dire sans base de données.
//    Avec ce code, tu es en train d'associer l'identifiant user au mot de passe password. Ce n'est pas un mot
//    de passe très recommandé, mais ça nous ira bien pour tester. Tu peux relancer ton application,
//    et aller au formulaire de login... mais password ne marchera pas !
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User
                .withUsername("Steve")
                .password(encoder.encode("motdepasse"))
                .roles("CHAMPION")
                .build();

        UserDetails admin = User
                .withUsername("Hasbulla")
                .password(encoder.encode("12345678"))
                .roles("HASBU")
                .build();




        return new InMemoryUserDetailsManager((List.of(user,admin)));
    }
}

