package com.ahs.dao;

import com.ahs.entity.UserInfo;
import com.ahs.entity.Account;

public interface IUserInfoDAO {
//    UserInfo getActiveUser(String userName);
//    String getFullName();
//
    Account getAllBalances(int userId);

    void setChecking(double amt, int userId);

    void setSaving(double amt, int userId);

    void checkingToSaving(double amt, int userId);

    void savingToChecking(double amt, int userId);

}
