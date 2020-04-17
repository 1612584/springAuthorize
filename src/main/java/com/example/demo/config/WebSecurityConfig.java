package com.example.demo.config;

import com.example.demo.filter.AuthoizationFilter;
import com.example.demo.filter.JWTAuthenticationFilter;
import com.example.demo.filter.JWTLoginFilter;
import com.example.demo.filter.SignUpFilter;
import com.example.demo.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("password").roles("USER").build());
//        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
//        return manager;
//
//    }
//    private final DataSource dataSource;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST,"/sign-up").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login",this.authenticationManager(),bCryptPasswordEncoder), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(bCryptPasswordEncoder);

    }
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
//    }
    //config filter login

    @Bean
    public FilterRegistrationBean<SignUpFilter> filter() {
        FilterRegistrationBean<SignUpFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new SignUpFilter());
        bean.addUrlPatterns("/sign-up");  // or use setUrlPatterns()

        return bean;
    }
    @Bean
    public FilterRegistrationBean<AuthoizationFilter> authozationFilter() {
        FilterRegistrationBean<AuthoizationFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new AuthoizationFilter());
        bean.addUrlPatterns("/news/*");  // or use setUrlPatterns()

        return bean;
    }
}
