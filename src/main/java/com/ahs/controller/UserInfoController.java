package com.ahs.controller;

import com.ahs.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ahs.config.AppUserDetailsService.MAX_FAILED_ATTEMPTS;

@Controller
@RequestMapping("app")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/account-details")
    public ModelAndView getAllUserAccounts(HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        ModelAndView mav = new ModelAndView();
        mav.addObject("accountInfo", userInfoService.getAllBalances(username));
        mav.addObject("name", userInfoService.getFullName(username));
        mav.setViewName("accounts");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested service.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }


    @RequestMapping("errNotAuthorized")
    public ModelAndView errNotAuthorized() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized to use this service." ;
        mav.addObject("error", errorMessage);
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "errBadCredential")
    public ModelAndView errBadCredential() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You have failed login attempt.";
        mav.addObject("error", errorMessage);
        mav.setViewName("login");
        return mav;

    }
    @RequestMapping(value = "errLockedAccount")
    public ModelAndView errLockedAccount() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "Account is locked due to " + MAX_FAILED_ATTEMPTS +
                " failed attempts! It will be unlocked after 2 Hours";
        mav.addObject("error", errorMessage);
        mav.setViewName("login");
        return mav;

    }

}

