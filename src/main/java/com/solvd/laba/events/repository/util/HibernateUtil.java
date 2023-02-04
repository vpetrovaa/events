package com.solvd.laba.events.repository.util;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Ticket;
import com.solvd.laba.events.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Event.class);
            configuration.addAnnotatedClass(Ticket.class);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }

}
