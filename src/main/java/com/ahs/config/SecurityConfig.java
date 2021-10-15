package com.ahs.config;

import com.ahs.dao.IUserInfoRep;
import com.ahs.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ahs.config.AppUserDetailsService.MAX_FAILED_ATTEMPTS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private IUserInfoRep userInfoRep;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/secure/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin()    //login configuration
                .loginPage("/app/login")
                .loginProcessingUrl("/app-login")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/app/secure/account-details")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {

                        UserInfo activeUserInfo = userInfoRep.findByUserName(request.getParameter("app_username"));

                        activeUserInfo.setFailedAttempt((short) (activeUserInfo.getFailedAttempt() + 1));
                        String redirectUrl = request.getContextPath() +  "/app/errBadCredential";

                        if(activeUserInfo.getFailedAttempt() >= MAX_FAILED_ATTEMPTS)
                        {
                            activeUserInfo.setLockTime();
                            activeUserInfo.setAccountLocked(true);
                            redirectUrl = request.getContextPath() +  "/app/errLockedAccount";
                        }
                        userInfoRep.save(activeUserInfo);
                        String error = exception.getMessage();
                        System.out.println("A failed login attempt. Reason: " + error);

                        response.sendRedirect(redirectUrl);
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        UserInfo activeUserInfo = userInfoRep.findByUserName(httpServletRequest.getParameter("app_username"));
                        activeUserInfo.setFailedAttempt((short) 0);
                        userInfoRep.save(activeUserInfo);
                        httpServletResponse.sendRedirect("/app/secure/account-details");
                    }
                })
                .and().logout()       //logout configuration
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/app/login")
                .and().exceptionHandling() //exception handling configuration
                .accessDeniedPage("/app/error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
