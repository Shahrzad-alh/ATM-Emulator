package com.ahs.service;

import com.ahs.dao.IUserInfoDAO;
import com.ahs.dao.IUserInfoRep;
import com.ahs.entity.Account;
import com.ahs.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements IUserInfoService {
    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Autowired
    private IUserInfoRep userInfoRepo;

    @Override
    public String getFullName(String username) {
        UserInfo user = userInfoRepo.findByUserName(username);
        return user.getFullName();
    }

    @Override
    public Account getAllBalances(String username) {
        UserInfo user = userInfoRepo.findByUserName(username);

        return userInfoDAO.getAllBalances(user.getUserId());
    }

    @Override
    public void setChecking(double amt, String username) {
        UserInfo user = userInfoRepo.findByUserName(username);
        userInfoDAO.setChecking(amt, user.getUserId());
    }

    @Override
    public void setSaving(double amt, String username) {
        UserInfo user = userInfoRepo.findByUserName(username);

        userInfoDAO.setSaving(amt, user.getUserId());
    }

    @Override
    public void checkingToSaving(double amt, String username) {
        UserInfo user = userInfoRepo.findByUserName(username);
        userInfoDAO.checkingToSaving(amt, user.getUserId());
    }

    @Override
    public void savingToChecking(double amt, String username) {
        UserInfo user = userInfoRepo.findByUserName(username);
        userInfoDAO.savingToChecking(amt, user.getUserId());
    }
}
