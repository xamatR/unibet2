package com.labprogweb.unibet2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod; // Add this import

import com.labprogweb.unibet2.security.JwtAuthFilter;
import com.labprogweb.unibet2.security.JwtService;
import com.labprogweb.unibet2.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("usuarioService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Administrator pode acessar qualquer endpoint
        //Clientes podem acessar apenas os seus prorprios dados e os dados dos eventos nos quais tem apostas
        //Swagger não precisa de autenticação e pode ser acessado por qualquer usuário
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v2/usuarios/**")
                    .permitAll()
                .antMatchers("/api/v2/administrador/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/v2/cliente/**")
                    .hasAnyRole("ADMIN", "CLIENTE")
                .antMatchers(HttpMethod.GET, "/api/v2/evento").hasAnyRole("ADMIN", "CLIENTE")
                .antMatchers("/api/v2/evento/**")
                    .hasAnyRole("ADMIN")
                .antMatchers("/api/v2/aposta/**")
                    .hasAnyRole("ADMIN", "CLIENTE")
                .antMatchers("/api/v2/info-bancaria/")
                    .hasAnyRole("ADMIN", "CLIENTE")
                .antMatchers("/api/v2/escolha/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/v2/odds/**")
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class); // Certifique-se de que o filtro JWT não bloqueie a solicitação
    
    }

//    http
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/api/v2/usuarios/**").permitAll() // Corrigido para incluir a barra inicial
//            .antMatchers("/api/v2/administrador/**").hasRole("ADMIN")
//    // Cliente endpoints
//            .antMatchers("/api/v2/cliente/**").hasAnyRole("ADMIN", "CLIENTE")
//    // Evento endpoints
//            .antMatchers(HttpMethod.GET,"/api/v2/evento").hasAnyRole("ADMIN", "CLIENTE")
//            .antMatchers("/api/v2/evento/**").hasAnyRole("ADMIN")
//    // Aposta endpoints
//            .antMatchers("/api/v2/aposta/**").hasAnyRole("ADMIN", "CLIENTE")
//            .antMatchers("/api/v2/info-bancaria/").hasAnyRole("ADMIN", "CLIENTE")
//            .antMatchers("/api/v2/escolha/**").hasRole("ADMIN")
//            .antMatchers("/api/v2/odds/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class); // Certifique-se de que o filtro JWT não bloqueie a solicitação

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
