package com.courseproject.demo.mappers;

import com.courseproject.demo.dtos.EventFileDto;
import com.courseproject.demo.entities.Event;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventFileMapper {
    Event toEntity(EventFileDto eventFileDto);

    EventFileDto toDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event partialUpdate(EventFileDto eventFileDto, @MappingTarget Event event);
}