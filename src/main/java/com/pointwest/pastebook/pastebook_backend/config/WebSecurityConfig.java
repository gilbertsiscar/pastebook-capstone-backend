package com.pointwest.pastebook.pastebook_backend.config;

import com.pointwest.pastebook.pastebook_backend.services.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticate jwtAuthenticate;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticate jwtAuthenticationEntryPointBean() throws Exception{
        return new JwtAuthenticate();
    }

    @Bean
    @Autowired
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
                .antMatchers("/api/test").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/users/login").permitAll()
                .antMatchers("/api/users/security/{userid}").permitAll()
                .antMatchers("/api/users/details/{userid}").permitAll()
                .antMatchers("/users/profile/{profileUrl}").permitAll()
                .antMatchers("/api/users/aboutme/{userId}").permitAll()
                .antMatchers("/api/users/profilePicture").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/search/{searchTerm}").permitAll()
                .antMatchers(HttpMethod.GET, " /api/users/profile/{userId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts").permitAll()
                .antMatchers("/api/like/{postId}").permitAll()
                .antMatchers("/api/comment/{postId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/{userid}/test").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/test").permitAll()
                .antMatchers(HttpMethod.POST, "/api/friendRequests").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friendRequests/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friendRequests/{senderId}/{receiverId}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/friendRequests/{senderId}/{receiverId}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/friends").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/friends/{requesterId}/{recipientId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friends/{pageId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/friends/{requesterId}/{recipientId}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/albums").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/albums/{albumId}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/albums/{albumId}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/albums/{userId}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/tl/photos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/tl/display/albums").permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts").permitAll()
                .antMatchers("/api/like/{postId}").permitAll()
                .antMatchers("/api/comment/{postId}").permitAll()
                .antMatchers("/onlineconnection").permitAll()

                .antMatchers("/api/notifications/short").permitAll()
                .antMatchers("/api/notifications/all").permitAll()
                .antMatchers("/api/notifications/seen").permitAll()

                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticate).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user-photos/**");
    }
}
