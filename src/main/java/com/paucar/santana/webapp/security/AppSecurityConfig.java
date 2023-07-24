package com.paucar.santana.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.paucar.santana.webapp.security.Roles.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;

        @Autowired
        public AppSecurityConfig(PasswordEncoder passwordEncoder) {
                this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                                .authorizeRequests()
                                .antMatchers(HttpMethod.DELETE, "/api/docentes/{TeacherId}").hasRole(DECANO.name())
                                .antMatchers(HttpMethod.PUT, "/api/docentes/{TeacherId}").hasRole(DECANO.name())
                                .antMatchers("/api/docentes/add").hasAnyRole(DECANO.name())
                                .antMatchers("/api/docentes")
                                .hasAnyRole(DECANO.name(), SECRETARÍA.name(), DOCENTE.name())
                                .antMatchers("/api/docentes/{TeacherId}")
                                .hasAnyRole(DECANO.name(), SECRETARÍA.name(), DOCENTE.name())
                                .anyRequest().authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/dashboard", true)
                                .and()
                                .logout()
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                                .and()
                                .httpBasic();
        }

        @Bean
        @Override
        protected UserDetailsService userDetailsService() {
                UserDetails Jaime = User.builder()
                                .username("Jaime")
                                .password(passwordEncoder.encode("123456"))
                                .roles(DOCENTE.name())
                                .build();

                UserDetails Rebeca = User.builder()
                                .username("Rebeca")
                                .password(passwordEncoder.encode("123456"))
                                .roles(SECRETARÍA.name())
                                .build();

                UserDetails Hector = User.builder()
                                .username("Hector")
                                .password(passwordEncoder.encode("password"))
                                .roles(DECANO.name())
                                .build();

                return new InMemoryUserDetailsManager(Jaime, Rebeca, Hector);
        }
}
