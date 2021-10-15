package com.ahs.config;

import com.ahs.dao.IUserInfoRep;
import com.ahs.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class AppUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private IUserInfoRep userInfoRep;

    public static final int MAX_FAILED_ATTEMPTS = 3;
    public static final int LOCK_DURATION = 2 * 60 * 60 * 1000; // Lock Time : 2 Hours

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        UserInfo activeUserInfo = userInfoRep.findByUserName(userName);
        if(activeUserInfo.getAccountLocked()) {
            if (activeUserInfo.getLockTime().before(new Date(System.currentTimeMillis() - LOCK_DURATION))) {
                activeUserInfo.setAccountLocked(false);
                activeUserInfo.setFailedAttempt((short) 0);
            }
        }
        userInfoRep.save(activeUserInfo);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        UserDetails userDetails = new User(activeUserInfo.getUserName(),
                activeUserInfo.getPassword(),true, true, true,
                !(activeUserInfo.getAccountLocked()),  Arrays.asList(authority));
        return userDetails;
    }
}
