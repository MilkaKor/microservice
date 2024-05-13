package com.courseproject.demo.mappers;

import com.courseproject.demo.dtos.EventDto;
import com.courseproject.demo.entities.Event;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {FileEventMapper.class})
public interface EventMapper {
    Event toEntity(EventDto eventDto);

    @AfterMapping
    default void linkFiles(@MappingTarget Event event) {
        event.getFiles().forEach(file -> file.setEvent(event));
    }

    EventDto toDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event partialUpdate(EventDto eventDto, @MappingTarget Event event);
}