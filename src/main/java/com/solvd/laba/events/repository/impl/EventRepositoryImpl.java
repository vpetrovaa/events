package com.solvd.laba.events.repository.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final SessionFactory sessionFactory;

    private final static String SELECT_QUERY =
            "select events.id,  events.name, events.description, events.event_time, events.price, " +
                    "events.type, events.topic, events.country, events.city, events.status, " +
                    "events.latitude, events.longitude from events %s;";

    @Override
    public List<Event> findByCriteria(EventCriteria criteria) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery(prepareQuery(criteria), Event.class).getResultList();
        }
    }

    private String prepareQuery(EventCriteria criteria) {
        StringJoiner queryJoiner = new StringJoiner(" and ", Strings.EMPTY, Strings.EMPTY);
        boolean isWhereClauseAdded = false;
        if (Objects.nonNull(criteria.getCity())) {
            isWhereClauseAdded = checkQuery(isWhereClauseAdded, queryJoiner);
            queryJoiner.add("events.city = '" + criteria.getCity() + "'");
        }
        if (Objects.nonNull(criteria.getType())) {
            isWhereClauseAdded = checkQuery(isWhereClauseAdded, queryJoiner);
            queryJoiner.add("events.type = '" + criteria.getType() + "'");
        }
        if (Objects.nonNull(criteria.getMaxPrice())) {
            isWhereClauseAdded = checkQuery(isWhereClauseAdded, queryJoiner);
            queryJoiner.add("events.price <= " + criteria.getMaxPrice());
        }
        if (Objects.nonNull(criteria.getTopic())) {
            isWhereClauseAdded = checkQuery(isWhereClauseAdded, queryJoiner);
            queryJoiner.add("events.topic like '%" + criteria.getTopic() + "%'");
        }
        return String.format(SELECT_QUERY, queryJoiner);
    }

    private boolean checkQuery(boolean isWhereClauseAdded, StringJoiner queryJoiner) {
        if (!isWhereClauseAdded) {
            queryJoiner.add(" where id > 0");
            isWhereClauseAdded = true;
        }
        return isWhereClauseAdded;
    }

}
