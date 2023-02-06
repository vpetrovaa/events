package com.solvd.laba.events.web.mapper.criteria;

import com.solvd.laba.events.domain.criteria.EventCriteria;
import com.solvd.laba.events.web.dto.criteria.EventCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventCriteriaMapper {

    EventCriteria dtoToEntity(EventCriteriaDto eventCriteriaDto);

}
