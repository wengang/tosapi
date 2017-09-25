package io.metaphor.tosapi.config;

import io.metaphor.tosapi.auth.JwtAuthenticationEntryPoint;
import io.metaphor.tosapi.auth.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private UserDetailsService userDetailsService;
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    public ApiSecurityConfiguration(JwtAuthenticationEntryPoint unauthorizedHandler,
                                    UserDetailsService userDetailsService,
                                    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER").and()
//                .withUser("admin").password("admin").roles("USER", "ADMIN", "ACTUATOR");
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
//                .httpBasic()
//                .and()
                .headers().cacheControl().disable()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                .antMatchers("/login", "/logout").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        // 添加JWT filter
        http
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
