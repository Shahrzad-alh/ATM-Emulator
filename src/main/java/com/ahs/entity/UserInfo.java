package com.ahs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "failed_attempt")
    private short failedAttempt;
    @Column(name = "account_locked")
    private boolean accountLocked;
    @Column(name = "lock_time")
    private Timestamp lockTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public short getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(short failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Timestamp getLockTime() {
        return lockTime;
    }

    public void setLockTime() {
        this.lockTime = new Timestamp(System.currentTimeMillis());
    }
}
