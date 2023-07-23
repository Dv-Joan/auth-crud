package com.paucar.santana.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

//Modulos de la dependencia Spring Security para la Autenticacion y Autorizacion de 
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

// Activacion de la configuracion de Spring Security
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

        // Modulo PasswordEncoder para la encriptacion de la contraseña
        @Autowired
        private final PasswordEncoder passwordEncoder;

        public AppSecurityConfig(PasswordEncoder passwordEncoder) {
                this.passwordEncoder = passwordEncoder;
        }

        /*
         * Configuracion de la Autorizacion de las peticiones HTTP de la API REST de
         * Docentes con sus respectivos roles y operaciones permitidas para cada uno
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {

                /*
                 * Manejo de inicio de sesiones para los usuarios, esto hace que al actualizar
                 * la pagina el usuario siga autenticado
                 */
                http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                /*
                 * Configuracion de las perticiones Http permitidas para cada uno de los roles
                 * definidos previamente
                 */
                http
                                .csrf().disable()
                                .authorizeHttpRequests()
                                /*
                                 * Los antMatchers son las rutas de la API REST de Docentes con sus respectivos
                                 * roles y operaciones permitidas para cada uno
                                 */
                                .antMatchers(HttpMethod.DELETE, "/api/docentes/{TeacherId}").hasRole(DECANO.name())
                                .antMatchers(HttpMethod.PUT, "/api/docentes/{TeacherId}").hasRole(DECANO.name())
                                .antMatchers("/api/docentes/add")

                                // Operaciones permitidas para los roles de Secretaría y Docente
                                .hasAnyRole(DECANO.name(), SECRETARÍA.name())
                                .antMatchers("/api/docentes")
                                .hasAnyRole(DECANO.name(), SECRETARÍA.name(), DOCENTE.name())
                                .antMatchers("/api/docentes{TeacherId}")
                                .hasAnyRole(DECANO.name(), SECRETARÍA.name(), DOCENTE.name())
                                .anyRequest()

                                // Verificacion de la Autenticacion
                                .authenticated()
                                .and()
                                .httpBasic();
        }

        @Bean
        @Override
        // Credenciales para los diferentes usuarios
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

                InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(Jaime, Rebeca, Hector);

                return userDetailsManager;
        }

}