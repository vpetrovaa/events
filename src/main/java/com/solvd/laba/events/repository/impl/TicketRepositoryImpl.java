package com.solvd.laba.events.repository.impl;

import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import com.solvd.laba.events.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Ticket> findByUser(User user) {
        System.out.println("Varya");
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("select * from tickets where user_id=" + user.getId(), Ticket.class).getResultList();
        }
    }
}
