package com.ahs.service;

import com.ahs.entity.Account;
import org.springframework.security.access.annotation.Secured;

public interface IUserInfoService {
    String getFullName(String userName);

    @Secured({"ROLE_ADMIN"})
    Account getAllBalances(String username);

    void setChecking(double amt, String username);

    void setSaving(double amt, String username);

    void checkingToSaving(double amt, String username);

    void savingToChecking(double amt, String username);

}
