package com.ahs.dao;

import com.ahs.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account getAllBalances(int userId) {
        String hql = "FROM Account as atcl WHERE atcl.accountId=" + userId;
        return ((List<Account>) entityManager.createQuery(hql).getResultList()).get(0);
    }

    @Override
    public void setChecking(double amt, int userId) {
        Query query = entityManager.createQuery("update Account set checking=:amt where account_id=:userId");
        query.setParameter("amt", amt);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void setSaving(double amt, int userId) {
        Query query = entityManager.createQuery("update Account set saving=:amt where account_id=:userId");
        query.setParameter("amt", amt);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void checkingToSaving(double amt, int userId) {
        Query query = entityManager.createQuery("update Account set saving=saving+:amt where account_id =:userId");
        query.setParameter("amt", amt);
        query.setParameter("userId", userId);
        query.executeUpdate();
        Query query1 = entityManager.createQuery("update Account set checking=checking-:amt where account_id =:userId");
        query1.setParameter("amt", amt);
        query1.setParameter("userId", userId);
        query1.executeUpdate();
    }

    @Override
    public void savingToChecking(double amt, int userId) {
        Query query = entityManager.createQuery("update Account set saving=saving-:amt where account_id =:userId");
        query.setParameter("amt", amt);
        query.setParameter("userId", userId);
        query.executeUpdate();
        Query query1 = entityManager.createQuery("update Account set checking=checking+:amt where account_id =:userId");
        query1.setParameter("amt", amt);
        query1.setParameter("userId", userId);
        query1.executeUpdate();
    }


}
