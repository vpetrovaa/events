package com.solvd.laba.events.repository.impl;

import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public boolean isExistByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("From User where email=:email", User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();
        return user != null;
    }

}
