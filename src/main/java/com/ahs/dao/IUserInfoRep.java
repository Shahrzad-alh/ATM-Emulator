package com.ahs.dao;

import com.ahs.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserInfoRep extends JpaRepository<UserInfo, Integer>{

    public UserInfo findByUserName(String username);

    @Query("select u from UserInfo  u where u.id=?1 and u.accountLocked=true or u.failedAttempt=?3")
    public UserInfo findByX(String username);
}
