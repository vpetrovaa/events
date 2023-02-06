package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Event;
import com.solvd.laba.events.domain.Point;
import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.repository.EventRepository;
import com.solvd.laba.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> findByCriteria(EventCriteria criteria) {
        return eventRepository.findByCriteria(criteria)
                .stream()
                .filter(event -> {
                    if (Objects.nonNull(criteria.getUserLocation()) && Objects.nonNull(criteria.getMaxRadius()))
                        return getDistanceBetweenPoints(criteria.getUserLocation(), event.getCoordinates()) <= criteria.getMaxRadius();
                    else return true;
                })
                .collect(Collectors.toList());
    }

    private Double getDistanceBetweenPoints(Point userLocation, Point eventLocation) {
        int earthRadiusKm = 6371;
        double latitudeDiff = degreeToRadian(eventLocation.getLatitude() - userLocation.getLatitude());
        double longitudeDiff = degreeToRadian(eventLocation.getLongitude() - userLocation.getLongitude());
        double a = Math.sin(latitudeDiff / 2) * Math.sin(latitudeDiff / 2) +
                Math.cos(degreeToRadian(userLocation.getLatitude())) *
                        Math.cos(degreeToRadian(eventLocation.getLatitude())) *
                        Math.sin(longitudeDiff / 2) * Math.sin(longitudeDiff / 2);
        var distance = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * distance;
    }

    private Double degreeToRadian(Double degree) {
        return degree * (Math.PI / 180);
    }

}
